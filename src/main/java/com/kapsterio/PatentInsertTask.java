package com.kapsterio;

import com.kapsterio.mapper.PatentMapper;
import com.kapsterio.model.Patent;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by bj-m-206255a on 2017/4/22.
 */
public class PatentInsertTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(PatentInsertTask.class);
    private SqlSessionFactory sqlSessionFactory;

    private List<Patent> patents;

    public PatentInsertTask(SqlSessionFactory sqlSessionFactory, List<Patent> patents) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.patents = patents;
    }

    @Override
    public void run() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            PatentMapper mapper = session.getMapper(PatentMapper.class);
            mapper.batchInsert(patents);
            logger.info("{} patents have being loaded ...", patents.size());
        } catch (Exception e) {
          logger.error("wrong!! ", e);
          //logger.info("patent list: {}", patents);
        } finally {
            session.close();
        }
    }
}
