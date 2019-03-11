# CodeMaker
>仅能生成SSM项目的controller、service、dao（mapper和xml）、po层基本增删改查，特别局限，不过如果你的项目恰好是基于SSM框架开发的，那么这个小工具用起来应该还是有点小爽的~

### CodeMaker做到的事情
可以根据你设计好的数据库表结构生成对应的对象，以及操作该对象的控制层、业务层、持久层的代码，算是节省了一些开发时间。

### 使用
从完整的流程说起：<br/>
1.数据库表结构设计，现在假如开发一个新模块Student，表设计如下：
```sql
CREATE TABLE `t_student` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id' ,
`name`  varchar(16) NOT NULL DEFAULT '' COMMENT '名字' ,
`sex`  tinyint(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别：1男，0女' ,
`address`  varchar(150) NOT NULL DEFAULT '' COMMENT '联系地址' ,
`email`  varchar(25) NOT NULL DEFAULT '' COMMENT '邮箱' ,
`age`  tinyint(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '年龄' ,
`ctime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
`mtime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`)
) COMMENT='学生信息表';
```
2.然后利用CodeMaker为`t_student`表创建简单的CRUD代码
<br/>打开CodeMaker，找到`CodeMakerTest`测试类，代码示例：
```java
@Test
    public void test() throws Exception {
        BaseCodePath codePath = new BaseCodePath(
                "jdbc:mysql://127.0.0.1:3306/test", //连接
                "root", //用户名
                "123", //密码
                "test") //这里填库名
                .setBaseProPath("D:\\CodeMaker\\src\\main\\java") //这里是你要生成代码的目标项目的路径，这里为了方便演示就在当前项目里了
                .setTableName("t_student") //这里放表名
                .setClassName("Student") //生成的model的类名（下面的各层类命名也会沿用这个）
                .setPointModelPath("sun.juwin.newcode.model") //model层的路径
                .setPointMapperPath("sun.juwin.newcode.mapper") //mapper层的路径
                .setPointServicePath("sun.juwin.newcode.service") //业务层的路径
                .setPointServiceImplPath("sun.juwin.newcode.service.impl") //业务实现层的路径
                .setPointControllerPath("sun.juwin.newcode.controller"); //控制层的路径

        BaseProjectMaker.build().makeBaseProjectCode(codePath).make();
    }
```
点击运行即可，以上述代码为例，运行成功后打印和输出文件如下：
![blockchain](https://raw.githubusercontent.com/exceting/CodeMaker/master/src/main/resources/readme/1.jpg "测试用例")

### 运行效果
##### model层代码：
```java
/**
* Copyright (c) 2009-2017 All Rights Reserved.
*/
package sun.juwin.newcode.model;

import java.time.LocalDateTime;

/**
* @author CodeMaker
* @version $Id: Student.java,v 0.1 2017-02-13 下午5:55
*          CodeMaker Exp $$
*/
public class Student {

    /** 主键id*/
    private Integer id;
    /** 名字*/
    private String name;
    /** 性别：1男，0女*/
    private Integer sex;
    /** 联系地址*/
    private String address;
    /** 邮箱*/
    private String email;
    /** 年龄*/
    private Integer age;
    /** 添加时间*/
    private LocalDateTime ctime;
    /** 更新时间*/
    private LocalDateTime mtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public LocalDateTime getMtime() {
        return mtime;
    }

    public void setMtime(LocalDateTime mtime) {
        this.mtime = mtime;
    }

}
```
会发现，建表语句里的注释成了代码里字段的注释，命名、类型也对应上了。
##### 持久层代码：
mapper接口：
```java
/**
* Copyright (c) 2009-2017 All Rights Reserved.
*/
package sun.juwin.newcode.mapper;

import sun.juwin.newcode.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author CodeMaker
* @version $Id: StudentMapper.java,v 0.1 2017-02-13 下午5:55
*          CodeMaker Exp $$
*/
@Mapper
public interface StudentMapper {

    void insert(@Param("pojo") Student pojo);

    void update(@Param("pojo") Student pojo);

