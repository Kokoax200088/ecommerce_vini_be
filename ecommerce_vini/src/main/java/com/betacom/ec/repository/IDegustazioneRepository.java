package com.betacom.ec.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Degustazione;

@Repository
public interface IDegustazioneRepository extends JpaRepository<Degustazione, Integer> {

	@Query(name = "degustazione.searchWithParameters")
	List<Degustazione> searchWithParameters(
	    @Param("nome") String nome,
	    @Param("descrizione") String descrizione,
	    @Param("prezzo") Double prezzo,
	    @Param("data_inizio") LocalDateTime dataInizio,
	    @Param("data_fine") LocalDateTime dataFine,
	    @Param("id_cantina") Integer id_cantina
	);
	
}
