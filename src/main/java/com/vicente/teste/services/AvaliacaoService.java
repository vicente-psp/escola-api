package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.models.AvaliacaoPeso;
import com.vicente.teste.models.enums.AnoLetivo;
import com.vicente.teste.repositories.AvaliacaoRepository;

import javassist.NotFoundException;

@Service
public class AvaliacaoService {

	@Autowired AvaliacaoRepository repository;
	@Autowired AvaliacaoPesoService avaliacaoPesoService;
	
	public Avaliacao findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Avaliação com id " + id + " não foi localizado"));
	}
	
	public Avaliacao save(Avaliacao entity) throws NotFoundException {
		AvaliacaoPeso avaliacaoPeso = avaliacaoPesoService
			.findById(entity.getAvaliacaoPeso().getId());
		entity.setPeso(avaliacaoPeso.getValor());
		
		return repository.save(entity);
	}
	
	public List<Avaliacao> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	
	public float sumNotaByAlunoAndBimestre(Aluno aluno, AnoLetivo anoLetivo) {
		return repository.sumNotaByAlunoAndAnoLetivo(aluno, anoLetivo).orElse(0.0f);
	}
	
	public List<Avaliacao> listByAlunoAndAnoLetivo(Aluno aluno, AnoLetivo anoLetivo) {
		return repository.listByAlunoAndAnoLetivo(aluno, anoLetivo);
	}
	
}
