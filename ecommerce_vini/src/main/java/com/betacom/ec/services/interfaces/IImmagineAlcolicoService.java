package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ImmagineAlcolicoReq;
import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;

public interface IImmagineAlcolicoService {
	public void create(ImmagineAlcolicoReq req) throws Exception;
	public void update(ImmagineAlcolicoReq req) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<ImmagineAlcolicoDTO> listBySearch(Integer idAlcolico) throws Exception;
	public ImmagineAlcolicoDTO getById (Integer id) throws Exception;
}
