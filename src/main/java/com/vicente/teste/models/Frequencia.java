package com.vicente.teste.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.vicente.teste.models.enums.AnoLetivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "frequencia_seq", sequenceName = "frequencia_seq", initialValue = 1, allocationSize = 1)
public class Frequencia {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frequencia_seq")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Ano letivo da frequência é obrigatório")
	private AnoLetivo anoLetivo;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	@NotNull(message = "Aluno da frequência é obrigatório")
	private Aluno aluno;
	
	@NotNull(message = "Presença é obrigatório")
	private Boolean presenca;

}
