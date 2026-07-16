package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.PrenotazioneDegustazione;

public interface IPrenotazioneDegustazioneRepository extends JpaRepository<PrenotazioneDegustazione, Integer>{
	
	@Query(name = "prenotazioneDegustazione.searchWithParameters")
	List<PrenotazioneDegustazione> searchWithParameters(
	    @Param("id_ordine") Integer id_ordine,
	    @Param("id_degustazione") Integer id_degustazione,
	    @Param("id_status") Integer id_status,
	    @Param("id_cantina") Integer id_cantina
	);
}
