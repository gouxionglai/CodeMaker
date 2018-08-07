/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.base.core;

/**
 * @author sunqinwen
 * @version \: CodePath.java,v 0.1 2018-08-07 18:19
 */
public class CodePath {

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

    public String getBaseProPath() {
        return baseProPath;
    }

    public CodePath setBaseProPath(String baseProPath) {
        this.baseProPath = baseProPath;
        return this;
    }

    public String getPointModelPath() {
        return pointModelPath;
    }

    public CodePath setPointModelPath(String pointModelPath) {
        this.pointModelPath = pointModelPath;
        targetModelPath = baseProPath + "/" + pointModelPath.replace(".", "/");
        return this;
    }

    public String getPointMapperPath() {
        return pointMapperPath;
    }

    public CodePath setPointMapperPath(String pointMapperPath) {
        this.pointMapperPath = pointMapperPath;
        targetMapperPath = baseProPath + "/" + pointMapperPath.replace(".", "/");
        return this;
    }

    public String getPointServicePath() {
        return pointServicePath;
    }

    public CodePath setPointServicePath(String pointServicePath) {
        this.pointServicePath = pointServicePath;
        targetServicePath = baseProPath + "/" + pointServicePath.replace(".", "/");
        return this;
    }

    public String getPointServiceImplPath() {
        return pointServiceImplPath;
    }

    public CodePath setPointServiceImplPath(String pointServiceImplPath) {
        this.pointServiceImplPath = pointServiceImplPath;
        targetServiceImplPath = baseProPath + "/" + pointServiceImplPath.replace(".", "/");
        return this;
    }

    public String getPointControllerPath() {
        return pointControllerPath;
    }

    public CodePath setPointControllerPath(String pointControllerPath) {
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
}
