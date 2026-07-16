package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Cantina;

@Repository
public interface ICantinaRepository extends JpaRepository<Cantina, Integer> {
	@Query(name="cantina.searchByFilter")
	List<Cantina> searchByFilter(
			@Param("nome") String nome,
			@Param("idVenditore") Integer idVenditore
			);
}
