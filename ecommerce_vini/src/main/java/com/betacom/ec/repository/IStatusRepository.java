package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.Status;

public interface IStatusRepository extends JpaRepository<Status, Integer>{
	@Query (name="status.searchWithParameters")
	List<Status> listWithParameters(@Param ("nome") String nome,
			@Param("descrizione") String descrizione);
}
