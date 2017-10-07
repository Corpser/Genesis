package com.jiaxiaoxuan.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.Application;

@RunWith(SpringRunner.class)
//此处配置时注入的类时spring boot main方法所在的类，否则在单元测试类中注入bean
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CreatePass {

	@Autowired
	private StringEncryptor stringEncryptor;
	
	@Test
	public  void encryptPwd() {
		String result = stringEncryptor.encrypt("root");
        System.out.println("encryptPwd   ==============  "  +  result);
	}
}