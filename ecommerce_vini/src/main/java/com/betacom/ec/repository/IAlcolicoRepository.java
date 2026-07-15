package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Alcolico;

public interface IAlcolicoRepository extends JpaRepository<Alcolico, Integer> {

	@Query("select a from Alcolico a where (:idColore is null or a.colore.id = :idColore) and (:idTipologia is null or a.tipologia_alcolico.id = :idTipologia) and (:nome is null or lower(a.nome) like lower(concat('%', :nome, '%'))) and (:gradazione is null or a.gradazione = :gradazione) and (:annata is null or a.annata = :annata)")
	List<Alcolico> searchByFilter(
			@Param("idColore") Integer idColore,
			@Param("idTipologia") Integer idTipologia,
			@Param("nome") String nome,
			@Param("gradazione") Integer gradazione,
			@Param("annata") Integer annata
			);
}
