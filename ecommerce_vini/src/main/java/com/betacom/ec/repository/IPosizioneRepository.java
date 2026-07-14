package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Posizione;

@Repository
public interface IPosizioneRepository  extends JpaRepository <Posizione, Integer> {
	
}
