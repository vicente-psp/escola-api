package com.vicente.teste.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.services.AvaliacaoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

	@Autowired AvaliacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (NotFoundException e) {
			Map<String, String> errors = new HashMap() {{
				put("mensagem", e.getMessage());
			}};
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}
	}
	
	public ResponseEntity<?> save(@Valid @RequestBody Avaliacao entity) throws Exception {
		try {
			return ResponseEntity.ok(service.save(entity));
		} catch (Exception e) {
			Map<String, String> errors = new HashMap() {{
				put("mensagem", e.getMessage());
			}};
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
	}
	
}
