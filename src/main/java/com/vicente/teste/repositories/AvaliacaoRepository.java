package com.vicente.teste.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.models.enums.AnoLetivo;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

	@Query("SELECT SUM(tb.nota) " +
			   "FROM   Avaliacao tb " +
			   "WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
		Optional<Float> sumNotaByAlunoAndAnoLetivo(
				@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
	@Query("SELECT  tb " +
			"FROM   Avaliacao tb " +
			"WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
	List<Avaliacao> listByAlunoAndAnoLetivo(
			@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
	@Query("SELECT  tb " +
			"FROM   Avaliacao tb " +
			"WHERE  tb.aluno = :aluno")
	List<Avaliacao> listByAluno(@Param("aluno") Aluno aluno);
	
}
