package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.RatingCantina;

public interface IRatingCantinaRepository extends JpaRepository<RatingCantina, Integer> {
	@Query (name="ratingCant.searchByFilter")
	List<RatingCantina> searchByFilter(
			@Param("cantina") Integer cantina,
			@Param("utente") Integer utente,
			@Param("valutazione") Integer valutazione
			);
}
