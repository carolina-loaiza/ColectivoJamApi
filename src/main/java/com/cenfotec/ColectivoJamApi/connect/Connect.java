package com.cenfotec.ColectivoJamApi.connect;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Connect {
	private static Firestore db;

	public static void initConnect() throws IOException {
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.getApplicationDefault()).build();
		FirebaseApp.initializeApp(options);
		
		db = FirestoreClient.getFirestore();
	}
	
	public static Firestore getDB() {
		return db;
	}
}
