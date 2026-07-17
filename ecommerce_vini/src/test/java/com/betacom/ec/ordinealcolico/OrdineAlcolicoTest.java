package com.betacom.ec.ordinealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.betacom.ec.controllers.OrdineAlcolicoController;
import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.utils.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineAlcolicoTest {
	
	@Autowired
	private OrdineAlcolicoController oaC;
	
	@Test
	@Order(1)
	public void createOrdAlcTest() throws Exception{
		log.debug("createAbbonamentoTest");
		OrdineAlcolicoReq req = new OrdineAlcolicoReq();
		req.setData_ordine(Utilities.stringToDate("17/07/2026"));
		req.setQuantita(2);
		req.setAlcolicoId(1);
		req.setCantinaId(1);
		req.setOrdineId(2);
		req.setStatusId(2);
			ResponseEntity<ResponseDTO> response = oaC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void updateOrdAlcTest() throws Exception{
		OrdineAlcolicoReq req = new OrdineAlcolicoReq();
		req.setId(1);
		req.setQuantita(4);
			ResponseEntity<ResponseDTO> response = oaC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void listOrdAlcTest() throws Exception {
		ResponseEntity<?> response = oaC.list(4, null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<StatusDTO> lS= (List<StatusDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (4)
	public void getByIdTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = oaC.getOrdineAlcolicoById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		StatusDTO dto = (StatusDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(5)
	public void deleteOrdAlcTest() throws Exception {
		ResponseEntity<ResponseDTO> response = oaC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
