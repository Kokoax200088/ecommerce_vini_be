package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Integer> {

}
