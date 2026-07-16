package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.output.CantinaDTO;

public interface ICantinaService {
	public void create(CantinaReq req) throws Exception;
	public void update(CantinaReq req) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<CantinaDTO> listBySearchString(String cantinaSearch, Integer idVenditoreSearch) throws Exception;
	public CantinaDTO getById (Integer id) throws Exception;
}
