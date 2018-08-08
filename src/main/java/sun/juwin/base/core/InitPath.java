/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.base.core;

import com.google.common.base.Strings;
import sun.juwin.base.dao.Table;
import sun.juwin.base.exception.BaseCodeMakerException;
import sun.juwin.base.handlers.BaseHandler;
import sun.juwin.base.model.TaskModel;
import sun.juwin.constant.CodeMakerConstant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sunqinwen
 * @version \: InitPath.java,v 0.1 2018-08-08 12:59 
 *
 */
public class InitPath {

    public static void initHandlers(BaseCodePath codePath, CodeMaker codeMaker) throws BaseCodeMakerException{
        Map<String, Object> mapParam = new HashMap<>();
        if(!Strings.isNullOrEmpty(codePath.getPointModelPath())){
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/model.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetModelPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetModelName());
            Table table = new Table();
            TaskModel model = table.getTaskModel(codePath.getTableName());
            if (model != null && model.getModels() != null && model.getModels().size() > 0) {
                mapParam.put("model", model);
                mapParam.put("className", codePath.getClassName());
                mapParam.put("modelPath", codePath.getPointModelPath());
                mapParam.put("tableName", codePath.getTableName());
                mapParam.put("lowerClassName", codePath.getClassName().substring(0, 1).toLowerCase()
                        + codePath.getClassName().substring(1));
            }else{
                throw new BaseCodeMakerException("根据您给的数据库及库表，无法反射出可用属性！");
            }
            codeMaker.setHandlers(mapParam, new BaseHandler());
        }else{
            throw new BaseCodeMakerException("model层为基础层，必须要有！");
        }

        if(!Strings.isNullOrEmpty(codePath.getPointMapperPath())){
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/mapper.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetMapperPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetMapperName());
            mapParam.put("mapperPath", codePath.getPointMapperPath());
            codeMaker.setHandlers(mapParam, new BaseHandler());

            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/mapper-xml.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetMapperPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetXmlName());
            mapParam.put("mapperPath", codePath.getPointMapperPath());
            codeMaker.setHandlers(mapParam, new BaseHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointServicePath())){
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/service.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetServicePath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetServiceName());
            mapParam.put("servicePath", codePath.getPointServicePath());
            codeMaker.setHandlers(mapParam, new BaseHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointServiceImplPath())){
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/service-xml.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetServiceImplPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetServiceImplName());
            mapParam.put("serviceImplPath", codePath.getPointServiceImplPath());
            codeMaker.setHandlers(mapParam, new BaseHandler());
        }
        if(!Strings.isNullOrEmpty(codePath.getPointControllerPath())){
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/controller.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetControllerPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetControllerName());
            mapParam.put("controllerPath", codePath.getPointControllerPath());
            codeMaker.setHandlers(mapParam, new BaseHandler());
        }
    }

}
