package org.hotel.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CodeUtils {

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return byte[] 消息摘要
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeMD5(String data) throws Exception {
		// 执行消息摘要
		return DigestUtils.md5(data);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return byte[] 消息摘要
	 * 
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data) {
		// 执行消息摘要
		return DigestUtils.md5Hex(data);
	}
	
	private static final String KEY_AES = "AES";

	public static String encrypt(String src, String key) throws Exception {
		if (key == null || key.length() != 16) {
			throw new Exception("key不满足条件");
		} 
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(src.getBytes());
		return byte2hex(encrypted);
	}

	public static String decrypt(String src, String key) throws Exception {
		if (key == null || key.length() != 16) {
			throw new Exception("key不满足条件");
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
		Cipher cipher = Cipher.getInstance(KEY_AES);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encrypted1 = hex2byte(src);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	/**
	 * 字符编码
	 */
	public final static String ENCODING = "UTF-8";

	/**
	 * Base64编码
	 * 
	 * @param data 待编码数据
	 * @return String 编码数据
	 * @throws Exception
	 */
	public static String encode(String data) throws Exception {
		// 执行编码
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));
		return new String(b, ENCODING);
	}

	/**
	 * Base64安全编码<br>
	 * 遵循RFC 2045实现
	 * @param data
	 *            待编码数据
	 * @return String 编码数据
	 * 
	 * @throws Exception
	 */
	public static String encodeSafe(String data) throws Exception {
		// 执行编码
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING), true);
		return new String(b, ENCODING);
	}

	/**
	 * Base64解码
	 * 
	 * @param data 待解码数据
	 * @return String 解码数据
	 * @throws Exception
	 */
	public static String decode(String data) throws Exception {
		// 执行解码
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(b, ENCODING);
	}
	
	static{
		//TODO
//		Security.insertProviderAt(new BouncyCastleProvider(), 1);
	}
	/**
	 * 密钥算法 <br>
	 * Java 6 只支持56bit密钥 <br>
	 * Bouncy Castle 支持64bit密钥
	 */
	public static final String KEY_ALGORITHM = "DES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式
	 */
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return Key 密钥
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {

		// 实例化DES密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		// 实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEY_ALGORITHM);

		// 生成秘密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);

		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		// 还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);

		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		// 还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);

		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 生成密钥 <br>
	 * Java 6 只支持56bit密钥 <br>
	 * Bouncy Castle 支持64bit密钥 <br>
	 * 
	 * @return byte[] 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {

		/*
		 * 实例化密钥生成器
		 * 
		 * 若要使用64bit密钥注意替换 将下述代码中的KeyGenerator.getInstance(CIPHER_ALGORITHM);
		 * 替换为KeyGenerator.getInstance(CIPHER_ALGORITHM, "BC");
		 */
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);

		/*
		 * 初始化密钥生成器 若要使用64bit密钥注意替换 将下述代码kg.init(56); 替换为kg.init(64);
		 */
		kg.init(56, new SecureRandom());

		// 生成秘密密钥
		SecretKey secretKey = kg.generateKey();

		// 获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}
	
	public static byte[] initKey(String seed) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		SecureRandom secureRandom = new SecureRandom(new Base64().decode(seed));  
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
	
	
	
}
