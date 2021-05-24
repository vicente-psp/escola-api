package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.repositories.AlunoRepository;

import javassist.NotFoundException;

@Service
public class AlunoService {

	@Autowired AlunoRepository repository;
	
	public Aluno findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Aluno com id " + id + " não foi localizado"));
	}
	
	public List<Aluno> findAll() {
		return repository.findAll();
	}
	
}
