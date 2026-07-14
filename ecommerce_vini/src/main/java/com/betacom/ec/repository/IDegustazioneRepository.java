package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.ec.models.Degustazione;

@Repository
public interface IDegustazioneRepository extends JpaRepository<Degustazione, Integer> {

}
