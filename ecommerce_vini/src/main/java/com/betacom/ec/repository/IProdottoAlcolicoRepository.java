package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ProdottoAlcolico;

public interface IProdottoAlcolicoRepository extends JpaRepository<ProdottoAlcolico, Integer>{
	@Query(name = "prodottoAlcolico.searchByFilter")
	List<ProdottoAlcolico> searchByFilter(@Param("idAlcolico") Integer idAlcolico, @Param("idCarrello") Integer idCarrello);

	void deleteByAlcolico_Id(Integer id_alcolico);

	void deleteByCantina_Id(Integer id);
}
