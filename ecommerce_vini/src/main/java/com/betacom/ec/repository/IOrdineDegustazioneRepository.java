package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.OrdineDegustazione;

public interface IOrdineDegustazioneRepository extends JpaRepository<OrdineDegustazione, Integer>{
	@Query(name="ordineDegustazione.searchWithParameters")
	List<OrdineDegustazione> searchWithParameters(@Param ("quantita") Integer quantita,
			@Param("id_ordine") Integer id_ordine,
			@Param("id_status") Integer id_status,
			@Param("id_degustazione") Integer id_degustazione,
			@Param("id_cantina") Integer id_cantina);

	void deleteByCantina_Id(Integer id);

	void deleteByDegustazione_Id(Integer id_degustazione);

	void deleteByOrdine_Id(Integer id);
}
