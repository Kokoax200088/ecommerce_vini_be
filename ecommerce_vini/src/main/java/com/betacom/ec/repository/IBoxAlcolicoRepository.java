package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.BoxAlcolico;

@Repository
public interface IBoxAlcolicoRepository extends JpaRepository<BoxAlcolico, Integer>{

}
