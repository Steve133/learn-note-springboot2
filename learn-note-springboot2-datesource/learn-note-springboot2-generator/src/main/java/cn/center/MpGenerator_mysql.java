package cn.center;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import cn.hutool.core.util.StrUtil;

/**
 * @author song
 * @title mp自动生成代码工具
 * @projectName demo
 * @description TODO
 * @date 2019年11月28日 下午2:11:37
 */
public class MpGenerator_mysql {

	private static String project = "/learn-note-springboot2-mysql-mybatisplus-druid-page-single-v2";//项目名称
	private static final String OUT_PATH = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\")) + project;
	private static final String AUTHOR = "cjs";//作者
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/multipledatasource1?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
	private static final String DATA_BASE = "mall";//数据库
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private static final DbType dbType = DbType.MYSQL;//数据类型---如果是oracle 要打开全局大写
	private static final String MODULE_NAME = null;//.Java文件生成的路径后加 模块名
	private static final String PACKAGE_NAME = "cn.center";//.Java文件生成的路径

	public static void main(String[] args) {

		
		// 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("created", FieldFill.INSERT); 
        TableFill modifiedField = new TableFill("updated", FieldFill.INSERT_UPDATE); 
        tableFillList.add(createField);
        tableFillList.add(modifiedField);
        
        
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig()
			.setDbType(dbType)// 数据库类型
			.setTypeConvert(new MySqlTypeConvert() {
				// 自定义数据库表字段类型转换【可选】
				@Override
				public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
					//System.out.println("转换类型：" + fieldType);
					//if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
						//return DbColumnType.BOOLEAN;
					//}
					return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
				}
			})
			.setDriverName(DRIVER)
			.setUsername(USER_NAME)
			.setPassword(PASSWORD)
			.setUrl(URL);
		
		
		AutoGenerator mpg = new AutoGenerator()
			.setTemplateEngine(new FreemarkerTemplateEngine())
			.setGlobalConfig(
					new GlobalConfig()
					.setOutputDir(OUT_PATH+"/src/main/java")// 输出目录
					.setFileOverride(true)// 是否覆盖已有文件，默认是false
					.setIdType(IdType.NONE)// 主键策略
					.setSwagger2(true)
					.setActiveRecord(false)// 不需要 ActiveRecord 特性的请改为false
					.setEnableCache(false)// Mapper.xml 二级缓存
					.setBaseResultMap(true)// Mapper.xml 生成 ResultMap
					.setBaseColumnList(true)// Mapper.xml 生成 columList
					.setAuthor(AUTHOR)
					/** 自定义文件命名，注意 %s 会自动填充表实体属性！ **/
					.setXmlName("%sMapper")
					.setMapperName("%sMapper")
					.setServiceName("%sService")
					.setServiceImplName("%sServiceImpl")
					.setControllerName("%sController")
					.setOpen(false)// 文件生成完成 不自动打开文件夹
			)
			.setDataSource(
					dsc
			)
			.setStrategy(
					// 策略配置
					new StrategyConfig()
					//.setCapitalMode(true)// 全局大写命名
					//.setDbColumnUnderline(true)// 全局下划线命名
					.setTablePrefix(new String[]{""})//此处可以修改为您的表前缀
					.setNaming(NamingStrategy.underline_to_camel)//表名生成策略 下滑线转驼峰
					//.setColumnNaming(NamingStrategy.underline_to_camel)
					.setInclude(new String[] { "cs_test" }) // 需要生成的表	1:new String[] { "tb_shop", "tb_employee" }	2:getTables(dsc)	3:scanner("表名")
					//.setExclude(new String[]{"test"}) // 排除生成的表
					//.setTableFillList(tableFillList)
					//自定义实体，公共字段
					//.setSuperEntityColumns(new String[]{"id"})
					//自定义实体父类
					//.setSuperEntityClass(PACKAGE_NAME+".pojo.BaseEntity")
					//自定义 mapper 父类
					//.setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")
					//自定义 service 父类
					//.setSuperServiceClass("com.baomidou.demo.base.BsBaseService")
					//自定义 service 实现类父类
					//.setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl")
					//自定义 controller 父类
					//.setSuperControllerClass("com.baomidou.demo.TestController")
					//实体】是否生成字段常量（默认 false）
					//public static final String ID = "test_id";
					//.setEntityColumnConstant(true)
					//【实体】是否为构建者模型（默认 false）
					//public User setName(String name) {this.name = name; return this;}
					//.setEntityBuilderModel(true)
					//【实体】是否为lombok模型（默认 false）
					.setEntityLombokModel(true)
					//Boolean类型字段是否移除is前缀处理
					.setEntityBooleanColumnRemoveIsPrefix(true)
					// controller以restFule风格
					.setRestControllerStyle(true)
					.setControllerMappingHyphenStyle(true)
			)
			.setPackageInfo(
					new PackageConfig()
					.setModuleName(MODULE_NAME)//包路径后加入模块名字
					.setParent(PACKAGE_NAME)// 自定义包路径
					.setController("controller")// 这里是控制器包名，默认 web
					.setEntity("pojo")
					.setMapper("mapper")
					.setXml("mapper")
					.setService("service")
					.setServiceImpl("service.impl")
			)
			.setCfg(
					// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
					new InjectionConfig() {
						@Override
						public void initMap() {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
							this.setMap(map);
						}
					}
					//将mapper文件重写到指定目录下
//					.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.ftl") {
//						@Override
//						public String outputFile(TableInfo tableInfo) {
//							// 自定义输入文件名称
//							if (!StringUtils.isEmpty(MODULE_NAME)) {
//								return OUT_PATH + "/src/main/resources/mapper/" + MODULE_NAME + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//							} else {
//								return OUT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//							}
//						}
//					}))
			)
			.setTemplate(
					//可以控制 /src/main/java 下文件是否输出
					new TemplateConfig()
					//.setXml(null)
					//.setController(null)
					//.setEntity(null)
					//.setMapper(null)
					//.setService(null)
					//.setServiceImpl(null)
			);
		mpg.execute();
	}

	/**
	 * @description: 读取控制台内容
	 * @param tip
	 * @return
	 * @author song
	 * @date 2019年11月28日 下午2:21:37
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * @description: 获取所有表
	 * @param dsc
	 * @return
	 * @author song
	 * @date 2019年11月28日 下午3:13:04
	 */
	private static String[] getTables(DataSourceConfig dsc) {
		Connection conn = dsc.getConn();
		StringBuffer tables = new StringBuffer();
		if (conn != null) {
			try {
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery("select table_name from information_schema.`TABLES` where table_schema='"+DATA_BASE+"'");
				while (rs.next()) {
					String tableName = rs.getString(1);
					tables.append(",").append(tableName);
				}
				String[] tableArray = StrUtil.split(tables.toString().substring(1, tables.length()), ",");
				rs.close();
				stat.close();
				return tableArray;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return new String[0];
	}
}