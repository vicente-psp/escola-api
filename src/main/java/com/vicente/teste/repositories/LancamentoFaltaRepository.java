package com.vicente.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vicente.teste.models.Aluno;
import com.vicente.teste.models.LancamentoFalta;
import com.vicente.teste.models.enums.AnoLetivo;

public interface LancamentoFaltaRepository extends JpaRepository<LancamentoFalta, Integer> {
	
	@Query("SELECT SUM(tb.quantidade) " +
		   "FROM   LancamentoFalta tb " +
		   "WHERE  tb.aluno = :aluno AND tb.anoLetivo = :anoLetivo")
	Integer sumQuantidadeFaltaByAlunoAndBimestre(
			@Param("aluno") Aluno aluno, @Param("anoLetivo") AnoLetivo anoLetivo);
	
}
