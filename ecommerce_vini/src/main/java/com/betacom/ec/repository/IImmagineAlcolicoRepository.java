package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ImmagineAlcolico;

public interface IImmagineAlcolicoRepository extends JpaRepository<ImmagineAlcolico, Integer> {
	@Query(name = "immagineAlcolico.searchWithParameters")
	List<ImmagineAlcolico> searchByFilter(
			@Param("idAlcolico") Integer idAlcolico
			);

	void deleteByAlcolico_Id(Integer id_alcolico);
}
