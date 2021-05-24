package com.vicente.teste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Frequencia;
import com.vicente.teste.models.enums.AnoLetivo;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Integer> {
	
	@Query("SELECT COUNT(tb.id) " +
		   "FROM   Frequencia tb " +
		   "WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
	Optional<Integer> countLancamentosByAlunoAndAnoLetivo(
			@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
	@Query("SELECT COUNT(tb.id) " +
		   "FROM   Frequencia tb " +
		   "WHERE  tb.aluno = :aluno AND tb.presenca = TRUE")
	Optional<Integer> countPresencaByAluno(@Param("aluno") Aluno aluno);
	
}
