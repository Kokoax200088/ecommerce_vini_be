package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Box;

@Repository
public interface IBoxRepository extends JpaRepository<Box, Integer>{

}
