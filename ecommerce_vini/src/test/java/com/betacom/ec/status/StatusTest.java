package com.betacom.ec.status;

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
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.StatusDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=EcommerceViniApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatusTest {

	
	//private ObjectMapper objectMapper = new ObjectMapper(); non viene usato???????
	
	@Autowired
	private StatusController sC;
	
	
	@Test
	@Order(1)
	public void createStatusTest() throws Exception{
		log.debug("createStatusTest");
		StatusReq req = new StatusReq();
		req.setDescrizione("testDescrizione");
		req.setNome("Test");
			ResponseEntity<ResponseDTO> response = sC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void updateStatusTest() throws Exception{
		StatusReq req = new StatusReq();
		req.setId(1);
		req.setNome("UPDATE");
			ResponseEntity<ResponseDTO> response = sC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void listStatusTest() throws Exception {
		ResponseEntity<?> response = sC.list("UPDATE", null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<StatusDTO> lS= (List<StatusDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (4)
	public void getByIdStatusTest() throws Exception {
		log.debug("getByIdStatusTest");
		
		ResponseEntity<Object> response = sC.getStatusById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		StatusDTO dto = (StatusDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(5)
	public void deleteStatusTest() throws Exception {
		ResponseEntity<ResponseDTO> response = sC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(6)
	public void createStatus2Test() throws Exception{
		log.debug("createStatus2Test");
		StatusReq req = new StatusReq();
		req.setDescrizione("testDescrizione");
		req.setNome("Test2");
			ResponseEntity<ResponseDTO> response = sC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
