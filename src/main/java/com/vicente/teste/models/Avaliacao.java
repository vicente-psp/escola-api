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
@SequenceGenerator(name = "avaliacao_seq", sequenceName = "avaliacao_seq", initialValue = 1, allocationSize = 1)
public class Avaliacao {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaliacao_seq")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Ano letivo da avaliação é obrigatório")
	private AnoLetivo anoLetivo;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	@NotNull(message = "Aluno da avaliação é obrigatório")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "avaliacao_peso_id")
	@NotNull(message = "Avaliação peso da avaliação é obrigatório")
	private AvaliacaoPeso avaliacaoPeso;
	
	@NotNull(message = "Nota da avaliação é obrigatório")
	private Float nota;
	
	private Float peso;

}
