package com.kedacom.tz.sh.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {

	// --------------AES---------------
	private static final String KEY = "y1e2s3o4n5g60925"; // 密匙，必须16位
	private static final String OFFSET = "l1i2a3n4g5y6s7l8"; // 偏移量
	private static final String ENCODING = "UTF-8"; // 编码
	private static final String ALGORITHM = "AES"; // 算法
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"; // 默认的加密算法，CBC模式

	// ---------------MD5-------------------
	private static final String MD5KEY = "y1s2l3o4r5l6j7y8"; // 密匙

	/**
	 * AES加密
	 * 
	 * @param data
	 * @return String
	 */
	public static String AESencrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());// CBC模式偏移量IV
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(data.getBytes(ENCODING));
		return Base64.getEncoder().encodeToString(encrypted);// 加密后再使用BASE64做转码
	}

	/**
	 * AES解密
	 * 
	 * @param data
	 * @return String
	 */
	public static String AESdecrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes()); // CBC模式偏移量IV
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] buffer = Base64.getDecoder().decode(data);// 先用base64解码
		byte[] encrypted = cipher.doFinal(buffer);
		return new String(encrypted, ENCODING);
	}

	// ---------------------MD5--------------------
	/**
	 * MD5方法
	 * 
	 * @param text 明文
	 * @return 密文
	 */
	public static String MD5encrypt(String text) throws Exception {
		// 加密后的字符串
		String encodeStr = DigestUtils.md5Hex(text + MD5KEY);
		return encodeStr;
	}

	/**
	 * MD5验证方法
	 * 
	 * @param text 明文
	 * @param md5  密文
	 * @return true/false
	 */
	public static boolean verify(String text, String md5) throws Exception {
		// 根据传入的密钥进行验证
		String md5Text = MD5encrypt(text);
		if (md5Text.equalsIgnoreCase(md5)) {
			return true;
		}
		return false;
	}

	/**
	 * test
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String data = "我爱你，中国";
		String aeSencrypt = EncryptUtil.AESencrypt(data);
		System.out.println(aeSencrypt);
		String aeSdecrypt = EncryptUtil.AESdecrypt(aeSencrypt);
		System.out.println(aeSdecrypt);
		String md5encrypt = EncryptUtil.MD5encrypt(data);
		System.out.println(md5encrypt);
		boolean verify = EncryptUtil.verify(data, md5encrypt);
		System.out.println(verify);

	}

}
