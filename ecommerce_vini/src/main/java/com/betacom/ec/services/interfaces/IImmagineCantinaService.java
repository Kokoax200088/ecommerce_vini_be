package com.betacom.ec.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.betacom.ec.dto.input.ImmagineCantinaReq;
import com.betacom.ec.dto.output.ImmagineCantinaDTO;

public interface IImmagineCantinaService {
	public void create(MultipartFile file, Integer id_cantina) throws Exception;
	public void update(ImmagineCantinaReq req) throws Exception;
	public void delete(Integer id) throws Exception;
	
	public List<ImmagineCantinaDTO> listBySearchString(Integer idCantina) throws Exception;
	public ImmagineCantinaDTO getById (Integer id) throws Exception;
}
