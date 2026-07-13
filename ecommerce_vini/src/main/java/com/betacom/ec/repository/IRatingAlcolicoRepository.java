package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.RatingAlcolico;

public interface IRatingAlcolicoRepository extends JpaRepository<RatingAlcolico, Integer> {

	@Query (name="ratingAlc.searchByFilter")
	List<RatingAlcolico> searchByFilter(
			@Param("alcolico") Integer alcolico,
			@Param("utente") Integer utente,
			@Param("valutazione") Integer valutazione
			);
}
