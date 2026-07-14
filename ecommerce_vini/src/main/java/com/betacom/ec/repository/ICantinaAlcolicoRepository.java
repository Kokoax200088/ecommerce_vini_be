package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.CantinaAlcolico;

@Repository
public interface ICantinaAlcolicoRepository extends JpaRepository<CantinaAlcolico, Integer>{

}
