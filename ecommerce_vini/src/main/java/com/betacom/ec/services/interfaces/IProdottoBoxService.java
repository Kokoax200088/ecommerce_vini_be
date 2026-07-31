package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ProdottoBoxReq;
import com.betacom.ec.dto.output.ProdottoBoxDTO;

public interface IProdottoBoxService {
	void create(ProdottoBoxReq req) throws Exception;
	void update(ProdottoBoxReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	 List<ProdottoBoxDTO> list();
	 List<ProdottoBoxDTO> searchByFilter(Integer idBox, Integer idCarrello);
	 ProdottoBoxDTO getById(Integer id) throws Exception;
}
