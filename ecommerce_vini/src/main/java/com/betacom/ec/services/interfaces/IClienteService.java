package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.RatingAlcolicoReq;
import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.output.ClienteDTO;

public interface IClienteService {
	public void create(ClienteRequest clienteRequest) throws Exception;
	public void update(ClienteRequest clienteRequest) throws Exception;
	public void delete(Integer id) throws Exception;

	public void addRatingAlcolico(RatingAlcolicoReq req) throws Exception;
	public void addRatingCantina(RatingCantinaReq req) throws Exception;
	
	public List<ClienteDTO> listBySearchString(String indirizzoSearch) throws Exception;
	public ClienteDTO getById(Integer id) throws Exception;
}
