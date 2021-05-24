package com.vicente.teste.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.models.AvaliacaoPeso;
import com.vicente.teste.models.LancamentoFalta;
import com.vicente.teste.models.dto.SituacaoDto;
import com.vicente.teste.models.enums.AnoLetivo;
import com.vicente.teste.models.enums.Situacao;
import com.vicente.teste.services.AlunoService;
import com.vicente.teste.services.AvaliacaoService;
import com.vicente.teste.services.LancamentoFaltaService;

import javassist.NotFoundException;

@SpringBootTest
public class AlunoRepositoryTests {
	
	@Autowired AlunoRepository repository;
	
	@Autowired AlunoService alunoService;
	@Autowired AvaliacaoService avaliacaoService;
	@Autowired LancamentoFaltaService lancamentoFaltaService;
	
	private Aluno aluno = Aluno.builder().id(1).build();
	private AvaliacaoPeso avaliacaoPeso = AvaliacaoPeso.builder().id(1).build();
	private AnoLetivo anoLetivo = AnoLetivo.PRIMEIRO_BIMESTRE;
	
	@Test
	public void findByIdTest() throws NotFoundException {
		final int id = 1;
		Aluno aluno = repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Aluno " + id + " não encontrado"));
		
		assertThat(aluno.getNome()).isEqualTo("Pedro");
	}
	
	@Test
	public void situacaoFinalAluno() throws Exception {
		Avaliacao avaliacao1 = Avaliacao.builder()
					.aluno(aluno)
					.avaliacaoPeso(avaliacaoPeso)
					.anoLetivo(anoLetivo)
					.nota(7.5f)
				.build();
		avaliacaoService.save(avaliacao1);
		
		Avaliacao avaliacao2 = Avaliacao.builder()
				.aluno(aluno)
				.avaliacaoPeso(AvaliacaoPeso.builder().id(2).build())
				.anoLetivo(anoLetivo)
				.nota(8.2f)
				.build();
		avaliacaoService.save(avaliacao2);
		
		Avaliacao avaliacao3 = Avaliacao.builder()
				.aluno(aluno)
				.avaliacaoPeso(AvaliacaoPeso.builder().id(3).build())
				.anoLetivo(anoLetivo)
				.nota(9.8f)
				.build();
		avaliacaoService.save(avaliacao3);
		
		Avaliacao avaliacao4 = Avaliacao.builder()
				.aluno(aluno)
				.avaliacaoPeso(AvaliacaoPeso.builder().id(4).build())
				.anoLetivo(anoLetivo)
				.nota(6.5f)
				.build();
		avaliacaoService.save(avaliacao4);
		
		LancamentoFalta lancamentoFalta = LancamentoFalta.builder()
				.aluno(aluno)
				.anoLetivo(anoLetivo)
				.quantidade(9)
			.build();
		lancamentoFaltaService.save(lancamentoFalta);
		
		SituacaoDto situacaoDto = alunoService.situacaoFinal(aluno.getId());
		
		assertThat(situacaoDto.getSituacao()).isEqualTo(Situacao.APROVADO);
	}
	
}
