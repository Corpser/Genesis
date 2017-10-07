package com.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Md5Util {

	/**
	 * 获取字符串MD5值
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	public String getMd5(String str) throws NoSuchAlgorithmException {
		try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(str.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }
	}
}
