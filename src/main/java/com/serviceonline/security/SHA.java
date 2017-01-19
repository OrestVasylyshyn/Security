package com.serviceonline.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
	
	public String getSHA512(String strToHash, String salt) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(strToHash.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i=0; i< bytes.length ;i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		}
		catch (NoSuchAlgorithmException ex) {}
		catch (UnsupportedEncodingException ex) {}

		return result;
	}

}