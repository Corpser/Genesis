package com.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * 生成随机字符串
 * 
 * @author No.1
 *
 */
@Component
public class RandomUtil {

	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "bcdfghjkmpqrtvwxy2346789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成随机字符串,不重复
	 * 
	 * @param length 字符串长度
	 * @param sum 生成数量
	 * @return
	 */
	public HashSet<String> getRandomSet(int length, int sum) {
		HashSet<String> set = new HashSet<String>();
		while (true) {
			String temp = this.getRandomString(length);
			set.add(temp);
			if (set.size() >= sum) {
				break;
			}
		}
		return set;
	}
	
	/**
	 * 生成可重复的字符串
	 * 
	 * @param length 字符串长度
	 * @param sum 生成数量
	 * @return
	 */
	public List<String> getRandomList(int length, int sum) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<sum;i++){
			list.add(this.getRandomString(length));
		}
		return list;
	}
}
