package com.serviceonline.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptor {
	
	private String key = null; // 128 bit key
	private final String initVector = "1234567890abcdef"; // 16 bytes IV
	private final String keyMask = "1234567890abcdef";
	
	public void setUp(String key) {
		Integer keyLength = key.length();
		if (keyLength>=8 && keyLength<=16) {
			this.key = key + keyMask.substring(keyLength, 16);
		} else {
			this.key = key.substring(0, 16);
		}
	}
	
	public String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(this.initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(this.key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(this.initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(this.key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}