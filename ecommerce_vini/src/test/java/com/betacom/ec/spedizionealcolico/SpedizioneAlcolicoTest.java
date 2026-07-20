package com.betacom.ec.spedizionealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.SpedizioneAlcolicoController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.prodottobox.ProdottoBoxTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioneAlcolicoTest {
	@Autowired
	private SpedizioneAlcolicoController saC;
	
	@Autowired
    private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private StatusController sC;
	
	@Test
	@Order(1)
	public void createRuoloUser() throws Exception{ 
		log.debug("createRuolo User");
		
		RuoloRequest req = new RuoloRequest();
		req.setId(1);
		req.setNome("user");
		req.setCanManage(false);
		req.setCanBuy(true);
		req.setCanSell(false);
		 mockMvc.perform(post("/rest/api/ruolo/create")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(req))
	                ).andExpect(status().isOk());
	}
    
    @Test
    @Order(2)
    public void createCliente() throws Exception {
        log.debug("createCliente Test");
        
        ClienteRequest req = new ClienteRequest();
        req.setNome("Mario");
		req.setCognome("Rossi");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(1); 
		req.setPassword("abete1");
		
		req.setIndirizzo("via Roma, 1 Torino TO");
        
        mockMvc.perform(post("/rest/api/cliente/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    
    @Test
    @Order(4)
    public void createRuoloSeller() throws Exception {
        log.debug("createRuolo Seller");
        
        RuoloRequest req = new RuoloRequest();
        req.setId(2);
        req.setNome("seller");
        req.setCanManage(false);
        req.setCanBuy(false);
        req.setCanSell(true);
        
        mockMvc.perform(post("/rest/api/ruolo/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void createVenditore() throws Exception {
        log.debug("createVenditore Test");
        
        VenditoreRequest req = new VenditoreRequest();
        req.setId(1);
        req.setNome("Caio Maio");
        req.setDataNascita("08/08/1996");
        req.setCognome("Ilario");
        req.setEmail("c.maio@gmail.com");
        req.setIdRuolo(2);
        req.setPartitaIva("A99");
        
        mockMvc.perform(post("/rest/api/venditore/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    @Test
    @Order(6)
    public void createCantina() throws Exception {
        log.debug("createCantina Test");
        
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setVenditoreId(1);
        
        mockMvc.perform(post("/rest/api/cantina/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    
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
	@Order(3)
	public void createSpeAlcTest() throws Exception{
		log.debug("createSpeAlcTest");
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setCodice_tracciamento("who?");
		req.setCorriere("brt");
		req.setId_cantina(1);
		req.setId_cliente(1);
		req.setId_ordine_alcolico(2);
		req.setId_status(1);
			ResponseEntity<ResponseDTO> response = saC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(4)
	public void updateSpeAlcTest() throws Exception{
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setId(1);
		req.setCorriere("NO");
			ResponseEntity<ResponseDTO> response = saC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(5)
	public void listSpeAlcTest() throws Exception {
		ResponseEntity<?> response = saC.list("NO",null, null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<SpedizioneAlcolicoDTO> lS= (List<SpedizioneAlcolicoDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (6)
	public void getByIdTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = saC.getSpedizioneAlcolicoById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		SpedizioneAlcolicoDTO dto = (SpedizioneAlcolicoDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(7)
	public void deleteSpeAlcTest() throws Exception {
		ResponseEntity<ResponseDTO> response = saC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(8)
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
