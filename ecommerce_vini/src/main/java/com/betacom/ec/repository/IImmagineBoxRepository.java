package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ImmagineBox;

public interface IImmagineBoxRepository extends JpaRepository<ImmagineBox, Integer>{
	@Query(name = "immagineBox.searchWithParameters")
	List<ImmagineBox> searchByFilter(
			@Param("idBox") Integer idBox
			);
}
