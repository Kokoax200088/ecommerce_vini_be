package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RatingCantinaReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.RatingAlcolicoDTO;
import com.betacom.ec.services.interfaces.IRatingCantinaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class RatingCantinaImpl implements IRatingCantinaService{

	@Transactional
	@Override
	public void create(RatingCantinaReq req) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public List<RatingAlcolicoDTO> list(CantinaReq alcReq, UtenteRequest utReq, Integer valutazione) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public RatingAlcolicoDTO getById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
