package com.vicente.teste.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.teste.models.AvaliacaoPeso;
import com.vicente.teste.services.AvaliacaoPesoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/avaliacoes-peso")
public class AvaliacaoPesoController {

	@Autowired AvaliacaoPesoService service;
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoPeso>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (NotFoundException e) {
			Map<String, String> errors = new HashMap<>();
			errors.put("mensagem", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}
	}
	
}
