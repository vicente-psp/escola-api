package com.vicente.teste.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Frequencia;
import com.vicente.teste.models.enums.AnoLetivo;

import javassist.NotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FrequenciaRepositoryTests {
	
	@Autowired FrequenciaRepository repository;
	
	private Aluno aluno = Aluno.builder().id(1).build();
	private AnoLetivo anoLetivo = AnoLetivo.PRIMEIRO_BIMESTRE;
	
	@Test
	@Order(1)
	public void save() {
		Frequencia lancamentoFalta1 = Frequencia.builder()
					.aluno(aluno)
					.anoLetivo(anoLetivo)
					.presenca(false)
				.build();
		repository.save(lancamentoFalta1);
		
		Frequencia lancamentoFalta2 = Frequencia.builder()
					.aluno(aluno)
					.anoLetivo(anoLetivo)
					.presenca(false)
				.build();
		repository.save(lancamentoFalta2);
		
		Frequencia lancamentoFalta3 = Frequencia.builder()
					.aluno(aluno)
					.anoLetivo(anoLetivo)
					.presenca(false)
				.build();
		repository.save(lancamentoFalta3);
		
		List<Frequencia> lista = repository.findAll();
		
		assertThat(lista.size()).isEqualTo(3);
	}
	
	@Test
	@Order(2)
	public void sumQuantidadeFaltaByAlunoAndBimestre() throws NotFoundException {
		Integer total = repository.countLancamentosByAlunoAndAnoLetivo(aluno, anoLetivo).orElse(0);
		
		assertThat(total).isEqualTo(39);
	}
	
	@Test
	@Order(3)
	public void delete() throws NotFoundException {
		repository.deleteAll();
		List<Frequencia> lista = repository.findAll();
		
		assertThat(lista.size()).isEqualTo(0);
	}
	
}
