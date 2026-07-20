package com.betacom.ec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Integer> {
	@Query(name="utente.searchByFilter")
	List<Utente> searchByFilter(
			@Param("nome") String nome,
			@Param("cognome") String cognome,
			@Param("email") String email,
			@Param("dataNascita") String dataNascita, //CHECK se funziona come stringa tbh
			@Param("ruolo") String ruolo
			);
	
	Optional<Utente> findByEmail(String email); //IN TEORIA fa in automatico perchè è una query generata in base all'attributo email
}
