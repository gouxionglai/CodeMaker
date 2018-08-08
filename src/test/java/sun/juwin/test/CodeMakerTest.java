/**
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.test;

import org.junit.Test;
import sun.juwin.baseproject.BaseCodePath;
import sun.juwin.core.CodeMaker;

/**
 * @author Juwin.S
 * @version \: Test.java,v 0.1 2018-02-09 18:13
 */
public class CodeMakerTest {

    @Test
    public void test() throws Exception {
        BaseCodePath codePath = new BaseCodePath(
                "jdbc:mysql://127.0.0.1:3306/dogvane?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull",
                "dogvane",
                "123",
                "dogvane")
                .setBaseProPath("E:/gggggg")
                .setTableName("t_reply")
                .setClassName("Reply")
                .setPointModelPath("sun.juwin.model")
                .setPointMapperPath("sun.juwin.mapper")
                .setPointServicePath("sun.juwin.service")
                .setPointServiceImplPath("sun.juwin.service.impl")
                .setPointControllerPath("sun.juwin.controller");

        CodeMaker.buildMaker().makeBaseProjectCode(codePath).make();
    }

}
