package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.OrdineAlcolico;

public interface IOrdineAlcolicoRepository extends JpaRepository<OrdineAlcolico, Integer>{
	@Query (name="ordineAlcolico.searchWithParameters")
	List<OrdineAlcolico> searchWithParameters(@Param ("quantita") Integer quantita,
			@Param("id_ordine") Integer id_ordine,
			@Param("id_status") Integer id_status,
			@Param("id_alcolico") Integer id_alcolico,
			@Param("id_cantina") Integer id_cantina);
}
