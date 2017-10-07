package com.jiaxiaoxuan.genesis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

public class CommentGenerator extends DefaultCommentGenerator {

	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() == null || introspectedColumn.getRemarks().trim().equals("")) {
			return;
		}
		field.addJavaDocLine("/** " + introspectedColumn.getRemarks() + " */");
	}
}
