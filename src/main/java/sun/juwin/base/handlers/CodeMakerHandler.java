/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.base.handlers;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author sunqinwen
 * @version \: Handler.java,v 0.1 2018-08-07 17:45 
 *
 */
public abstract class CodeMakerHandler {

    abstract void makeCode(VelocityEngine ve, VelocityContext context, String targetPath, String fileName) throws Exception;

    /**
     * 模板内容推送
     */
    public void setContentToPath(String dirPath, String fileName, String str) throws Exception {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream of = new FileOutputStream(dirPath + "/" + fileName);
        of.write(str.getBytes("UTF-8"));
        of.flush();
        of.close();
    }

}
