package com.vicente.teste.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "avaliacao_peso_seq", sequenceName = "avaliacao_peso_seq", initialValue = 1, allocationSize = 1)
public class AvaliacaoPeso {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaliacao_peso_seq")
	private Integer id;
	
    @NotNull(message = "Descrição da avaliação é obrigatório")
	private String descricao;
	
	@NotNull(message = "Valor da avaliação é obrigatório")
	private Float valor;

}
