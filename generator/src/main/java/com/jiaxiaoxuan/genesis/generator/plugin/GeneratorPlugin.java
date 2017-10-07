package com.jiaxiaoxuan.genesis.generator.plugin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorPlugin extends PluginAdapter {
	private String mapperPackage;
	private String modelPackage;
	private String servicePath;
	private String servicePackage;
	private String controllerPath;
	private String controllerPackage;
	private String tableName;

	public GeneratorPlugin() {
		super();
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	public String tf(String tableName) {
		if (tableName.indexOf("_") != -1) {
			String[] names = tableName.split("_");
			String start = names[0];
			for (int i = 1; i < names.length; i++) {
				start += names[i].substring(0, 1).toUpperCase()+names[i].substring(1, names[i].length());
			}
			return start;
		}
		return tableName;
	}

	public void initialized(IntrospectedTable introspectedTable) {
		tableName = tf(introspectedTable.getFullyQualifiedTableNameAtRuntime());
		modelPackage = properties.getProperty("modelPackage");
		servicePath = properties.getProperty("servicePath");
		servicePackage = properties.getProperty("servicePackage");
		controllerPath = properties.getProperty("controllerPath");
		controllerPackage = properties.getProperty("controllerPackage");
		mapperPackage = properties.getProperty("mapperPackage");
	}

	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		try {
			// 创建Service目录
			File serviceFilePath = new File(servicePath + "/" + servicePackage.replace(".", "/"));
			if (!serviceFilePath.isDirectory()) {
				serviceFilePath.mkdirs();
			}
			File serviceImplFilePath = new File(servicePath + "/" + servicePackage.replace(".", "/") + "/impl");
			if (!serviceImplFilePath.isDirectory()) {
				serviceImplFilePath.mkdirs();
			}
			// 创建controller目录
			File controllerFilePath = new File(controllerPath + "/" + controllerPackage.replace(".", "/"));
			if (!controllerFilePath.isDirectory()) {
				controllerFilePath.mkdirs();
			}

			// 所有属性集合
			List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
			String keyName = introspectedTable.getGeneratedKey().getColumn();
			String keyType = "Integer";
			for (IntrospectedColumn c : columns) {
				if (c.getActualColumnName().equals(keyName)) {
					String keyJavaType = c.getFullyQualifiedJavaType().getFullyQualifiedName();
					keyType = keyJavaType.substring(keyJavaType.lastIndexOf(".") + 1, keyJavaType.length());
				}
			}

			// 将模版复制到临时目录中
			String tempFolderPath = System.getProperty("java.io.tmpdir") + "/ftl";
			copyFtl(tempFolderPath);

			// 模版配置
			Configuration config = new Configuration(Configuration.VERSION_2_3_23);
			config.setDirectoryForTemplateLoading(new File(tempFolderPath));
			config.setDefaultEncoding("UTF-8");
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			// 生成Service接口类
			Template serviceTemplate = config.getTemplate("service.ftl", "UTF-8");
			Map<String, Object> serviceMap = new HashMap<String, Object>();
			serviceMap.put("modelPackage", modelPackage);
			serviceMap.put("package", servicePackage);
			serviceMap.put("tableName", tableName);

			String className = tableName.replaceFirst(tableName.substring(0, 1),
					tableName.substring(0, 1).toUpperCase()) + "Service";

			File file = new File(serviceFilePath + "/" + className + ".java");
			if (!file.isFile()) {
				file.createNewFile();
				Writer out = new BufferedWriter(new FileWriter(file));
				serviceTemplate.process(serviceMap, out);
				out.flush();
				out.close();
			}

			// 生成Service接口实现类
			Template serviceImplTemplate = config.getTemplate("serviceImpl.ftl", "UTF-8");
			Map<String, Object> serviceImplMap = new HashMap<String, Object>();
			serviceImplMap.put("modelPackage", modelPackage);
			serviceImplMap.put("package", servicePackage);
			serviceImplMap.put("tableName", tableName);
			serviceImplMap.put("mapperPackage", mapperPackage);
			serviceImplMap.put("columns", columns);
			serviceImplMap.put("keyType", keyType);

			String ImplclassName = tableName.replaceFirst(tableName.substring(0, 1),
					tableName.substring(0, 1).toUpperCase()) + "ServiceImpl";

			File Implfile = new File(serviceFilePath + "/impl/" + ImplclassName + ".java");
			if (!Implfile.isFile()) {
				Implfile.createNewFile();
				Writer out = new OutputStreamWriter(new FileOutputStream(Implfile), "UTF-8");
				serviceImplTemplate.process(serviceImplMap, out);
				out.flush();
			}

			// 生成controller类
			Template controllerTemplate = config.getTemplate("controller.ftl", "UTF-8");
			Map<String, Object> controllerMap = new HashMap<String, Object>();
			controllerMap.put("modelPackage", modelPackage);
			controllerMap.put("servicePackage", servicePackage);
			controllerMap.put("package", controllerPackage);
			controllerMap.put("tableName", tableName);
			controllerMap.put("mapperPackage", mapperPackage);
			controllerMap.put("keyType", keyType);

			String controllerclassName = tableName.replaceFirst(tableName.substring(0, 1),
					tableName.substring(0, 1).toUpperCase()) + "Controller";

			File controllerfile = new File(controllerFilePath + "/" + controllerclassName + ".java");
			if (!controllerfile.isFile()) {
				controllerfile.createNewFile();
				Writer out = new OutputStreamWriter(new FileOutputStream(controllerfile), "UTF-8");
				controllerTemplate.process(controllerMap, out);
				out.flush();
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private void copyFtl(String tempFolderPath) throws Exception {

		File tempFile = new File(tempFolderPath);
		if (!tempFile.isDirectory()) {
			tempFile.mkdirs();
		}
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add("service");
		fileNameList.add("serviceImpl");
		fileNameList.add("controller");

		copyFile(fileNameList, tempFile.getPath());
	}

	private void copyFile(List<String> fileNameList, String targetPath) throws Exception {
		for (String fileName : fileNameList) {
			InputStream is = this.getClass().getResourceAsStream("/ftl/" + fileName + ".ftl");
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			File targetFile = new File(targetPath + "/" + fileName + ".ftl");
			if (!targetFile.isFile()) {
				targetFile.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, false));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				writer.write(temp);
				writer.newLine();
			}
			is.close();
			br.close();
			writer.flush();
			writer.close();
		}
	}
}
