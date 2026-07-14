package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Cantina;

@Repository
public interface ICantinaRepository extends JpaRepository<Cantina, Integer> {

}
