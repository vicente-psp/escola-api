package com.vicente.teste.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.Avaliacao;
import com.vicente.teste.models.enums.AnoLetivo;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

	@Query("SELECT SUM(tb.nota * tb.peso) " +
		   "FROM   Avaliacao tb " +
		   "WHERE  tb.aluno = :aluno")
	Optional<Float> sumNotaComPesoByAluno(@Param("aluno") Aluno aluno);
	
	@Query("SELECT SUM(tb.peso) " +
			"FROM   Avaliacao tb " +
			"WHERE  tb.aluno = :aluno")
	Optional<Float> sumPesoByAluno(@Param("aluno") Aluno aluno);
	
	@Query("SELECT SUM(tb.nota * tb.peso) " +
			"FROM   Avaliacao tb " +
			"WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
	Optional<Float> sumNotaComPesoByAlunoAndAnoLetivo(
			@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
	@Query("SELECT SUM(tb.peso) " +
			"FROM   Avaliacao tb " +
			"WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
	Optional<Float> sumPesoByAlunoAndAnoLetivo(
			@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
}
