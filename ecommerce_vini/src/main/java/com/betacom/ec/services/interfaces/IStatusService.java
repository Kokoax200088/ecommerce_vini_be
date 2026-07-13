package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.StatusRequest;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.exception.EcommerceVinoException;

public interface IStatusService {
	void create(StatusRequest req) throws EcommerceVinoException;

	void update(StatusRequest req) throws EcommerceVinoException;

	void remove(Integer id_status) throws EcommerceVinoException;
	
	List<StatusRequest> listWithParameters();

	StatusDTO getById(Integer id_status) throws EcommerceVinoException;
}
