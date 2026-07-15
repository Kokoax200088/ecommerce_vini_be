package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.OrdineDegustazione;

public interface IOrdineDegustazioneRepository extends JpaRepository<OrdineDegustazione, Integer>{
	@Query("SELECT DISTINCT od FROM OrdineDegustazione od WHERE (:quantita IS NULL OR od.quantita = :quantita) AND (:id_ordine IS NULL OR od.ordine.id = :id_ordine) AND (:id_status IS NULL OR od.status.id = :id_status) AND (:id_degustazione IS NULL OR od.degustazione.id = :id_degustazione) AND (:id_cantina IS NULL OR od.cantina.id = :id_cantina)")
	List<OrdineDegustazione> searchWithParameters(@Param ("quantita") Integer quantita,
			@Param("id_ordine") Integer id_ordine,
			@Param("id_status") Integer id_status,
			@Param("id_degustazione") Integer id_degustazione,
			@Param("id_cantina") Integer id_cantina);
}
