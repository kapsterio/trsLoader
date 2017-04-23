package com.kapsterio.model;

import java.util.Date;

/**
 * Created by bj-m-206255a on 2017/4/22.
 *
 create table patent(
 id VARCHAR(40) NOT NULL,
 public_date DATE,
 apply_no VARCHAR(40) NOT NULL,
 apply_date DATE,
 name VARCHAR(200) NOT NULL,
 primary_class_no VARCHAR(20) NOT NULL,
 class_no VARCHAR(80) NOT NULL,
 applier VARCHAR(100) NOT NULL,
 author VARCHAR(200) NOT NULL,
 address VARCHAR(200) NOT NULL,
 agency VARCHAR(200) NOT NULL,
 agent VARCHAR(100) NOT NULL,
 abstract TEXT,
 protect_iterm TEXT,
 path VARCHAR(100) NOT NULL,
 page_num SMALLINT(3) NOT NULL,
 code VARCHAR(20) NOT NULL,
 PRIMARY KEY (id)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Patent {
    private String id;
    private Date publicDate;
    private String applyNo;
    private Date applyDate;
    private String name;
    private String primaryClassNo;
    private String classNo;
    private String applier;
    private String author;
    private String address;
    private String agency;
    private String agent;
    private String abstractContent;
    private String protectItem;
    private String path;
    private int pageNum;
    private String code;
    private String internationalApply;
    private String internationalPublic;
    private Date importDate;

    public String getInternationalApply() {
        return internationalApply;
    }

    public void setInternationalApply(String internationalApply) {
        this.internationalApply = internationalApply;
    }

    public String getInternationalPublic() {
        return internationalPublic;
    }

    public void setInternationalPublic(String internationalPublic) {
        this.internationalPublic = internationalPublic;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryClassNo() {
        return primaryClassNo;
    }

    public void setPrimaryClassNo(String primaryClassNo) {
        this.primaryClassNo = primaryClassNo;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getProtectItem() {
        return protectItem;
    }

    public void setProtectItem(String protectItem) {
        this.protectItem = protectItem;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "Patent{" +
                "id='" + id + '\'' +
                ", publicDate=" + publicDate +
                ", applyNo='" + applyNo + '\'' +
                ", applyDate=" + applyDate +
                ", name='" + name + '\'' +
                ", primaryClassNo='" + primaryClassNo + '\'' +
                ", classNo='" + classNo + '\'' +
                ", applier='" + applier + '\'' +
                ", author='" + author + '\'' +
                ", address='" + address + '\'' +
                ", agency='" + agency + '\'' +
                ", agent='" + agent + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", protectItem='" + protectItem + '\'' +
                ", path='" + path + '\'' +
                ", pageNum=" + pageNum +
                ", code='" + code + '\'' +
                ", internationalApply='" + internationalApply + '\'' +
                ", internationalPublic='" + internationalPublic + '\'' +
                ", importDate=" + importDate +
                '}';
    }
}
