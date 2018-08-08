/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.core.handlers;

import org.apache.velocity.Template;
import sun.juwin.constant.CodeMakerConstant;
import sun.juwin.core.CodeMaker;

import java.io.StringWriter;

/**
 * @author sunqinwen
 * @version \: ModelHandler.java,v 0.1 2018-08-07 17:44
 */
public class BaseHandler extends CodeMakerHandler {

    @Override
    public void makeCode(CodeMaker codeMaker) throws Exception {
        int cursor = codeMaker.getCursor() - 1;
        if (codeMaker.getHandlerSize() > codeMaker.getCursor()) {
            codeMaker.getNextCodeMakerHandler().makeCode(codeMaker);
        }
        Template modelVm = codeMaker.getVe().getTemplate(
                String.valueOf(codeMaker.getFilePath().get(cursor).get(CodeMakerConstant.TARGET_VM_PATH)), "utf-8");
        StringWriter modelStr = new StringWriter();
        modelVm.merge(codeMaker.getContext(), modelStr);
        String targetPath = String.valueOf(codeMaker.getFilePath().get(cursor).get(CodeMakerConstant.TARGET_FILE_PATH));
        String fileName = String.valueOf(codeMaker.getFilePath().get(cursor).get(CodeMakerConstant.TARGET_FILE_NAME));
        System.out.println(String.format("%s ----------> %s", fileName, targetPath));
        setContentToPath(targetPath, fileName, modelStr.toString());
    }
}
