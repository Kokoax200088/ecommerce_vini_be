package com.betacom.ec.spedizionebox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.ec.EcommerceViniApplication;
import com.betacom.ec.controllers.SpedizioneBoxController;
import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=EcommerceViniApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioneBoxTest {
	@Autowired
	private SpedizioneBoxController sbC;
	
	
	@Test
	@Order(1)
	public void createSpedizioneBoxTest() throws Exception{
		log.debug("createSpeBoxTest");
		SpedizioneBoxReq req = new SpedizioneBoxReq();
		req.setCodice_tracciamento("no traccia");
		req.setCorriere("corriere");
		req.setId_box(2);
		req.setId_cantina(2);
		req.setId_cliente(2);
		req.setId_status(2);
			ResponseEntity<ResponseDTO> response = sbC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void updateSpedizioneBoxTest() throws Exception{
		SpedizioneBoxReq req = new SpedizioneBoxReq();
		req.setId(1);
		req.setCodice_tracciamento("UPDATE");
			ResponseEntity<ResponseDTO> response = sbC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void listSpedizioneBoxTest() throws Exception {
		ResponseEntity<?> response = sbC.list(null,"UPDATE",null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<SpedizioneBoxDTO> lS= (List<SpedizioneBoxDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (4)
	public void getByIdSpedizioneBoxTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = sbC.getSpedizioneBoxById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		SpedizioneBoxDTO dto = (SpedizioneBoxDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(5)
	public void deleteSpedizioneBoxTest() throws Exception {
		ResponseEntity<ResponseDTO> response = sbC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(6)
	public void createSpedizioneBox2Test() throws Exception{
		log.debug("createSpeBox2Test");
		SpedizioneBoxReq req = new SpedizioneBoxReq();
		req.setCodice_tracciamento("no traccia");
		req.setCorriere("corriere");
		req.setId_box(2);
		req.setId_cantina(2);
		req.setId_cliente(2);
		req.setId_status(2);
			ResponseEntity<ResponseDTO> response = sbC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
