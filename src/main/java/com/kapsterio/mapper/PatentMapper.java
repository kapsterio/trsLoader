package com.kapsterio.mapper;

import com.kapsterio.model.Patent;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by bj-m-206255a on 2017/4/22.
 */

@Mapper
public interface PatentMapper {
    @Results({
            @Result(property = "publicDate", column = "public_date"),
            @Result(property = "applyNo", column = "apply_no"),
            @Result(property = "applyDate", column = "apply_date"),
            @Result(property = "primaryClassNo", column = "primary_class_no"),
            @Result(property = "classNo", column = "class_no"),
            @Result(property = "abstractContent", column = "abstract"),
            @Result(property = "protectItem", column = "protect_item"),
            @Result(property = "pageNum", column = "page_no"),
            @Result(property = "internationalApply", column = "international_apply"),
            @Result(property = "internationalPublic", column = "international_public"),
            @Result(property = "importDate", column = "import_date")
    })
    @Select("select * from patent where id = #{id}")
    Patent findById(String id);


    void insert(Patent patent);

    @Insert("create table if not exists patent(" +
            " id VARCHAR(40) NOT NULL," +
            " public_date DATE," +
            " apply_no VARCHAR(40)," +
            " apply_date DATE," +
            " name VARCHAR(200) NOT NULL," +
            " primary_class_no TEXT," +
            " class_no TEXT," +
            " applier TEXT," +
            " author TEXT," +
            " address VARCHAR(200)," +
            " agency VARCHAR(200)," +
            " agent VARCHAR(100)," +
            " abstract TEXT," +
            " protect_item mediumtext," +
            " path VARCHAR(100)," +
            " page_num SMALLINT(3)," +
            " code VARCHAR(20)," +
            " international_apply VARCHAR(100)," +
            " international_public VARCHAR(100)," +
            " import_date DATE," +
            " PRIMARY KEY (id)" +
            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    void createPatentTableIfNotExist();

    @Insert({
            "<script>",
            "insert into patent (id, public_date, apply_no, apply_date, name, primary_class_no, class_no, applier, author, address, agency, agent, abstract, protect_item, path, page_num, code, international_apply, international_public, import_date)",
            "values ",
            "<foreach  collection='list' item='patent' separator=','>",
            "( #{patent.id}, #{patent.publicDate}, #{patent.applyNo}, #{patent.applyDate},#{patent.name},#{patent.primaryClassNo}, #{patent.classNo},#{patent.applier},#{patent.author},#{patent.address},#{patent.agency}, #{patent.agent}, #{patent.abstractContent}, #{patent.protectItem}, #{patent.path}, #{patent.pageNum}, #{patent.code}, #{patent.internationalApply}, #{patent.internationalPublic}, #{patent.importDate})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(List<Patent> patentList);
}
