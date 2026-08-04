package com.betacom.ec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Venditore;

public interface IVenditoreRepository extends JpaRepository<Venditore, Integer> {
		Venditore findByUtente_email(String email);
		
		
		@Query("SELECT v.id FROM Venditore v WHERE v.utente.email = :email")
		Optional<Integer> findIdByUtenteEmail(@Param("email") String email);
}
