package com.betacom.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.ec.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
