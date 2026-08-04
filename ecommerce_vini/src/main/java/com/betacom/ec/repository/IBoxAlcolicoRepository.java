package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.BoxAlcolico;

@Repository
public interface IBoxAlcolicoRepository extends JpaRepository<BoxAlcolico, Integer>{
	@Query (name="boxAlcolico.searchByFilter")
	List<BoxAlcolico> searchByFilter( 
			@Param("quantita") Integer quantita,
			@Param("id_ordine") Integer id_ordine,
			@Param("id_status") Integer id_status,
			@Param("id_alcolico") Integer id_alcolico,
			@Param("id_cantina") Integer id_cantina
			);

	void deleteByAlcolico_Id(Integer id_alcolico);
}
