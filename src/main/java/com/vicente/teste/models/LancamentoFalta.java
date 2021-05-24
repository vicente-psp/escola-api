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
@SequenceGenerator(name = "lancamento_falta_seq", sequenceName = "lancamento_falta_seq", initialValue = 1, allocationSize = 1)
public class LancamentoFalta {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_falta_seq")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "Ano letivo do lançamento da falta é obrigatório")
	private AnoLetivo anoLetivo;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	@NotNull(message = "Aluno do lançamento da falta é obrigatório")
	private Aluno aluno;
	
	@NotNull(message = "Quantidade do lançamento da falta é obrigatório")
	private Integer quantidade;

}
