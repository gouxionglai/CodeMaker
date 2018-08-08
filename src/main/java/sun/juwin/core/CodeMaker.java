/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.core;

import com.google.common.base.Strings;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import sun.juwin.baseproject.BaseCodePath;
import sun.juwin.baseproject.InitPath;
import sun.juwin.exception.BaseCodeMakerException;
import sun.juwin.core.handlers.*;
import sun.juwin.constant.CodeMakerConstant;

import java.util.*;

/**
 *
 * @author sunqinwen
 * @version \: CodeMaker.java,v 0.1 2018-08-07 17:51 
 *
 */
public class CodeMaker {

    private VelocityEngine ve;

    private int handlerSize = 0;

    private VelocityContext context = new VelocityContext();

    private List<Map<String, Object>> filePath = new ArrayList<>();

    private List<CodeMakerHandler> handlers = new ArrayList<CodeMakerHandler>();

    public CodeMaker buildBaseProjectMaker(BaseCodePath codePath) throws BaseCodeMakerException{

        if(codePath == null || Strings.isNullOrEmpty(codePath.getBaseProPath())){
            throw new BaseCodeMakerException("您目标项目路径不可为空！");
        }

        String path = CodeMaker.class.getResource("").toString();

        String temPath = path.substring(path.indexOf(CodeMakerConstant.START_WORD) + 6,
                path.indexOf(CodeMakerConstant.PRO_NAME)) + CodeMakerConstant.TEMPLATE_PATH;

        this.ve = new VelocityEngine();
        Properties p = new Properties();
        p.put(Velocity.FILE_RESOURCE_LOADER_PATH, temPath);
        ve.init(p);

        InitPath.initHandlers(codePath, this);

        return new CodeMaker();
    }

    public void setHandlers(Map<String, Object> vmParam, CodeMakerHandler handler) throws BaseCodeMakerException{
        if(handler != null){
            if(vmParam != null){
                vmParam.forEach((k, v) -> {
                    if(!CodeMakerConstant.TARGET_FILE_NAME.equals(k) &&
                            !CodeMakerConstant.TARGET_FILE_PATH.equals(k) &&
                            !CodeMakerConstant.TARGET_VM_PATH.equals(k)){
                        context.put(k, v);
                        vmParam.remove(k);
                    }
                });
                if(vmParam.size() != 3){
                    throw new BaseCodeMakerException("you must give me the target file name and target file path and target vm path all 3 params!");
                }
                handlers.add(handler);
                filePath.add(vmParam);
                handlerSize++;
            }
        }
    }

    public void make() throws Exception{
        int cursor = getNextCursor();
        handlers.get(cursor).makeCode(ve, context,
                String.valueOf(filePath.get(cursor).get(CodeMakerConstant.TARGET_VM_PATH)),
                String.valueOf(filePath.get(cursor).get(CodeMakerConstant.TARGET_FILE_PATH)),
                String.valueOf(filePath.get(cursor).get(CodeMakerConstant.TARGET_FILE_NAME)));
    }

    private int getNextCursor() throws Exception {
        if(handlers.size() == 0){
            throw new Exception("no handlers");
        }
        return handlerSize++;
    }
}