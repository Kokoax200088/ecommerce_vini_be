package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.ec.models.Caratteristica;

public interface ICaratteristicaRepository extends JpaRepository<Caratteristica, Integer> {
}
