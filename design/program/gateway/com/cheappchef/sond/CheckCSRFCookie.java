package com.cheappchef.sond;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Header;

public class CheckCSRFCookie {

	public void apply(@Header("cookie") String cookieHeader) throws Exception {
		
		Pattern pattern = Pattern.compile("csrf=([^;]*)");
		
		Matcher matcher = pattern.matcher(cookieHeader);
		
		if (matcher.find()) {
			
			if (Objects.equals(matcher.group(1),"csrf")) {
				
				return;
			}
		}
			
		throw new Exception("CSRF attempt will not be processed: CSRF Cookie not found or not match");
	}
}
