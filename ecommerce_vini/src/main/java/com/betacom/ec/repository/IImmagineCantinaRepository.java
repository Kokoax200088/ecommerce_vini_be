package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ImmagineCantina;

public interface IImmagineCantinaRepository extends JpaRepository<ImmagineCantina, Integer> {
	List<ImmagineCantina> searchByFilter(
			@Param("idCantina") Integer idCantina
			);
}
