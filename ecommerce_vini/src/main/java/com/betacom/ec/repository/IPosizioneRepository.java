package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Posizione;

@Repository
public interface IPosizioneRepository  extends JpaRepository <Posizione, Integer> {
	@Query(name="posizione.searchByFilter")
	List<Posizione> searchByFilter(
			@Param("descrizione") String descrizione
			);
}
