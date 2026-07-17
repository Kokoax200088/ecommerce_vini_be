package com.betacom.ec.ordine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.utils.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineTest {
	
	@Autowired
	private OrdineController oC;
	
	@Test
	@Order(1)
	public void createOrdineTest() throws Exception{
		OrdineReq req = new OrdineReq();
		req.setData_ordine(Utilities.stringToDate("19/03/2020"));
		req.setIndirizzoDestinazione("Via testing");
		req.setTotale(50.0);
		req.setId_utente(1);
		req.setId_status(2);
		ResponseEntity<ResponseDTO> response = oC.create(req);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void updateOrdineTest() throws Exception{
		OrdineReq req = new OrdineReq();
		req.setId(1);
		req.setIndirizzoDestinazione("Via testingn't");
		ResponseEntity<ResponseDTO> response = oC.update(req);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void listOrdineTest() throws Exception {
		ResponseEntity<?> response = oC.list(null, 50.0,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<StatusDTO> lS= (List<StatusDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (4)
	public void getByIdOrdineTest() throws Exception {
		log.debug("getByIdOrdineTest");
		
		ResponseEntity<Object> response = oC.getOrdineById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		StatusDTO dto = (StatusDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(5)
	public void deleteOrdineTest() throws Exception {
		ResponseEntity<ResponseDTO> response = oC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@Order(6)
	public void createOrdine2Test() throws Exception{
		OrdineReq req = new OrdineReq();
		req.setData_ordine(Utilities.stringToDate("19/03/2020"));
		req.setIndirizzoDestinazione("Via testing");
		req.setTotale(50.0);
		req.setId_utente(1);
		req.setId_status(2);
		ResponseEntity<ResponseDTO> response = oC.create(req);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
