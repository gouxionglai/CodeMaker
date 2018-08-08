/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.core.handlers;

import sun.juwin.core.CodeMaker;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author sunqinwen
 * @version \: Handler.java,v 0.1 2018-08-07 17:45
 */
public abstract class CodeMakerHandler {

    public abstract void makeCode(CodeMaker codeMaker) throws Exception;

    /**
     * 模板内容推送
     */
    public void setContentToPath(String dirPath, String fileName, String str) throws Exception {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream of = new FileOutputStream(String.format("%s/%s", dirPath, fileName));
        of.write(str.getBytes("UTF-8"));
        of.flush();
        of.close();
    }

}
