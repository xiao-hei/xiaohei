package com.baizhi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

import org.springframework.util.DigestUtils;
/**
 * 
 * @author 李老湿
 * @date 2017年5月2日
 */
public class MD5Util {
	public static void main(String[] args) {
		String pass = "123123";
		//生成盐
		String salt = MD5Util.getSalt(4);
		//加密
		String jdkMD = MD5Util.jdkMD(pass+salt);
		
	}
	
	/**
	 * JDK Md5加密
	 */
	public static String jdkMD(String target){
		StringBuilder stringBuilder = new  StringBuilder();
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] digest2 = digest.digest(target.getBytes());
		for (byte b : digest2) {
			int l = b & 0xff;
			if(l<16){
				stringBuilder.append("0");
			}
			stringBuilder.append(Integer.toHexString(l));
		}
		return stringBuilder.toString();
	}
	/**
	 * 给用户密码加盐，增加安全性
	 */
	public static String getSalt(int count) {
		// 生成随机字符
		char[] str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		// 随机数
		Random random = new Random();
		/**
		 * StringBuilder 线程不安全 效率高 常用 StringBuffer 线程安全 效率低
		 */
		String salt = "";
		for (int i = 0; i < count; i++) {
			salt += str[random.nextInt(str.length)];
		}

		return salt;

	}

}
