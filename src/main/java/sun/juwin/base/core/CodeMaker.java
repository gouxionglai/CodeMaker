/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.base.core;

import com.google.common.base.Strings;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import sun.juwin.base.dao.Table;
import sun.juwin.base.exception.BaseCodeMakerException;
import sun.juwin.base.handler.BaseHandler;
import sun.juwin.base.handlers.*;
import sun.juwin.base.model.TaskModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author sunqinwen
 * @version \: CodeMaker.java,v 0.1 2018-08-07 17:51 
 *
 */
public class CodeMaker {

    private static final String TEMPLATE_PATH = "CodeMaker/src/main/resources";

    private static final String START_WORD = "file:/";

    private static final String PRO_NAME = "CodeMaker";

    private VelocityEngine ve;

    // 全局模板参数
    private VelocityContext context = new VelocityContext();

    private String tableName;

    private String modelName;

    //路径
    private CodePath codePath;

    private List<CodeMakerHandler> handlers = new ArrayList<CodeMakerHandler>();

    public CodeMaker buildMaker(CodePath codePath) throws BaseCodeMakerException{

        if(codePath == null || Strings.isNullOrEmpty(codePath.getBaseProPath())){
            throw new BaseCodeMakerException("您目标项目路径不可为空！");
        }

        String path = BaseHandler.class.getResource("").toString();
        //取得模板根目录
        String temPath = path.substring(path.indexOf(START_WORD) + 6, path.indexOf(PRO_NAME)) + TEMPLATE_PATH;
        //Velocity引擎及初始化相关
        this.ve = new VelocityEngine();
        Properties p = new Properties();
        p.put(Velocity.FILE_RESOURCE_LOADER_PATH, temPath);
        ve.init(p);
        // 初始化责任链
        if(!Strings.isNullOrEmpty(codePath.getPointModelPath())){
            handlers.add(new ModelHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointMapperPath())){
            handlers.add(new MapperHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointServicePath())){
            handlers.add(new ServiceHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointServiceImplPath())){
            handlers.add(new ServiceImplHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointControllerPath())){
            handlers.add(new ControllerHandler());
        }

        return new CodeMaker();
    }

    public CodeMaker setModelName(String tableName, String modelName) throws BaseCodeMakerException{

        if(Strings.isNullOrEmpty(tableName) || Strings.isNullOrEmpty(modelName)) {
            throw new BaseCodeMakerException("非常抱歉，表名和model类名都是必填项！");
        }

        Table table = new Table();
        TaskModel model = table.getTaskModel(tableName);
        if (model != null && model.getModels() != null && model.getModels().size() > 0) {
            context.put("model", model);
            context.put("className", modelName);
            context.put("modelPath", codePath.getPointModelPath());
            context.put("mapperPath", codePath.getPointMapperPath());
            context.put("servicePath", codePath.getPointServicePath());
            context.put("serviceImplPath", codePath.getPointServiceImplPath());
            context.put("controllerPath", codePath.getPointControllerPath());
            context.put("tableName", tableName);
            context.put("lowerClassName", modelName.substring(0, 1).toLowerCase() + modelName.substring(1));
        }else{
            throw new BaseCodeMakerException("根据您给的数据库及库表，无法反射出可用属性！");
        }

        this.tableName = tableName;
        this.modelName = modelName;

        return this;
    }

    public void setHandlers(Map<String, String> vmParam, CodeMakerHandler handler){
        if(handler != null){
            if(vmParam != null){
                vmParam.forEach((k, v) -> context.put(k, v));
                handlers.add(handler);
            }
        }
    }
}
