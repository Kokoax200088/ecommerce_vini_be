package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.ec.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer>{

}
