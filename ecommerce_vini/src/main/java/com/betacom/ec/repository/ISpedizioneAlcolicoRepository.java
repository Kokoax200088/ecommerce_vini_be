package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.SpedizioneAlcolico;

public interface ISpedizioneAlcolicoRepository extends JpaRepository<SpedizioneAlcolico, Integer>{
		@Query(name="spedizioneAlcolico.searchWithParameters")
		List<SpedizioneAlcolico> searchWithParameters(
				@Param("corriere") String corriere,
				@Param("codice_tracciamento") String codice_tracciamento,
				@Param("id_cantina") Integer id_cantina,
				@Param("id_ordine_alcolico") Integer id_ordine_alcolico,
				@Param("id_status") Integer id_status,
				@Param("id_cliente") Integer id_cliente,
				@Param("id_venditore") Integer id_venditore); // <-- nuovo
	void deleteByCantina_Id(Integer id);

	void deleteByCliente_Id(Integer id);
}
