package com.cheappchef.app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CacheHash {

	public CacheHash() {
		// TODO Auto-generated constructor stub
	}
	
	public String apply(String value) {
		
		MessageDigest messageDigest;
		
		try {
		
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			
			return "dummy-cache-key";
		}
		
		byte[] messageDigested = messageDigest.digest(value.getBytes());
		
		BigInteger numericRepresentation = new BigInteger(1, messageDigested);
		
		String stringRepresentation = numericRepresentation.toString(16);
		
		return String.format("%32s", stringRepresentation).replace(" ", "0");
	}

}
