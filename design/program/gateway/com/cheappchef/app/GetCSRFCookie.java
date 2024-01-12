package com.cheappchef.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Header;

public class GetCSRFCookie {

	public String apply(@Header("cookie") String cookieHeader) {
		
		Pattern pattern = Pattern.compile("csrf=([^;]*)");
		
		Matcher matcher = pattern.matcher(cookieHeader);
		
		if (matcher.find()) {
			
			return matcher.group(1);
		}
		else {
			
			return "";
		}
	}
}
