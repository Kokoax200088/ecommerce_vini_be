package com.betacom.ec.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ProdottoDegustazione;

public interface IProdottoDegustazioneRepository extends JpaRepository<ProdottoDegustazione, Integer>{
	
	@Query(name="prodottoDegustazione.searchByFilter")
	List<ProdottoDegustazione> searchByFilter(@Param("idDegustazione")Integer id_degustazione,@Param("idCarrello") Integer id_carrello);

	void deleteByCantina_Id(Integer id);

	void deleteByDegustazione_Id(Integer id_degustazione);
}
