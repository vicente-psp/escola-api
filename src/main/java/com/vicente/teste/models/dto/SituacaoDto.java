package com.vicente.teste.models.dto;

import com.vicente.teste.models.enums.Situacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacaoDto {

	private int aluno;
	private float media1;
	private float media2;
	private float media3;
	private float media4;
	private float mediaFinal;
	private float percentualPresencas;
	private Situacao situacao;
	
}
