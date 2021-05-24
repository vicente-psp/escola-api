package com.vicente.teste.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.models.AvaliacaoPeso;
import com.vicente.teste.models.enums.AnoLetivo;

import javassist.NotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AvaliacaoServiceTests {
	
	@Autowired AvaliacaoService service;
	
	@Autowired AvaliacaoPesoService avaliacaoPesoService;
	
	private Aluno aluno = Aluno.builder().id(1).build();
	private AnoLetivo anoLetivo = AnoLetivo.PRIMEIRO_BIMESTRE;
	
	@Test
	@Order(1)
	public void save() throws NotFoundException {
		AvaliacaoPeso avaliacaoPeso = avaliacaoPesoService
				.findById(1);
		
		Avaliacao avaliacao = Avaliacao.builder()
				.aluno(aluno)
				.anoLetivo(anoLetivo)
				.avaliacaoPeso(avaliacaoPeso)
				.nota(8.9f)
			.build();
		
		Avaliacao novaAvaliacao = service.save(avaliacao);
		
		assertThat(novaAvaliacao.getPeso()).isEqualTo(avaliacaoPeso.getValor());
	}
	
	@Test
	@Order(2)
	public void delete() throws NotFoundException {
		service.deleteById(1);
		List<Avaliacao> lista = service.findAll();
		
		assertThat(lista.size()).isEqualTo(0);
	}

}
