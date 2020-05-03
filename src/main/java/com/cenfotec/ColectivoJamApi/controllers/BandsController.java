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

import com.cenfotec.ColectivoJamApi.domain.Bands;
import com.cenfotec.ColectivoJamApi.service.BandsService;
import com.google.cloud.Timestamp;

@RestController
public class BandsController {
	BandsService bandsService = new BandsService();
	
	@GetMapping("/bands")
	public ResponseEntity<List<Bands>> getPatents() {
		return ResponseEntity.ok(bandsService.getAllPatents());
	}

	@PostMapping("/bands")
	public ResponseEntity<String> savePatent(@RequestBody Bands newBand) {
		Date date = new Date();
		newBand.setDateCreated(Timestamp.of(date));
		
		return ResponseEntity.ok(bandsService.savePatent(newBand));
	}

	@GetMapping("/bands/{name}")
	public ResponseEntity<Bands> savePatent(@PathVariable String checkName) {
		try {
			return ResponseEntity.ok(bandsService.getBandByName(checkName));
		} catch (InterruptedException e) {
			return ResponseEntity.status(500).body(null);
		} catch (ExecutionException e) {
			return ResponseEntity.status(500).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}

	@DeleteMapping("/bands/{name}")
	public ResponseEntity<String> deletePatentById(@PathVariable String checkName) {
		try {
			return ResponseEntity.ok(bandsService.deleteBandByName(checkName));
		} catch (InterruptedException e) {
			return ResponseEntity.status(503).body(null);
		} catch (ExecutionException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
}
