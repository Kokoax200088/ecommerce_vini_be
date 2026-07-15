package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.services.interfaces.IBoxService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoxImpl implements IBoxService{

	@Override
	public void create(BoxReq req) throws Exception {
		log.debug("create box{}", req);
		
	}

	@Override
	public void update(BoxReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoxDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoxDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
