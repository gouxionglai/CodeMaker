/**
 * Copyright (c) 2009-2017 All Rights Reserved.
 */
package sun.juwin.base.handler;

import com.google.common.base.Strings;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import sun.juwin.base.db.Table;
import sun.juwin.base.model.PathModel;
import sun.juwin.base.model.TaskModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.Properties;

/**
 *
 * @author Juwin.S
 * @version \: CoreTask.java,v 0.1 2017-03-01 18:04 
 * 处理中心
 */
public class BaseHandler {

    public static final String TEMPLATE_PATH = "CodeMaker/src/main/resources";
    public static final String START_WORD = "file:/";
    public static final String PRO_NAME = "CodeMaker";

    private String pointModelPath;
    private String pointMapperPath;
    private String pointServicePath;
    private String pointServiceImplPath;
    private String pointControllerPath;

    private String targetModelPath;
    private String targetMapperPath;
    private String targetServicePath;
    private String targetServiceImplPath;
    private String targetControllerPath;

    public void baseHandler(String baseProPath, String tableName, String className) throws Exception {
        String path = BaseHandler.class.getResource("").toString();
        //取得模板根目录
        String temPath = path.substring(path.indexOf(START_WORD) + 6, path.indexOf(PRO_NAME)) + TEMPLATE_PATH;
        //Velocity引擎及初始化相关
        VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        p.put(Velocity.FILE_RESOURCE_LOADER_PATH, temPath);
        ve.init(p);
        //引入model层模板
        Template modelVm = ve.getTemplate("model.vm", "utf-8");
        //引入dao层相关的模板
        Template mapperVm = ve.getTemplate("mapper.vm", "utf-8");
        Template mapperXmlVm = ve.getTemplate("mapper-xml.vm", "utf-8");
        //引入service层相关模板
        Template serviceVm = ve.getTemplate("service.vm", "utf-8");
        Template serviceImplVm = ve.getTemplate("service-impl.vm", "utf-8");
        //引入controller层模板
        Template controllerVm = ve.getTemplate("controller.vm", "utf-8");
        VelocityContext context = new VelocityContext();
        Table table = new Table();
        TaskModel model = table.getTaskModel(tableName);
        if (model != null && model.getModels() != null && model.getModels().size() > 0) {
            //初始化一些必要的路径
            setTargetPath(baseProPath);
            context.put("model", model);
            context.put("className", className);
            context.put("modelPath", pointModelPath);
            context.put("mapperPath", pointMapperPath);
            context.put("servicePath", pointServicePath);
            context.put("serviceImplPath", pointServiceImplPath);
            context.put("controllerPath", pointControllerPath);
            context.put("tableName", tableName);
            context.put("lowerClassName", className.substring(0, 1).toLowerCase() + className.substring(1));

            if (!Strings.isNullOrEmpty(targetModelPath)) {
                StringWriter modelStr = new StringWriter();
                modelVm.merge(context, modelStr);
                System.out.println(targetModelPath + "/" + className + ".java");
                setContentToPath(targetModelPath, className + ".java", modelStr.toString());
            }

            if (!Strings.isNullOrEmpty(targetMapperPath)) {
                StringWriter mapperStr = new StringWriter();
                mapperVm.merge(context, mapperStr);
                System.out.println(targetMapperPath + "/" + className + "Mapper.java");
                setContentToPath(targetMapperPath, className + "Mapper.java", mapperStr.toString());
            }

            if (!Strings.isNullOrEmpty(targetMapperPath)) {
                StringWriter mapperXmlStr = new StringWriter();
                mapperXmlVm.merge(context, mapperXmlStr);
                System.out.println(targetMapperPath + "/" + className + "Mapper.xml");
                setContentToPath(targetMapperPath, className + "Mapper.xml", mapperXmlStr.toString());
            }

            if (!Strings.isNullOrEmpty(targetServicePath)) {
                StringWriter serviceStr = new StringWriter();
                serviceVm.merge(context, serviceStr);
                System.out.println(targetServicePath + "/" + className + "Service.java");
                setContentToPath(targetServicePath, className + "Service.java", serviceStr.toString());
            }

            if (!Strings.isNullOrEmpty(targetServiceImplPath)) {
                StringWriter serviceImplStr = new StringWriter();
                serviceImplVm.merge(context, serviceImplStr);
                System.out.println(targetServiceImplPath + "/" + className + "ServiceImpl.java");
                setContentToPath(targetServiceImplPath, className + "ServiceImpl.java", serviceImplStr.toString());
            }

            if (!Strings.isNullOrEmpty(targetControllerPath)) {
                StringWriter controllerStr = new StringWriter();
                controllerVm.merge(context, controllerStr);
                System.out.println(targetControllerPath + "/" + className + "Controller.java");
                setContentToPath(targetControllerPath, className + "Controller.java", controllerStr.toString());
            }
        }
    }

    /**
     * 生成java路径
     */
    public void setPointPath(PathModel pointPath) {
        pointModelPath = pointPath.getModelPath();
        pointMapperPath = pointPath.getMapperPath();
        pointServicePath = pointPath.getServicePath();
        pointServiceImplPath = pointPath.getServiceImplPath();
        pointControllerPath = pointPath.getControllerPath();
    }

    /**
     * 生成最终项目文件的去向
     */
    private void setTargetPath(String baseProPath) {
        if(!Strings.isNullOrEmpty(pointModelPath)){
            targetModelPath = baseProPath + "/" + pointModelPath.replace(".", "/");
        }
        if(!Strings.isNullOrEmpty(pointMapperPath)){
            targetMapperPath = baseProPath + "/" + pointMapperPath.replace(".", "/");
        }
        if(!Strings.isNullOrEmpty(pointServicePath)){
            targetServicePath = baseProPath + "/" + pointServicePath.replace(".", "/");
        }
        if(!Strings.isNullOrEmpty(pointServiceImplPath)){
            targetServiceImplPath = baseProPath + "/" + pointServiceImplPath.replace(".", "/");
        }
        if(!Strings.isNullOrEmpty(pointControllerPath)){
            targetControllerPath = baseProPath + "/" + pointControllerPath.replace(".", "/");
        }
    }

    /**
     * 模板内容推送
     */
    private void setContentToPath(String dirPath, String fileName, String str) throws Exception {
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
