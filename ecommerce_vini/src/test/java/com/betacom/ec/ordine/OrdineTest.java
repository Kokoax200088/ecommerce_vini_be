package com.betacom.ec.ordine;

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

import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.controllers.UtenteController;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.utils.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineTest {
	
	@Autowired
	private OrdineController oC;
	
	@Autowired
	private StatusController sC;
	 
	@Autowired
	private UtenteController utenteController;
    
	@Autowired
	private RuoloController ruoloController;
	
	@Test
	@Order(1)
	public void createRuoloUser() { //crea user
		log.debug("createRuolo User");
		
		RuoloRequest req = new RuoloRequest();
		req.setNome("user");
		req.setCanManage(false);
		req.setCanBuy(true);
		req.setCanSell(false);
		
		try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
	
    @Test
	@Order (2)
	public void createUtenteUser(){
		log.debug("createUtente admin role");
		
		UtenteRequest req = new UtenteRequest();
		req.setNome("Luigi");
		req.setCognome("Mangino");
		req.setDataNascita("21/11/2005");
		req.setEmail("luigi.mangino@tiscali.net");
		req.setIdRuolo(1); //ROLE USER
		req.setPassword("abete1");
		
		try {
			ResponseEntity<ResponseDTO> response = utenteController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
			
			UtenteDTO utente= (UtenteDTO)utenteController.getById(1).getBody();
			Assertions.assertThat(utente.getNome().equals("Luigi"));
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
    
    @Test
	@Order(3)
	public void createStatusTest() throws Exception{
		log.debug("createStatusTest");
		StatusReq req = new StatusReq();
		req.setDescrizione("testDescrizione");
		req.setNome("Test");
			ResponseEntity<ResponseDTO> response = sC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
    
	@Test
	@Order(4)
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
	@Order(5)
	public void updateOrdineTest() throws Exception{
		OrdineReq req = new OrdineReq();
		req.setId(1);
		req.setIndirizzoDestinazione("Via testingn't");
		req.setId_utente(1);
		ResponseEntity<ResponseDTO> response = oC.update(req);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(6)
	public void listOrdineTest() throws Exception {
		ResponseEntity<?> response = oC.list(null, 50.0,null,null,null, null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<OrdineDTO> oS= (List<OrdineDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(oS.size()).isGreaterThan(0);
		
		oS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (7)
	public void getByIdOrdineTest() throws Exception {
		log.debug("getByIdOrdineTest");
		
		ResponseEntity<Object> response = oC.getOrdineById(1,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		OrdineDTO dto = (OrdineDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(8)
	public void deleteOrdineTest() throws Exception {
		ResponseEntity<ResponseDTO> response = oC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(4)
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
