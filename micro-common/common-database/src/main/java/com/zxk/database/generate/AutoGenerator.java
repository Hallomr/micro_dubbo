package com.zxk.database.generate;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;
import java.util.regex.Matcher;
/**
 * mybatis-plus代码生成
 * */
public class AutoGenerator {
 
    // 数据库连接配置
    private static final String JDBC_URL = "jdbc:mysql://47.106.146.227:3306/tenant?serverTimezone=GMT%2B8&useSSL=false&characterEncoding=UTF-8&allowMultiQueries=true";
    private static final String JDBC_USER_NAME = "root";//数据库名称
    private static final String JDBC_PASSOWRD = "123456";//数据库密码
 
    // 包名和模块名
    private static final String PACKAGE_NAME = "com.zxk";
    private static final String MODULE_NAME = "micro-provider";
 
    // 表名，多个表使用英文逗号分割
    private static final String[] TBL_NAMES = { "t_role"};
    // 表名的前缀，从表生成代码时会去掉前缀
    private static final String TABLE_PREFIX = "t_";
    //项目所在目录
    private static final String PROJECT_PATH = "C:\\Users\\PC1PM99F\\Desktop\\logs";
    //注释的作者
    private static final String AUTHOR = "zxk";
 
 
    // 生成代码入口main方法
    public static void main(String[] args) {
        // 1.数据库配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(JDBC_URL,JDBC_USER_NAME,JDBC_PASSOWRD);
        // 1.1.快速生成器
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);
        // 2.全局配置
        // 覆盖已生成文件
        // 不打开生成文件目录
        // 设置注释的作者
        // 设置注释的日期格式
        // 使用java8新的时间类型
        String path;
        if("".equals(PROJECT_PATH)){
            path="src\\main\\java";
        }else{
            path=PROJECT_PATH.concat(File.separator).concat("src\\main\\java");
        }

        fastAutoGenerator.globalConfig(globalConfigBuilder -> globalConfigBuilder.fileOverride().disableOpenDir()
                .outputDir(path).author(AUTHOR).commentDate("yyyy-MM-dd").dateType(DateType.TIME_PACK));
        // 3.包配置
        // 设置父包名
        // 设置父包模块名
        // 设置MVC下各个模块的包名
        // 设置XML资源文件的目录
        String pack = ObjectUtil.cloneByStream(PACKAGE_NAME).replaceAll("\\.", Matcher.quoteReplacement(File.separator));

        String finalPack = pack;
        fastAutoGenerator.packageConfig(packageConfigBuilder -> packageConfigBuilder.parent(PACKAGE_NAME)
                .moduleName(MODULE_NAME).entity("entity").mapper("mapper").service("service").serviceImpl("service.impl").controller("controller")
                .pathInfo(Collections.singletonMap(OutputFile.mapper.xml, path.concat(File.separator).concat(finalPack).concat(File.separator).concat(MODULE_NAME).concat(File.separator)+"mapper\\xml")));

 
        // 4.模板配置
        // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        fastAutoGenerator.templateEngine(templateEngine);
 
        // 5.注入配置
 
        // 6.策略配置
        // 设置需要生成的表名
        // 设置过滤表前缀
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.enableCapitalMode()
                .enableSkipView().disableSqlFilter().addInclude(TBL_NAMES).addTablePrefix(TABLE_PREFIX));
 
        // 6.1.Entity策略配置
        // 生成实体时生成字段的注解，包括@TableId注解等
        // 数据库表和字段映射到实体的命名策略，为下划线转驼峰
        // 全局主键类型为ASSIGN_ID 雪花算法生成id
        // 实体名称格式化为XXXEntity
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.entityBuilder()
                .enableTableFieldAnnotation().naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel).idType(IdType.ASSIGN_ID));
 
        // 6.2.Controller策略配置
        // 开启生成@RestController控制器
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.controllerBuilder().enableRestStyle());
 
        // 6.3.Service策略配置
        // 格式化service接口和实现类的文件名称，去掉默认的ServiceName前面的I
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.serviceBuilder()
                .formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl"));
 
        // 6.4.Mapper策略配置
        // 格式化 mapper文件名,格式化xml实现类文件名称
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.mapperBuilder()
                .formatMapperFileName("%sMapper").formatXmlFileName("%sMapper"));
 
        // 7.生成代码
        fastAutoGenerator.execute();
    }

}