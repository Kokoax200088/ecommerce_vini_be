package com.betacom.ec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	@Query(name="cliente.searchByFilter")
	List<Cliente> searchByFilter(
			@Param("indirizzo") String indirizzo
			);

	@Query("select c.id from Cliente c where c.utente.email = :email")
	Optional<Integer> findIdByUtenteEmail(@Param("email") String email);
	
	@Query("select c.id from Cliente c where c.utente.id = :idUtente")
	Optional<Integer> findIdByUtenteId(@Param("idUtente") Integer idUtente);
}