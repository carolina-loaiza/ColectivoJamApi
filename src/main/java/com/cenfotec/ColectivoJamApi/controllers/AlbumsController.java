package com.cenfotec.ColectivoJamApi.controllers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.ColectivoJamApi.domain.Albums;
import com.cenfotec.ColectivoJamApi.service.AlbumsService;
import com.google.cloud.Timestamp;

@RestController
public class AlbumsController {
	AlbumsService albumsService = new AlbumsService();
	
	@GetMapping("/albums")
	public ResponseEntity<List<Albums>> getPatents() {
		return ResponseEntity.ok(albumsService.getAllAlbums());
	}

	@PostMapping("/albums")
	public ResponseEntity<String> savePatent(@RequestBody Albums newAlbum) {
		Date date = new Date();
		newAlbum.setDateCreated(Timestamp.of(date));
		
		return ResponseEntity.ok(albumsService.saveAlbum(newAlbum));
	}

	@GetMapping("/albums/{name}")
	public ResponseEntity<Albums> savePatent(@PathVariable String checkName) {
		try {
			return ResponseEntity.ok(albumsService.getAlbumByName(checkName));
		} catch (InterruptedException e) {
			return ResponseEntity.status(500).body(null);
		} catch (ExecutionException e) {
			return ResponseEntity.status(500).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@DeleteMapping("/albums/{name}")
	public ResponseEntity<String> deleteAlbumByName(@PathVariable String checkName) {
		try {
			return ResponseEntity.ok(albumsService.deleteAlbumByName(checkName));
		} catch (InterruptedException e) {
			return ResponseEntity.status(503).body(null);
		} catch (ExecutionException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
}
