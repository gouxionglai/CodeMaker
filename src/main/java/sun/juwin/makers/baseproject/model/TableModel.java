/**
 * Copyright (c) 2009-2017 All Rights Reserved.
 */
package sun.juwin.makers.baseproject.model;

/**
 * @author Juwin.S
 * @version \: TableModel.java,v 0.1 2017-03-02 10:14
 * 用来存放数据库表最基本的元素
 */
public class TableModel {

    //大写属性名
    private String capitalCase;

    //小写属性名
    private String lowerCase;

    //属性javaType
    private String javaType;

    //注释
    private String note;

    //需要导入的包
    private String importPackages;

    public String getCapitalCase() {
        return capitalCase;
    }

    public void setCapitalCase(String capitalCase) {
        this.capitalCase = capitalCase;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String importPackages) {
        this.importPackages = importPackages;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
