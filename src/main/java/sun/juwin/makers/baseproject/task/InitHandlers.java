/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.makers.baseproject.task;

import com.google.common.base.Strings;
import sun.juwin.makers.baseproject.model.BaseCodePath;
import sun.juwin.core.CodeMaker;
import sun.juwin.exception.BaseCodeMakerException;
import sun.juwin.makers.baseproject.model.TaskModel;
import sun.juwin.constant.CodeMakerConstant;
import sun.juwin.makers.baseproject.database.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunqinwen
 * @version \: InitPath.java,v 0.1 2018-08-08 12:59
 */
public class InitHandlers {

    public static void initHandlers(BaseCodePath codePath, CodeMaker codeMaker) throws BaseCodeMakerException {
        Map<String, Object> mapParam;
        if (!Strings.isNullOrEmpty(codePath.getPointModelPath())) {
            mapParam = new HashMap<>();
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
            } else {
                throw new BaseCodeMakerException("根据您给的数据库及库表，无法反射出可用属性！");
            }
            codeMaker.setHandlers(mapParam);
        } else {
            throw new BaseCodeMakerException("model层为基础层，必须要有！");
        }

        if (!Strings.isNullOrEmpty(codePath.getPointMapperPath())) {
            mapParam = new HashMap<>();
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/mapper.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetMapperPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetMapperName());
            mapParam.put("mapperPath", codePath.getPointMapperPath());
            codeMaker.setHandlers(mapParam);

            mapParam = new HashMap<>();
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/mapper-xml.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetMapperPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetXmlName());
            mapParam.put("mapperPath", codePath.getPointMapperPath());
            codeMaker.setHandlers(mapParam);
        }
        if (!Strings.isNullOrEmpty(codePath.getPointServicePath())) {
            mapParam = new HashMap<>();
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/service.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetServicePath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetServiceName());
            mapParam.put("servicePath", codePath.getPointServicePath());
            codeMaker.setHandlers(mapParam);
        }
        if (!Strings.isNullOrEmpty(codePath.getPointServiceImplPath())) {
            mapParam = new HashMap<>();
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/service-impl.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetServiceImplPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetServiceImplName());
            mapParam.put("serviceImplPath", codePath.getPointServiceImplPath());
            codeMaker.setHandlers(mapParam);
        }
        if (!Strings.isNullOrEmpty(codePath.getPointControllerPath())) {
            mapParam = new HashMap<>();
            mapParam.put(CodeMakerConstant.TARGET_VM_PATH, "base/controller.vm");
            mapParam.put(CodeMakerConstant.TARGET_FILE_PATH, codePath.getTargetControllerPath());
            mapParam.put(CodeMakerConstant.TARGET_FILE_NAME, codePath.getTargetControllerName());
            mapParam.put("controllerPath", codePath.getPointControllerPath());
            codeMaker.setHandlers(mapParam);
        }
    }

}
