package com.betacom.ec.services.interfaces;

import com.betacom.ec.dto.input.ProdottoAlcolicoReq;

public interface IProdottoAlcolicoService {
	void create(ProdottoAlcolicoReq req) throws Exception;
	void delete(Integer id) throws Exception;
	
	void list();
	void getById(Integer id) throws Exception;

}
