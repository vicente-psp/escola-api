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
import com.vicente.teste.models.LancamentoFalta;
import com.vicente.teste.models.enums.AnoLetivo;

import javassist.NotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LancamentoFaltaRepositoryTests {
	
	@Autowired LancamentoFaltaRepository repository;
	
	@Test
	@Order(1)
	public void save() {
		LancamentoFalta lancamentoFalta1 = LancamentoFalta.builder()
					.aluno(Aluno.builder().id(1).build())
					.anoLetivo(AnoLetivo.PRIMEIRO_BIMESTRE)
					.quantidade(15)
				.build();
		repository.save(lancamentoFalta1);
		
		LancamentoFalta lancamentoFalta2 = LancamentoFalta.builder()
				.aluno(Aluno.builder().id(1).build())
				.anoLetivo(AnoLetivo.PRIMEIRO_BIMESTRE)
				.quantidade(15)
				.build();
		repository.save(lancamentoFalta2);
		
		LancamentoFalta lancamentoFalta3 = LancamentoFalta.builder()
				.aluno(Aluno.builder().id(1).build())
				.anoLetivo(AnoLetivo.PRIMEIRO_BIMESTRE)
				.quantidade(9)
				.build();
		repository.save(lancamentoFalta3);
		
		List<LancamentoFalta> lista = repository.findAll();
		
		assertThat(lista.size()).isEqualTo(3);
	}
	
	@Test
	@Order(2)
	public void sumQuantidadeFaltaByAlunoAndBimestre() throws NotFoundException {
		Integer soma = repository.sumQuantidadeFaltaByAlunoAndBimestre(Aluno.builder().id(1).build(), AnoLetivo.PRIMEIRO_BIMESTRE);
		
		assertThat(soma).isEqualTo(39);
	}
	
	@Test
	@Order(3)
	public void delete() throws NotFoundException {
		repository.deleteById(13);
		repository.deleteById(14);
		repository.deleteById(15);
		List<LancamentoFalta> lista = repository.findAll();
		
		assertThat(lista.size()).isEqualTo(0);
	}
	
}
