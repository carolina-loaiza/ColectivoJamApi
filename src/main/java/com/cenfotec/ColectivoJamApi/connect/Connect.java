package com.cenfotec.ColectivoJamApi.connect;

import java.io.IOException;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

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
