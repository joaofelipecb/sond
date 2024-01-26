package com.cheappchef.sond;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Exchange;

import org.apache.camel.Handler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class ValidateFirebaseAuthToken {
	
	private Exchange exchange;
	
	private String cookieHeader;
	
	private String authCookie;

	@Handler
	public void apply(Exchange exchange) throws Exception {
		
		initializeObject(exchange);
		
		initializeFirebaseAppIfNeeded();
		
		copyAllHeaders();
		
		decodeAndValidateAndStoreAuthToken();
	}
	
	public void initializeObject(Exchange exchange) throws Exception {
		
		this.exchange = exchange;
		
		collectCookieHeader();
		
		collectAuthCookie();
	}
	
	public void collectCookieHeader() throws Exception {
		
		cookieHeader = (String) exchange.getIn().getHeader("cookie");
		
		if (cookieHeader == null) {
			
			throw new Exception("No cookie found");
		}
	}
	
	public void collectAuthCookie() throws Exception {
		
		Pattern pattern = Pattern.compile("auth=([^;]*)");
		
		Matcher matcher = pattern.matcher(cookieHeader);
		
		if (matcher.find()) {
			
			authCookie = matcher.group(1);
		}
		else {
			
			throw new Exception("No auth cookie found");
		}
	}
	
	public void initializeFirebaseAppIfNeeded() {
		
		try {
			
			FirebaseApp.getInstance();
		}
		catch (IllegalStateException exception) {
			
			/*FirebaseOptions options = FirebaseOptions.builder()
				    .setCredentials(GoogleCredentials.getApplicationDefault())
				    .setProjectId("<FIREBASE_PROJECT_ID>")
				    .build();*/
			
			FirebaseApp.initializeApp(/*options*/);
		}
	}
	
	public void copyAllHeaders() {
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
	}
	
	public void decodeAndValidateAndStoreAuthToken() throws Exception {
		
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(authCookie);
		
		String uid = decodedToken.getUid();
		
		exchange.getOut().setHeader("sondFirebaseAuthUserId", uid);		
	}
}
