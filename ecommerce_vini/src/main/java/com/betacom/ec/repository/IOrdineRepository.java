package com.betacom.ec.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer>{
	@Query (name="ordine.searchWithParameters")
	List<Ordine> searchWithParameters(@Param ("data") LocalDate data,
			@Param("totale") Double totale,
			@Param("id_status") Integer id_status,
			@Param("id_utente") Integer id_utente,
			@Param("indirizzo_destinazione") String indirizzo_destinazione);
	
	@Query(name = "ordine.searchByVenditore")
	List<Ordine> searchByVenditore(
			@Param("data") LocalDate data,
			@Param("totale") Double totale,
			@Param("id_status") Integer id_status,
			@Param("id_utente") Integer id_utente,
			@Param("indirizzo_destinazione") String indirizzo_destinazione,
			@Param("id_venditore") Integer id_venditore);
}