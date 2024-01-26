package com.cheappchef.sond;

import com.google.firebase.FirebaseApp;

public interface FirebaseUtils {

	public default void initializeFirebaseAppIfNeeded() {
		
		try {
			
			FirebaseApp.getInstance();
		}
		catch (IllegalStateException exception) {
			
			FirebaseApp.initializeApp();
		}
	}
}
