/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.makers.baseproject.task;

import com.google.common.base.Strings;
import sun.juwin.makers.baseproject.model.BaseCodePath;
import sun.juwin.core.CodeMaker;
import sun.juwin.exception.BaseCodeMakerException;

/**
 * @author sunqinwen
 * @version \: BaseProjectMaker.java,v 0.1 2018-08-08 17:58
 */
public class BaseProjectMaker {

    private BaseProjectMaker() {
    }

    public static BaseProjectMaker build() {
        return new BaseProjectMaker();
    }

    public CodeMaker makeBaseProjectCode(BaseCodePath codePath, String basePath) throws BaseCodeMakerException {
        codePath.setPointModelPath(String.format("%s.model", basePath))
                .setPointMapperPath(String.format("%s.mapper", basePath))
                .setPointServicePath(String.format("%s.service", basePath))
                .setPointServiceImplPath(String.format("%s.service.impl", basePath))
                .setPointControllerPath(String.format("%s.controller", basePath));
        return makeBaseProjectCode(codePath);
    }

    public CodeMaker makeBaseProjectCode(BaseCodePath codePath) throws BaseCodeMakerException {
        if (codePath == null || Strings.isNullOrEmpty(codePath.getBaseProPath())) {
            throw new BaseCodeMakerException("您目标项目路径不可为空！");
        }
        CodeMaker codeMaker = CodeMaker.buildMaker();
        InitHandlers.initHandlers(codePath, codeMaker);
        return codeMaker;
    }

}