    Student one(@Param("id") Integer id);

}
```
mapper XML：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="sun.juwin.newcode.mapper.StudentMapper">

    <insert id="insert" parameterType="sun.juwin.newcode.model.Student" useGeneratedKeys="true"
            keyProperty="pojo.id">
        INSERT INTO t_student
        <trim prefix="(" suffix=")" suffixOverrides=",">
                    <if test="pojo.id != null">id,</if>
                    <if test="pojo.name != null">name,</if>
                    <if test="pojo.sex != null">sex,</if>
                    <if test="pojo.address != null">address,</if>
                    <if test="pojo.email != null">email,</if>
                    <if test="pojo.age != null">age,</if>
                    <if test="pojo.ctime != null">ctime,</if>
                    <if test="pojo.mtime != null">mtime,</if>
                </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
                    <if test="pojo.id != null">#{pojo.id},</if>
                    <if test="pojo.name != null">#{pojo.name},</if>
                    <if test="pojo.sex != null">#{pojo.sex},</if>
                    <if test="pojo.address != null">#{pojo.address},</if>
                    <if test="pojo.email != null">#{pojo.email},</if>
                    <if test="pojo.age != null">#{pojo.age},</if>
                    <if test="pojo.ctime != null">#{pojo.ctime},</if>
                    <if test="pojo.mtime != null">#{pojo.mtime},</if>
                </trim>
    </insert>

    <update id="update" parameterType="sun.juwin.newcode.model.Student">
        UPDATE t_student
        <trim prefix="set" suffixOverrides=",">
                    <if test="pojo.id != null">id = #{pojo.id},</if>
                    <if test="pojo.name != null">name = #{pojo.name},</if>
                    <if test="pojo.sex != null">sex = #{pojo.sex},</if>
                    <if test="pojo.address != null">address = #{pojo.address},</if>
                    <if test="pojo.email != null">email = #{pojo.email},</if>
                    <if test="pojo.age != null">age = #{pojo.age},</if>
                    <if test="pojo.ctime != null">ctime = #{pojo.ctime},</if>
                    <if test="pojo.mtime != null">mtime = #{pojo.mtime},</if>
                </trim>
        WHERE id = #{pojo.id,jdbcType=INTEGER}
    </update>

    <select id="one" resultType="sun.juwin.newcode.model.Student">
        SELECT
        *
        FROM
        t_student
        WHERE id = #{id}
    </select>

</mapper>
```
##### 业务层代码：
接口：
```java
/**
* Copyright (c) 2009-2017 All Rights Reserved.
*/
package sun.juwin.newcode.service;

import sun.juwin.newcode.model.Student;

/**
* @author CodeMaker
* @version $Id: StudentService.java,v 0.1 2017-02-13 下午5:55
*          CodeMaker Exp $$
*/
public interface StudentService {

    Integer add(Student student);

    Student one(int studentId);

    void update(Student student);

}
```
实现：
```java
/**
* Copyright (c) 2009-2017 All Rights Reserved.
*/
package sun.juwin.newcode.service.impl;

import sun.juwin.newcode.model.Student;
import sun.juwin.newcode.mapper.StudentMapper;
import sun.juwin.newcode.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author CodeMaker
* @version $Id: StudentService.java,v 0.1 2017-02-13 下午5:55
*          CodeMaker Exp $$
*/
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Integer add(Student student){
        this.studentMapper.insert(student);
        return student.getId();
    }

    @Override
    public void update(Student student){
        this.studentMapper.update(student);
    }

    @Override
    public Student one(int studentId){
        return this.studentMapper.one(studentId);
    }

}
```
##### 控制层代码：
```java
/**
* Copyright (c) 2009-2017 All Rights Reserved.
*/
package sun.juwin.newcode.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import sun.juwin.newcode.model.Student;
import sun.juwin.newcode.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
* @author CodeMaker
* @version \: StudentController.java,v 0.1 2017-02-16 13:37
*          用来存放student相关的后台接口
*/
@RestController
@RequestMapping("/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private StudentService studentService;
    @RequestMapping("add")
    public String addStudent(@RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "sex", required = false) Integer sex,
                                        @RequestParam(value = "address", required = false) String address,
                                        @RequestParam(value = "email", required = false) String email,
                                        @RequestParam(value = "age", required = false) Integer age,
                                        @RequestParam(value = "ctime", required = false) LocalDateTime ctime,
                                        @RequestParam(value = "mtime", required = false) LocalDateTime mtime
                                            ) {
        try {
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setSex(sex);
            student.setAddress(address);
            student.setEmail(email);
            student.setAge(age);
            student.setCtime(ctime);
            student.setMtime(mtime);
            this.studentService.add(student);
            return "添加成功！";
        } catch (Exception e) {
            logger.error("add student error !", e);
            return "添加失败！";
        }
    }

    @RequestMapping("update")
    public String updateStudent(@RequestParam(value = "id", required = false) Integer id,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "sex", required = false) Integer sex,
                                            @RequestParam(value = "address", required = false) String address,
                                            @RequestParam(value = "email", required = false) String email,
                                            @RequestParam(value = "age", required = false) Integer age,
                                            @RequestParam(value = "ctime", required = false) LocalDateTime ctime,
                                            @RequestParam(value = "mtime", required = false) LocalDateTime mtime
                                                ) {
        try {
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setSex(sex);
            student.setAddress(address);
            student.setEmail(email);
            student.setAge(age);
            student.setCtime(ctime);
            student.setMtime(mtime);
            this.studentService.update(student);
            return "修改成功！";
        } catch (Exception e) {
            logger.error("update student error !", e);
            return "修改失败！";
        }
    }

    @RequestMapping("one")
    public Student getOne(@RequestParam(value = "id")Integer id){
        return this.studentService.one(id);
    }
}
```

最后说下，模块的生成与否都是可以通过参数控制的，比如不想生成控制层的代码，那么只需要将
```java
.setPointControllerPath("sun.juwin.newcode.controller"); //控制层的路径
```
这段代码去掉即可，如果有什么定制化的要求，可以通过修改`resources`下面的模板资源来完成定制。
