/**
 * Copyright (c) 2009-2018 All Rights Reserved.
 */
package sun.juwin.test;

import org.junit.Test;

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

    }

}
