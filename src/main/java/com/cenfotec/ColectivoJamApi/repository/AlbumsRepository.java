package com.cenfotec.ColectivoJamApi.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cenfotec.ColectivoJamApi.connect.Connect;
import com.cenfotec.ColectivoJamApi.domain.Albums;
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
public class AlbumsRepository {
	private Firestore db;
	private String collectionBands = "albums";
	
	public AlbumsRepository() {
		db = Connect.getDB();
	}
	
	public String save(Albums newAlbum) {
		DocumentReference addedDocRef = db.collection(collectionBands).document();
		ApiFuture<WriteResult> future = addedDocRef.set(newAlbum);

		return addedDocRef.getId();
	}

	public List<Albums> findAll() {
		ApiFuture<QuerySnapshot> future = db.collection(collectionBands).get();
		List<Albums> albums = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = null;

		try {
			documents = future.get().getDocuments();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		for (QueryDocumentSnapshot document : documents) {
			Albums toAlbum = document.toObject(Albums.class);
			toAlbum.setId(document.getId());
			albums.add(toAlbum);
		}

		return albums;
	}

	public Albums findByName(String checkName) throws InterruptedException, ExecutionException, Exception {
		Albums toAlbum = null;
		CollectionReference albums = db.collection(collectionBands);

		Query albumQuery = albums.whereEqualTo("name", checkName);
		ApiFuture<QuerySnapshot> querySnapshot = albumQuery.get();
		
		if (querySnapshot.get().getDocuments().isEmpty()) {
			throw new Exception("NOT FOUND ALBUM NAME");
		}
		
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			toAlbum = document.toObject(Albums.class);
		}

		return toAlbum;
	}

	public String delete(String checkName) throws InterruptedException, ExecutionException, Exception {
		CollectionReference albums = db.collection(collectionBands);
		
		Query albumQuery = albums.whereEqualTo("name", checkName);
		ApiFuture<QuerySnapshot> querySnapshot = albumQuery.get();
		
		if (querySnapshot.get().getDocuments().isEmpty()) {
			throw new Exception("NOT FOUND ALBUM WITH NAME: "+ checkName);
		}
		
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			ApiFuture<WriteResult> writeResult = albums.document(document.getId()).delete();
		}
		
		return "DELETED ALBUM WITH NAME: " + checkName;
	}
}
