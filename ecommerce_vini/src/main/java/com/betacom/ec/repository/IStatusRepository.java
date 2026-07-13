package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.ec.models.Status;

public interface IStatusRepository extends JpaRepository<Status, Integer>{

}
