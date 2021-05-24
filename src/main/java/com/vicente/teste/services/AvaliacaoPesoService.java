package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.AvaliacaoPeso;
import com.vicente.teste.repositories.AvaliacaoPesoRepository;

import javassist.NotFoundException;

@Service
public class AvaliacaoPesoService {

	@Autowired AvaliacaoPesoRepository repository;
	
	public AvaliacaoPeso findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Avaliação peso com id " + id + " não foi localizado"));
	}
	
	public List<AvaliacaoPeso> findAll() {
		return repository.findAll();
	}
	
}
