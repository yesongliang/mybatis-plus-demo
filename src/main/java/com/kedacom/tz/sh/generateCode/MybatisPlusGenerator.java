package com.kedacom.tz.sh.generateCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MybatisPlusGenerator {

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		// TODO 设置用户名
		gc.setAuthor("yesongliang");
		gc.setOpen(true);
		// service 命名方式
		gc.setServiceName("%sService");
		// service impl 命名方式
		gc.setServiceImplName("%sServiceImpl");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		// 是否覆盖已有文件
		gc.setFileOverride(false);
		gc.setActiveRecord(true);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// TODO 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/ad-data?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("kedacom");
		dsc.setPassword("kedacom");
		mpg.setDataSource(dsc);

		// TODO 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName(scanner("模块名"));
		pc.setParent("com.kedacom.tz.sh");
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		// 自定义需要填充的字段
		// List<TableFill> tableFillList = new ArrayList<>();
		// 如 每张表都有一个创建时间、修改时间
		// 而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
		// 修改时，修改时间会修改，
		// 虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
		// 使用公共字段填充功能，就可以实现，自动按场景更新了。
		// 如下是配置
		// TableFill createField = new TableFill("gmt_create",
		// FieldFill.INSERT);
		// TableFill modifiedField = new TableFill("gmt_modified",
		// FieldFill.INSERT_UPDATE);
		// tableFillList.add(createField);
		// tableFillList.add(modifiedField);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("queryById", true);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
//		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 设置逻辑删除键
//		strategy.setLogicDeleteFieldName("deleted");
		// TODO 指定生成的bean的数据库表名
		strategy.setInclude("demo_user");
//		strategy.setTablePrefix("demo_");// 此处可以修改为您的表前缀
		// strategy.setSuperEntityColumns("id");
		// 驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
		// 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
//		tc.setEntity("templates/entity.java");
//		tc.setMapper("templates/mapper.java");
//		tc.setXml("templates/mapper.xml");
//		tc.setServiceImpl("templates/service.java");
		tc.setController("templates/controller.java");
		tc.setXml(null);
		mpg.setTemplate(tc);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
		System.out.println("generate successed");
	}

}
