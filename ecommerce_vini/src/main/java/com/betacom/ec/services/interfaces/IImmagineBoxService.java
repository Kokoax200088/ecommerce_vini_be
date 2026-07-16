package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ImmagineBoxReq;
import com.betacom.ec.dto.output.ImmagineBoxDTO;

public interface IImmagineBoxService {
	void create(ImmagineBoxReq req) throws Exception;
	void update(ImmagineBoxReq req)throws Exception;
	void delete(Integer id)throws Exception;
	
	List<ImmagineBoxDTO> list();
	ImmagineBoxDTO getById(Integer id)throws Exception;
}
