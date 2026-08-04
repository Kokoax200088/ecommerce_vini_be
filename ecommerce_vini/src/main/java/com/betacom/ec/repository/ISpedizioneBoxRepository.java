package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.SpedizioneBox;

public interface ISpedizioneBoxRepository extends JpaRepository<SpedizioneBox, Integer>{
	@Query(name = "spedizioneBox.searchWithParameters")
	List<SpedizioneBox> searchWithParameters(
	    @Param("corriere") String corriere, 
	    @Param("codice_tracciamento") String codice_tracciamento,
	    @Param("id_cantina") Integer id_cantina, 
	    @Param("id_cliente") Integer id_cliente,
	    @Param("id_status") Integer id_status,
	    @Param("id_ordine_box") Integer id_ordine_box
	);

	void deleteByCantina_Id(Integer id);

	void deleteByCliente_Id(Integer id);
}
