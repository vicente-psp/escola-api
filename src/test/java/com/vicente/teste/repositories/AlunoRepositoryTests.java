package com.vicente.teste.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vicente.teste.models.Aluno;

import javassist.NotFoundException;

@SpringBootTest
public class AlunoRepositoryTests {
	
	@Autowired AlunoRepository repository;
	
	@Test
	public void findByIdTest() throws NotFoundException {
		final int id = 1;
		Aluno aluno = repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Aluno " + id + " não encontrado"));
		
		assertThat(aluno.getNome()).isEqualTo("Pedro");
	}
	
}
