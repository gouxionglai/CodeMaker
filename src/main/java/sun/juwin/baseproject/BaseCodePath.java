/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.baseproject;

import sun.juwin.baseproject.database.JdbcModel;

/**
 * @author sunqinwen
 * @version \: CodePath.java,v 0.1 2018-08-07 18:19
 */
public class BaseCodePath {

    private String tableName;

    private String className;

    // 惯用类格式路径
    private String baseProPath;

    private String pointModelPath;

    private String pointMapperPath;

    private String pointServicePath;

    private String pointServiceImplPath;

    private String pointControllerPath;

    // 文件绝对路径
    private String targetModelPath;

    private String targetMapperPath;

    private String targetServicePath;

    private String targetServiceImplPath;

    private String targetControllerPath;

    public BaseCodePath(String mysqlUrl, String mysqlUser, String mysqlPwd, String schema) {
        JdbcModel.url = mysqlUrl;
        JdbcModel.user = mysqlUser;
        JdbcModel.pwd = mysqlPwd;
        JdbcModel.schema = schema;
    }

    public String getBaseProPath() {
        return baseProPath;
    }

    public BaseCodePath setBaseProPath(String baseProPath) {
        this.baseProPath = baseProPath;
        return this;
    }

    public String getPointModelPath() {
        return pointModelPath;
    }

    public BaseCodePath setPointModelPath(String pointModelPath) {
        this.pointModelPath = pointModelPath;
        targetModelPath = baseProPath + "/" + pointModelPath.replace(".", "/");
        return this;
    }

    public String getPointMapperPath() {
        return pointMapperPath;
    }

    public BaseCodePath setPointMapperPath(String pointMapperPath) {
        this.pointMapperPath = pointMapperPath;
        targetMapperPath = baseProPath + "/" + pointMapperPath.replace(".", "/");
        return this;
    }

    public String getPointServicePath() {
        return pointServicePath;
    }

    public BaseCodePath setPointServicePath(String pointServicePath) {
        this.pointServicePath = pointServicePath;
        targetServicePath = baseProPath + "/" + pointServicePath.replace(".", "/");
        return this;
    }

    public String getPointServiceImplPath() {
        return pointServiceImplPath;
    }

    public BaseCodePath setPointServiceImplPath(String pointServiceImplPath) {
        this.pointServiceImplPath = pointServiceImplPath;
        targetServiceImplPath = baseProPath + "/" + pointServiceImplPath.replace(".", "/");
        return this;
    }

    public String getPointControllerPath() {
        return pointControllerPath;
    }

    public BaseCodePath setPointControllerPath(String pointControllerPath) {
        this.pointControllerPath = pointControllerPath;
        targetControllerPath = baseProPath + "/" + pointControllerPath.replace(".", "/");
        return this;
    }

    public String getTargetModelPath() {
        return targetModelPath;
    }

    public String getTargetMapperPath() {
        return targetMapperPath;
    }

    public String getTargetServicePath() {
        return targetServicePath;
    }

    public String getTargetServiceImplPath() {
        return targetServiceImplPath;
    }

    public String getTargetControllerPath() {
        return targetControllerPath;
    }

    public String getTargetModelName() {
        return String.format("%s.java", className);
    }

    public String getTargetMapperName() {
        return String.format("%sMapper.java", className);
    }

    public String getTargetXmlName(){
        return String.format("%sMapper.xml", className);
    }

    public String getTargetServiceName() {
        return String.format("%sService.java", className);
    }

    public String getTargetServiceImplName() {
        return String.format("%sServiceImpl.java", className);
    }

    public String getTargetControllerName() {
        return String.format("%sController.java", className);
    }

    public String getTableName() {
        return tableName;
    }

    public BaseCodePath setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public BaseCodePath setClassName(String className) {
        this.className = className;
        return this;
    }
}
