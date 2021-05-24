package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Frequencia;
import com.vicente.teste.repositories.FrequenciaRepository;

import javassist.NotFoundException;

@Service
public class FrequenciaService {

	@Autowired FrequenciaRepository repository;
	
	public Frequencia findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Frequência com id " + id + " não foi localizado"));
	}
	
	public List<Frequencia> findAll() {
		return repository.findAll();
	}
	
	public Frequencia save(Frequencia entity) throws Exception {
		if (!isValidPresenca(entity)) {
			throw new Exception(
				"Quantidade de frequência do aluno id " + entity.getAluno().getId() + 
				" no ano letivo " + entity.getAnoLetivo() + " não pode ultrapassar 40"
			);
		}
		return repository.save(entity);
	}
	
	public void deleteById(int id) throws NotFoundException {
		findById(id);
		repository.deleteById(id);
	}
	
	private boolean isValidPresenca(Frequencia entity) {
		int total = repository.countLancamentosByAlunoAndAnoLetivo(entity.getAluno(), entity.getAnoLetivo()).orElse(0);
		return total < 40;
	}
	
	public int countPresencaByAluno(Aluno aluno) {
		return repository.countPresencaByAluno(aluno).orElse(0);
	}
	
}
