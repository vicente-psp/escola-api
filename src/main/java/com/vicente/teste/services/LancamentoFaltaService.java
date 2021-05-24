package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.LancamentoFalta;
import com.vicente.teste.repositories.LancamentoFaltaRepository;

import javassist.NotFoundException;

@Service
public class LancamentoFaltaService {

	@Autowired LancamentoFaltaRepository repository;
	
	public LancamentoFalta findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Aluno com id " + id + " não foi localizado"));
	}
	
	public List<LancamentoFalta> findAll() {
		return repository.findAll();
	}
	
	public LancamentoFalta save(LancamentoFalta entity) throws Exception {
		if (!isValidQuantidadeFaltas(entity)) {
			throw new Exception(
				"Quantidade de faltas do aluno id " + entity.getAluno().getId() + 
				" no ano letivo " + entity.getAnoLetivo() + " não pode ultrapassar 40 faltas"
			);
		}
		return repository.save(entity);
	}
	
	public void deleteById(int id) throws NotFoundException {
		findById(id);
		repository.deleteById(id);
	}
	
	private boolean isValidQuantidadeFaltas(LancamentoFalta entity) {
		int totalFaltas = repository.sumQuantidadeFaltaByAlunoAndAnoLetivo(entity.getAluno(), entity.getAnoLetivo()).orElse(0);
		return entity.getQuantidade() + totalFaltas <= 40;
	}
	
	public List<LancamentoFalta> listByAluno(Aluno aluno) {
		return repository.listByAluno(aluno);
	}
	
}
