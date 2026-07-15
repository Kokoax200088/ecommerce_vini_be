package com.betacom.ec.services.interfaces;

import java.util.List;

import com.betacom.ec.dto.output.ColoreDTO;

public interface IColoreService {

	List<ColoreDTO> listAll();

	ColoreDTO getById(Integer id) throws Exception;
}
