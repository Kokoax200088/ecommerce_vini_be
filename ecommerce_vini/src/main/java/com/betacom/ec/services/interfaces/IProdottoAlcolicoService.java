package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.input.ProdottoAlcolicoReq;
import com.betacom.ec.dto.output.ProdottoAlcolicoDTO;

public interface IProdottoAlcolicoService {
	void create(ProdottoAlcolicoReq req) throws Exception;
	void update(ProdottoAlcolicoReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<ProdottoAlcolicoDTO> list();
	ProdottoAlcolicoDTO getById(Integer id) throws Exception;

}
