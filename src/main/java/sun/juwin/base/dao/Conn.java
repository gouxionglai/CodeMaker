/**
 * Copyright (c) 2009-2017 All Rights Reserved.
 */
package sun.juwin.base.dao;

import sun.juwin.base.model.JdbcModel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Juwin.S
 * @version \: Conn.java,v 0.1 2017-03-01 18:58 
 * 原始产生数据库连接的类
 */
public class Conn {
    private static ThreadLocal<Connection> conn = new ThreadLocal();
    private Conn(){}
    public static Connection getConn(){
        Connection connection = conn.get();
        if(connection == null){
            synchronized (Conn.class){
                if(connection == null){
                    try{
                        Connection realConn = DriverManager.getConnection(JdbcModel.url, JdbcModel.user, JdbcModel.pwd);
                        conn.set(realConn);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return conn.get();
    }
}
