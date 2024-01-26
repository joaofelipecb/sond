package com.cheappchef.sond;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

import java.util.concurrent.TimeUnit;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SessionCookieOptions;

public class ManageFirebaseAuthCookie implements FirebaseUtils {
	
	private Exchange exchange;
	
	@Handler
	public void apply(Exchange exchange) throws Exception {
		
		this.exchange = exchange;
		
		initializeFirebaseAppIfNeeded();
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		
		createSessionCookie();
	}

	public void createSessionCookie() throws FirebaseAuthException {
		
		String idToken = (String) exchange.getIn().getHeader("auth");
		
		long expiresIn = TimeUnit.DAYS.toMillis(1);
		
		SessionCookieOptions sessionCookieOptions = SessionCookieOptions.builder()
				.setExpiresIn(expiresIn)
				.build();
		
		String sessionCookie = FirebaseAuth.getInstance().createSessionCookie(idToken, sessionCookieOptions);
		
		exchange.setProperty("sessionCookie", sessionCookie);
	}
}
