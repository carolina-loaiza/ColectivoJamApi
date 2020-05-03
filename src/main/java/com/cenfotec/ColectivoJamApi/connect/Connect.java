package com.cenfotec.ColectivoJamApi.connect;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Connect {
	private static Firestore db;

	public static void initConnect() throws IOException {
		FirestoreOptions firestoreOptions =
			    FirestoreOptions.getDefaultInstance().toBuilder()
			        .setProjectId("elcolectivo")
			        .build();
		
		db = firestoreOptions.getService();
	}
	
	public static Firestore getDB() {
		return db;
	}
}
