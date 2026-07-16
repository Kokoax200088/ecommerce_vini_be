package com.betacom.ec.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.betacom.ec.models.ImmagineDegustazione;

public interface IImmagineDegustazioneRepository extends JpaRepository<ImmagineDegustazione, Integer>{
	
	@Query (name="imgdeg.searchWithParameters")
	List<ImmagineDegustazione> searchWithParameters(@Param ("id_cantina") Integer id_cantina );
}
