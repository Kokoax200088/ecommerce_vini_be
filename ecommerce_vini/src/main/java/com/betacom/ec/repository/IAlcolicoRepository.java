package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Alcolico;

public interface IAlcolicoRepository extends JpaRepository<Alcolico, Integer> {

	@Query(name="alcolico.searchByFilter")
	List<Alcolico> searchByFilter(
			@Param("idColore") Integer idColore,
			@Param("idTipologia") Integer idTipologia,
			@Param("nome") String nome,
			@Param("gradazione") Integer gradazione,
			@Param("annata") Integer annata
			);
}
