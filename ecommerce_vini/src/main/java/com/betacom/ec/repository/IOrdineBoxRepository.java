package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.OrdineBox;

public interface IOrdineBoxRepository extends JpaRepository<OrdineBox, Integer>{
	@Query("SELECT DISTINCT ob FROM OrdineBox ob WHERE (:quantita IS NULL OR ob.quantita = :quantita) AND (:id_ordine IS NULL OR ob.ordine.id = :id_ordine) AND (:id_status IS NULL OR ob.status.id = :id_status) AND (:id_box IS NULL OR ob.box.id = :id_box) AND (:id_cantina IS NULL OR ob.cantina.id = :id_cantina)")
	List<OrdineBox> searchWithParameters(@Param ("quantita") Integer quantita,
			@Param("id_ordine") Integer id_ordine,
			@Param("id_status") Integer id_status,
			@Param("id_box") Integer id_box,
			@Param("id_cantina") Integer id_cantina);
}
