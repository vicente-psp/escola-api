package com.vicente.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.dto.SituacaoDto;
import com.vicente.teste.models.enums.AnoLetivo;
import com.vicente.teste.models.enums.Situacao;
import com.vicente.teste.repositories.AlunoRepository;

import javassist.NotFoundException;

@Service
public class AlunoService {

	@Autowired AlunoRepository repository;
	@Autowired AvaliacaoService avaliacaoService;
	@Autowired FrequenciaService lancamentoFaltaService;
	
	public Aluno findById(int id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Aluno com id " + id + " não foi localizado"));
	}
	
	public List<Aluno> findAll() {
		return repository.findAll();
	}
	
	public SituacaoDto situacaoFinal(int id) throws NotFoundException {
		Aluno aluno = findById(id);
		float somaNotas1 = avaliacaoService.sumNotaComPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.PRIMEIRO_BIMESTRE);
		float somaPesos1 = avaliacaoService.sumPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.PRIMEIRO_BIMESTRE);
		float media1 = somaPesos1 > 0f ? somaNotas1 / somaPesos1 : 0f;
		
		float somaNotas2 = avaliacaoService.sumNotaComPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.SEGUNDO_BIMESTRE);
		float somaPesos2 = avaliacaoService.sumPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.SEGUNDO_BIMESTRE);
		float media2 = somaPesos2 > 0f ? somaNotas2 / somaPesos2 : 0f;
		
		float somaNotas3 = avaliacaoService.sumNotaComPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.TERCEIRO_BIMESTRE);
		float somaPesos3 = avaliacaoService.sumPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.TERCEIRO_BIMESTRE);
		float media3 = somaPesos3 > 0f ? somaNotas3 / somaPesos3 : 0f;
		
		float somaNotas4 = avaliacaoService.sumNotaComPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.QUARTO_BIMESTRE);
		float somaPesos4 = avaliacaoService.sumPesoByAlunoAndAnoLetivo(aluno, AnoLetivo.QUARTO_BIMESTRE);
		float media4 = somaPesos4 > 0f ? somaNotas4 / somaPesos4 : 0f;
		
		float mediaFinal = (media1 + media2 + media3 + media4) / 4f;
		
		int somaFaltas = lancamentoFaltaService.sumQuantidadeFaltaByAluno(aluno);
		
		float percentualPresenca = 100f - ((somaFaltas / 40f) * 100f);
		
		SituacaoDto situacaoDto = SituacaoDto.builder().aluno(id).build();
		
		if (mediaFinal >= 6f && percentualPresenca >= 75f) {
			situacaoDto.setSituacao(Situacao.APROVADO);
		} else if (mediaFinal < 5f || mediaFinal < 75f) {
			situacaoDto.setSituacao(Situacao.REPROVADO);
		} else if (mediaFinal >= 5f && mediaFinal < 6f) {
			situacaoDto.setSituacao(Situacao.RECUPERACAO);
		}
		
		return situacaoDto;
	}
	
}
