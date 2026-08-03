package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;  // <-- JPA Query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.CantinaAlcolico;

@Repository
public interface ICantinaAlcolicoRepository extends JpaRepository<CantinaAlcolico, Integer>{

	@Query(name="cantinaAlcolico.searchByFilter")
	List<CantinaAlcolico> searchByFilter(
			@Param("idCantina") Integer idCantina,
			@Param("idAlcolico") Integer idAlcolico
			);
	
	void deleteByCantina_Id(Integer idCantina);
}
