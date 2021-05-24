package com.vicente.teste.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@SequenceGenerator(name = "aluno_seq", sequenceName = "aluno_seq", initialValue = 1, allocationSize = 1)
public class Aluno {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_seq")
	private Integer id;
	
	@NotNull(message = "Nome é obrigatório")
	@Size(min = 3, max = 40, message = "Nome deve ter entre {min} e {max} caracteres")
	private String nome;
	
	@Size(min = 3, max = 20, message = "Matrícula deve ter entre {min} e {max} caracteres")
	private String matricula;

}
