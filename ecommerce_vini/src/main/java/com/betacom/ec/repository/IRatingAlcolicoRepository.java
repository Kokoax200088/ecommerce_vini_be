package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.RatingAlcolico;

public interface IRatingAlcolicoRepository extends JpaRepository<RatingAlcolico, Integer> {

	@Query (name="ratingAlc.searchByFilter")
	List<RatingAlcolico> searchByFilter(
			@Param("alcolico") String alcolico,
			@Param("utente") Integer utente,
			@Param("valutazione") Integer valutazione
			);

	void deleteByAlcolico_Id(Integer id_alcolico);

	void deleteByCliente_Id(Integer id);

	void deleteByCantina_Id(Integer id);
}
