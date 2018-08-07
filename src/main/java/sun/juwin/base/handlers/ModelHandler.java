/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.base.handlers;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 *
 * @author sunqinwen
 * @version \: ModelHandler.java,v 0.1 2018-08-07 17:44 
 *
 */
public class ModelHandler extends CodeMakerHandler {

    @Override
    public void makeCode(VelocityEngine ve, VelocityContext context, String targetPath, String fileName) throws Exception {
        //引入model层模板
        Template modelVm = ve.getTemplate("base/model.vm", "utf-8");
        StringWriter modelStr = new StringWriter();
        modelVm.merge(context, modelStr);
        System.out.println(String.format("%s%s----------> 已生成！", targetPath, fileName));
        setContentToPath(targetPath, fileName, modelStr.toString());
    }
}
