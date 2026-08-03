package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.CantinaAlcolicoReq;
import com.betacom.ec.dto.output.CantinaAlcolicoDTO;

public interface ICantinaAlcolicoService {
	public CantinaAlcolicoDTO create(CantinaAlcolicoReq req) throws Exception;
	public void update(CantinaAlcolicoReq req) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<CantinaAlcolicoDTO> listBySearchString(Integer idCantina, Integer idAlcolico) throws Exception;
	public CantinaAlcolicoDTO getById (Integer id) throws Exception;
}
