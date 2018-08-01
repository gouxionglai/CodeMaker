/**
 * Copyright (c) 2009-2017 All Rights Reserved.
 */
package sun.juwin.db;

import sun.juwin.model.JdbcModel;
import sun.juwin.model.TableModel;
import sun.juwin.model.TaskModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author Juwin.S
 * @version \: getTable.java,v 0.1 2017-03-01 19:06 
 *
 */
public class Table {

    //mysql类型和java类型映射
    private static Map<String, String> mysqlJavaTypeMap = new HashMap<String, String>();

    //类与包关系映射
    private static Map<String, String> classPackageMap = new HashMap<String, String>();

    static {
        mysqlJavaTypeMap.put("varchar", "String");
        mysqlJavaTypeMap.put("tinyint", "Integer");
        mysqlJavaTypeMap.put("int", "Integer");
        mysqlJavaTypeMap.put("timestamp", "LocalDateTime");
        mysqlJavaTypeMap.put("date", "LocalDate");
        mysqlJavaTypeMap.put("time", "LocalTime");
        mysqlJavaTypeMap.put("float", "Float");
        mysqlJavaTypeMap.put("double", "Double");
        mysqlJavaTypeMap.put("bigint", "Long");
        mysqlJavaTypeMap.put("smallint", "Integer");
        mysqlJavaTypeMap.put("mediumint", "Integer");
    }

    static {
        classPackageMap.put("LocalDate", "java.time.LocalDate");
        classPackageMap.put("LocalTime", "java.time.LocalTime");
        classPackageMap.put("LocalDateTime", "java.time.LocalDateTime");
    }

    private Connection conn = Conn.getConn();

    /**
     * 用来获取对应数据库表的类型、字段名
     * @return
     */
    public List<TableModel> getAllProperty(String tableName) throws Exception{
        List<TableModel> tableModels = new ArrayList<TableModel>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select `column_name`, data_type, column_comment from information_schema.columns where `table_name` = '"+tableName+"' AND TABLE_SCHEMA = '"+JdbcModel.schema +"'");
        while (resultSet.next()){
            TableModel tableModel = new TableModel();
            String pro = resultSet.getString("column_name");
            String type = resultSet.getString("data_type");
            String note = resultSet.getString("column_comment");
            String javaType = mysqlJavaTypeMap.get(type);
            if(javaType == null){
                System.out.println("程序终止-存在无法映射的mysql类型！");
                return null;
            }
            tableModel.setJavaType(javaType);
            tableModel.setImportPackages(classPackageMap.get(tableModel.getJavaType()));
            tableModel.setLowerCase(pro);
            tableModel.setCapitalCase(Table.capitalStr(pro));
            tableModel.setNote(note);
            tableModels.add(tableModel);
        }
        conn.close();
        return tableModels;
    }

    public TaskModel getTaskModel(String tableName){
        try{
            TaskModel taskModel = new TaskModel();
            List<TableModel> models = this.getAllProperty(tableName);
            taskModel.setModels(models);
            Set<String> packages = new HashSet<String>();
            for(TableModel model : models){
                if(model.getImportPackages() != null){
                    packages.add(model.getImportPackages());
                }
            }
            taskModel.setPackages(packages);
            return taskModel;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("生成对象过程中出现迷之错误，请定位问题~");
            return null;
        }
    }

    public static String capitalStr(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }
}
