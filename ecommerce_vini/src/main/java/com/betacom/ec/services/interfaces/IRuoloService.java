package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.output.RuoloDTO;

public interface IRuoloService {
	public void create(RuoloRequest ruoloRequest) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public RuoloDTO getById(Integer id) throws Exception;
	public List<RuoloDTO> listAll() throws Exception;
}
