package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.enums.Situacao;
import com.vicente.teste.repositories.AlunoRepository;

import javassist.NotFoundException;

@Service
public class AlunoService {

	@Autowired AlunoRepository repository;
	@Autowired AvaliacaoService avaliacaoService;
	@Autowired LancamentoFaltaService lancamentoFaltaService;
	
	public Aluno findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Aluno com id " + id + " não foi localizado"));
	}
	
	public List<Aluno> findAll() {
		return repository.findAll();
	}
	
	public Situacao situacaoFinalAluno(Aluno aluno) {
		float somaNotas = avaliacaoService.sumNotaComPesoByAluno(aluno);
		float somaPesos = avaliacaoService.sumPesoByAluno(aluno);
		
		float media = somaNotas / somaPesos;
		
		int somaFaltas = lancamentoFaltaService.sumQuantidadeFaltaByAluno(aluno);
		
		float percentualPresenca = 100f - ((somaFaltas / 40f) * 100f);
		
		if (media >= 6.0 && percentualPresenca >= 75.0) {
			return Situacao.APROVADO;
		}
		if (media < 5.0 && media < 75.0) {
			return Situacao.REPROVADO;
		}
		if (media >= 5.0 && media < 6.0) {
			return Situacao.RECUPERACAO;
		}
		
		return null;
	}
	
}
