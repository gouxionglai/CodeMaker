/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.makers.baseproject.task;

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
        if (codePath == null) {
            throw new BaseCodeMakerException("base code path can not be null !");
        }
        codePath.setPointModelPath(String.format("%s.model", basePath))
                .setPointMapperPath(String.format("%s.mapper", basePath))
                .setPointServicePath(String.format("%s.service", basePath))
                .setPointServiceImplPath(String.format("%s.service.impl", basePath))
                .setPointControllerPath(String.format("%s.controller", basePath));
        return makeBaseProjectCode(codePath);
    }

    public CodeMaker makeBaseProjectCode(BaseCodePath codePath) throws BaseCodeMakerException {
        if (codePath == null) {
            throw new BaseCodeMakerException("base code path can not be null !");
        }
        if (!codePath.check()) {
            throw new BaseCodeMakerException("table_name, class_name, base_pro_path can not be null !");
        }
        CodeMaker codeMaker = CodeMaker.buildMaker();
        InitHandlers.initHandlers(codePath, codeMaker);
        return codeMaker;
    }

}
