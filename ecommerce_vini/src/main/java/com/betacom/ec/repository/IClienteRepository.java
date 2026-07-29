package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	@Query(name="cliente.searchByFilter")
	List<Cliente> searchByFilter(
			@Param("indirizzo") String indirizzo
			);
}
