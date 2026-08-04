package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Box;

@Repository
public interface IBoxRepository extends JpaRepository<Box, Integer>{
	@Query (name="box.searchByFilter")
	List<Box> searchByFilter( 
			@Param("nome") String nome,
			@Param("id_cantina") Integer id_cantina
			);
	
	void deleteByCantina_Id(Integer idCantina);
}
