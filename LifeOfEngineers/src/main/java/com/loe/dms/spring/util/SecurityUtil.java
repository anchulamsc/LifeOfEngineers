package com.loe.dms.spring.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

	private static final String ALGORITHM = "AES";
	private static final String KEY = "1Hbfh667adfDEJ78";
	private static Key key = generateKey();

	public static String encrypt(String value) {
		Key key;
		String encryptedValue64 = "";
		try {
			key = generateKey();
			Cipher cipher = Cipher.getInstance(SecurityUtil.ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
			encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
		} catch (Exception e) {
			logger.error("Exception thrown in while encrypting the password");
		}
		return encryptedValue64;

	}

	public static String decrypt(String value) {
		String decryptedValue = "";
		try {
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(SecurityUtil.ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
			byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
			decryptedValue = new String(decryptedByteValue, "utf-8");
		} catch (Exception e) {
			logger.error("Exception thrown in while decrypting the password");
		}
		return decryptedValue;

	}

	private static Key generateKey() {
		Key key = null;
		try {
			key = new SecretKeySpec(SecurityUtil.KEY.getBytes(), SecurityUtil.ALGORITHM);
		} catch (Exception e) {
			logger.error("Exception thrown in while generateKey");
		}
		return key;
	}

}
