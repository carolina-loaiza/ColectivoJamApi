package com.cenfotec.ColectivoJamApi.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UUIDController {
	
	@GetMapping("/UUIDController")
	public ResponseEntity<UUID> getUUID(){
		return ResponseEntity.ok(UUID.randomUUID());
	}
}