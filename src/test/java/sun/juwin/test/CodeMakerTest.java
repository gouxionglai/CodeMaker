/**
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.test;

import org.junit.Test;
import sun.juwin.core.CoreTask;
import sun.juwin.model.JdbcModel;
import sun.juwin.model.PathModel;

/**
 *
 * @author Juwin.S
 * @version \: Test.java,v 0.1 2018-02-09 18:13 
 * 仅支持SSM类型的maven项目，其次文件是严格按照以下规则来生成：
 * ①model层：实体类层
 * ②dao层：数据回源层
 * ③mapper层：mybatis的配置文件层（有时跟dao层应该是同一个路径）
 * ④service层：业务接口层
 * ⑤serviceImpl层：业务实现层
 * ⑥controller层：控制层（接口层）
 */
public class CodeMakerTest {

    @Test
    public void test() throws Exception{

        /***
         * 以下定义好自己的数据库url、数据库用户名、数据库密码
         */
        JdbcModel.url = "jdbc:mysql://xxx.xx.xx.xxx:3306/数据库名";
        JdbcModel.user = "xxxxx";
        JdbcModel.pwd = "xxxxx";
        JdbcModel.schema = "数据库名";

        CoreTask coreTask = new CoreTask();
        /***
         * 由于每个项目的命名规则不一致，因此各层级结构的路径允许自定义
         */
        coreTask.setPointPath(new PathModel()
                .setControllerPath("sun.juwin.codemaker.controller")
                .setServicePath("sun.juwin.codemaker.service")
                .setServiceImplPath("sun.juwin.codemaker.service.impl")
                .setDaoPath("sun.juwin.codemaker.dao")
                .setMapperPath("sun.juwin.codemaker.dao")
                .setModelPath("sun.juwin.codemaker.po"));
        /***
         * 执行生成文件的方法
         * 参数1：最终生成文件所存放的项目路径，maven项目就是生成在main.java下面
         * 参数2：数据库表名
         * 参数3：该数据库表生成后期望的实体类命名
         */
        coreTask.coreTask("E:\\new_api\\Thread-Lock\\src\\main\\java", "r_record_season", "RecordSeason");
    }

}
