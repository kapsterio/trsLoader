package com.kapsterio;

import com.kapsterio.mapper.PatentMapper;
import com.kapsterio.model.Patent;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by bj-m-206255a on 2017/4/22.
 */
@Component
public class DataLoader {
    private static Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private static final String BEGIN = "<REC>";

    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    private ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private  PatentMapper mapper;

    @Autowired
    private SqlSessionFactory factory;

    private Map<String, String> propertiesMap = Maps.newHashMap();


    @PostConstruct
    private void init() {
        propertiesMap.put("<公开（公告）号>", "id");
        propertiesMap.put("<公开（公告）日>", "publicDate");
        propertiesMap.put("<申请号>", "applyNo");
        propertiesMap.put("<申请日>", "applyDate");
        propertiesMap.put("<名称>", "name");
        propertiesMap.put("<主分类号>", "primaryClassNo");
        propertiesMap.put("<分类号>", "classNo");
        propertiesMap.put("<申请（专利权）人>", "applier");
        propertiesMap.put("<发明（设计）人>", "author");
        propertiesMap.put("<地址>", "address");
        propertiesMap.put("<专利代理机构>", "agency");
        propertiesMap.put("<代理人>", "agent");
        propertiesMap.put("<摘要>", "abstractContent");
        propertiesMap.put("<主权项>", "protectItem");
        propertiesMap.put("<发布路径>", "path");
        propertiesMap.put("<页数>", "pageNum");
        propertiesMap.put("<国省代码>", "code");
        propertiesMap.put("<国际申请>", "internationalApply");
        propertiesMap.put("<国际公布>", "internationalPublic");
        propertiesMap.put("<<进入国家日期>", "importDate");
    }

    public void loadFileToMysql(Path file) {
        this.mapper.createPatentTableIfNotExist();
        if (Files.exists(file) && Files.isReadable(file)) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line = null;
                List<Patent> patents = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    if (BEGIN.equals(line)) {
                        Patent patent = parsePatent(reader);
                        if (patents.size() == 1000) {
                            //flush to db and clear the list;
                            executorService.submit(new PatentInsertTask(factory, ImmutableList.copyOf(patents)));
                            patents.clear();
                        }
                        patents.add(patent);
                    }
                }

                if (patents.size() != 0) {

                    executorService.submit(new PatentInsertTask(factory, ImmutableList.copyOf(patents)));
                    //mapper.batchInsert(patents);
                    //logger.info("{} patents have being loaded....", patents.size());

                    //executorService.submit(() -> System.out.println(patents));
                }
                logger.info("{} is completed", file.getFileName());
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        } else {
            logger.error("{} file not exist or not readable", file.getFileName());
        }
    }

    public void shutDownAndWait() throws Exception{
        //executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
    }


    /**
     * <REC>
     <公开（公告）号>=CN85100462A
     <公开（公告）日>=1985.11.10
     <申请号>=CN85100462
     <申请日>=1985.04.03
     <名称>=一种可用于常温常压合成复盆子酮的触媒
     <主分类号>=B01J23/44
     <分类号>=B01J23/44;C07C49/245
     <申请（专利权）人>=南开大学
     <发明（设计）人>=蒿怀桐;苏正元;李壁宜;朱信强;贾琦
     <地址>=天津市南开区八里台
     <专利代理机构>=南开大学专利事务所
     <代理人>=谭海安;解松凡
     <摘要>=一种可用于<img file="85100462_ab_0.GIF" wi="546" he="71" />催化氢化而合成<img file="85100462_ab_1.GIF" wi="546" he="66" />(复盆子酮)的触媒，它是以活性碳作载体并混入5％的钯所组成。使用本触媒可使催化加氢反应在常温常压下进行，且复盆子酮的产率达94.4％(按不饱和酮计)。
     <主权项>=1、一种可以用于从HO-<img file="85100462_IMG3.GIF" wi="70" he="65" />-CH=CH-CO-CH<sub>3</sub>制备HO-<img file="85100462_IMG4.GIF" wi="70" he="65" />-CH<sub>2</sub>-CH<sub>2</sub>COCH<sub>3</sub>(复盆子酮)的催化氢化触媒，其特征在于它是以活性碳作载体，混入5%的钯组成。
     <发布路径>=BOOKS/FM/1985/19851110/85100462
     <页数>=12
     <国省代码>=天津;12

     <公开（公告）号>=CN1279731A
     <公开（公告）日>=2001.01.10
     <申请号>=CN98811333.3
     <申请日>=1998.03.11
     <名称>=放电表面处理用压粉体电极
     <主分类号>=C23C26/00
     <分类号>=C23C26/00
     <申请（专利权）人>=三菱电机株式会社
     <发明（设计）人>=井上徹;後藤昭弘
     <地址>=日本东京
     <国际申请>=PCT/JP98/01007 1998.03.11
     <国际公布>=WO99/46424 日 1999.09.16
     <进入国家日期>=2000.05.19
     <专利代理机构>=上海专利商标事务所
     <代理人>=孙敬国
     <摘要>=本发明揭示一种放电表面处理用压粉体电极,由金属粉末或者金属化合物粉末、或者在它们中间添加陶瓷粉末后加压成型的压粉体电极主体和在所述压粉体电极主体的加压成型中使用的金属制压模构成,不必从压模中取出压粉体电极主体部分、而连压模一起在放电表面处理中使用。
     <主权项>=1．一种放电表面处理用压粉体电极，所述放电表面处理是在金属粉末或者金属化合物粉末、或者在它们中间添加陶瓷粉末后加压成型的压粉体电极和工件之间产生放电，并利用放电能量，在工件表面上形成由电极材料或者电极材料由于放电能量反应后的物质组成的覆盖膜，在所述放电表面处理中，其特征在于，所述放电表面处理用压粉体电极由金属粉末或者金属化合物粉末、或者在它们中间添加陶瓷粉末后加压成型的压粉体电极主体和在所述压粉体电极主体的加压成型中使用的金属制压模构成，不必从压模中取出压粉体电极主体、而连压模一起在放电表面处理中使用。
     <发布路径>=BOOKS/FM/2001/20010110/98811333.3
     <页数>=12
     <国省代码>=日本;JP
     */

    private Patent parsePatent(BufferedReader reader) throws IOException{
        String line = null;
        Patent patent = new Patent();
        while ((line = reader.readLine()) != null && !line.trim().equals("")) {
            String tag = line.substring(0, line.indexOf("="));
            if (propertiesMap.containsKey(tag)) {
                String property = propertiesMap.get(tag);
                String value = line.substring(line.indexOf("=") + 1, line.length());
                Object actualValue = null;
                if (property.equals("publicDate") || property.equals("applyDate") || property.equals("importDate")) {
                    //解析成
                    try {
                        actualValue = format.parse(value);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (property.equals("pageNum")) {
                    actualValue = Integer.parseInt(value);
                } else {
                    actualValue = value;
                }

                try {
                    Field field = Patent.class.getDeclaredField(property);
                    field.setAccessible(true);
                    field.set(patent, actualValue);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return patent;
    }

}
