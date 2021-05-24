package com.vicente.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.teste.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
