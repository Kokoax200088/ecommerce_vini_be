package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.ec.models.MessageID;
import com.betacom.ec.models.Messaggi;

public interface IMessaggioRepository extends JpaRepository<Messaggi, MessageID>{
}