package com.cenfotec.ColectivoJamApi.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cenfotec.ColectivoJamApi.connect.Connect;
import com.cenfotec.ColectivoJamApi.domain.Bands;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@SuppressWarnings("unused")
public class BandsRepository {
	private Firestore db;
	private String collectionBands = "bands";
	
	public BandsRepository() {
		db = Connect.getDB();
	}
	
	public String save(Bands newBands) {
		DocumentReference addedDocRef = db.collection(collectionBands).document();
		ApiFuture<WriteResult> future = addedDocRef.set(newBands);

		return addedDocRef.getId();
	}

	public List<Bands> findAll() {
		ApiFuture<QuerySnapshot> future = db.collection(collectionBands).get();
		List<Bands> bands = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = null;

		try {
			documents = future.get().getDocuments();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		for (QueryDocumentSnapshot document : documents) {
			Bands toPatent = document.toObject(Bands.class);
			toPatent.setId(document.getId());
			bands.add(toPatent);
		}

		return bands;
	}

	public Bands findByName(String checkName) throws InterruptedException, ExecutionException, Exception {
		Bands toBands = null;
		CollectionReference bands = db.collection(collectionBands);

		Query bandsQuery = bands.whereEqualTo("name", checkName);
		ApiFuture<QuerySnapshot> querySnapshot = bandsQuery.get();
		
		if (querySnapshot.get().getDocuments().isEmpty()) {
			throw new Exception("NOT FOUND BAND NAME");
		}
		
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			toBands = document.toObject(Bands.class);
		}

		return toBands;
	}

	public String delete(String checkName) throws InterruptedException, ExecutionException, Exception {
		CollectionReference bands = db.collection(collectionBands);
		
		Query bandsQuery = bands.whereEqualTo("name", checkName);
		ApiFuture<QuerySnapshot> querySnapshot = bandsQuery.get();
		
		if (querySnapshot.get().getDocuments().isEmpty()) {
			throw new Exception("NOT FOUND BAND WITH NAME: "+ checkName);
		}
		
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			ApiFuture<WriteResult> writeResult = bands.document(document.getId()).delete();
		}
		
		return "DELETED BAND WITH NAME: " + checkName;
	}
}
