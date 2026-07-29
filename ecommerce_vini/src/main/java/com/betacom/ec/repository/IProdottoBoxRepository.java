package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ProdottoAlcolico;
import com.betacom.ec.models.ProdottoBox;

public interface IProdottoBoxRepository extends JpaRepository<ProdottoBox, Integer>{
	
}
