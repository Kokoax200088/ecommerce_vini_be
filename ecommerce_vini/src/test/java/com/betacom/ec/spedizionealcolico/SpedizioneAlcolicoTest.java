package com.betacom.ec.spedizionealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.betacom.ec.controllers.SpedizioneAlcolicoController;
import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.ordinealcolico.OrdineAlcolicoTest;
import com.betacom.ec.utils.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpedizioneAlcolicoTest {
	@Autowired
	private SpedizioneAlcolicoController saC;
	
	@Test
	@Order(1)
	public void createSpeAlcTest() throws Exception{
		log.debug("createSpeAlcTest");
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setCodice_tracciamento("who?");
		req.setCorriere("brt");
		req.setId_cantina(1);
		req.setId_cliente(1);
		req.setId_ordine_alcolico(2);
		req.setId_status(2);
			ResponseEntity<ResponseDTO> response = saC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void updateSpeAlcTest() throws Exception{
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setId(1);
		req.setCorriere("NO");
			ResponseEntity<ResponseDTO> response = saC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void listSpeAlcTest() throws Exception {
		ResponseEntity<?> response = saC.list("NO",null, null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<SpedizioneAlcolicoDTO> lS= (List<SpedizioneAlcolicoDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (4)
	public void getByIdTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = saC.getSpedizioneAlcolicoById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		SpedizioneAlcolicoDTO dto = (SpedizioneAlcolicoDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(5)
	public void deleteSpeAlcTest() throws Exception {
		ResponseEntity<ResponseDTO> response = saC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(6)
	public void createSpeAlc2Test() throws Exception{
		log.debug("createSpeAlc2Test");
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setCodice_tracciamento("who?");
		req.setCorriere("brt");
		req.setId_cantina(1);
		req.setId_cliente(1);
		req.setId_ordine_alcolico(2);
		req.setId_status(2);
			ResponseEntity<ResponseDTO> response = saC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
