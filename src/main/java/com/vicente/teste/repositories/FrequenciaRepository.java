package com.vicente.teste.repositories;

import java.util.List;
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
		   "WHERE  tb.aluno = :aluno AND tb.presenca = FALSE")
	Optional<Integer> sumQuantidadeFaltaByAluno(@Param("aluno") Aluno aluno);
	
	@Query("SELECT tb " +
		   "FROM   Frequencia tb " +
		   "WHERE  tb.aluno = :aluno")
	List<Frequencia> listByAluno(@Param("aluno") Aluno aluno);
	
}
