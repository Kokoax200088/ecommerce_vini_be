package com.betacom.ec.ordinealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.controllers.OrdineAlcolicoController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CaratteristicaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.dto.output.PosizioneDTO;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.services.interfaces.IPosizioneService;
import com.betacom.ec.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineAlcolicoTest {
	
	@Autowired
	private OrdineAlcolicoController oaC;
	
	@Autowired
	private StatusController sC;
	
	@Autowired
	private IPosizioneService posS;
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	@Order(1)
	@WithMockUser(roles = "ADMIN")
	public void createColoreTest() throws Exception {
		log.debug("createColoreTest");
		ColoreReq req = new ColoreReq();
		req.setNome("Rosso");
		req.setDescrizione("testDescrizione");
		mockMvc.perform(post("/rest/api/colore/create")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}
	
	@Test
	@Order(2)
	@WithMockUser(roles = "ADMIN")
	public void createTipologiaAlcolicoTest() throws Exception {
		log.debug("createTipologiaAlcolicoTest");
		TipologiaAlcolicoReq req = new TipologiaAlcolicoReq();
		req.setNome("Rosso Fermo");
		req.setDescrizione("testDescrizione");
		mockMvc.perform(post("/rest/api/tipologia-alcolico/create")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	@Order(3)
	@WithMockUser(roles = "ADMIN")
	public void createCaratteristicaTest() throws Exception {
		log.debug("createCaratteristicaTest");
		CaratteristicaReq req = new CaratteristicaReq();
		req.setNome("Corposo");
		req.setDescrizione("testDescrizione");
		mockMvc.perform(post("/rest/api/caratteristica/create")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}
	
	@Test
    @Order(4)
	@WithMockUser(roles = "ADMIN")
    public void createRuoloSeller() throws Exception {
        log.debug("createRuolo Seller");
        
        RuoloRequest req = new RuoloRequest();
        req.setId(2);
        req.setNome("seller");
        req.setCanManage(false);
        req.setCanBuy(false);
        req.setCanSell(true);
        
        mockMvc.perform(post("/rest/api/ruolo/create")
        		.with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @WithMockUser(roles = "ADMIN")
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
        req.setPassword("venditore1");
        mockMvc.perform(post("/rest/api/venditore/create")
        		.with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }
    @Test
    @Order(6)
    @WithMockUser(roles = "ADMIN")
    public void createPosizioneTest() {
        try {
            PosizioneReq req = new PosizioneReq();
            req.setId(1);
            req.setLatitudine(45.5);
            req.setLongitudine(9.5);
            req.setDescrizione("Location Test");
            posS.create(req);
            
            List<PosizioneDTO> list = posS.listBySearchString("Location");
            assertNotNull(list);
            assertEquals(1, list.size());
            assertEquals("Location Test", list.get(0).getDescrizione());
        } catch (Exception e) {
            throw new AssertionError("Errore in createPosizioneTest: " + e.getMessage());
        }
    }
    @Test
    @Order(7)
    @WithMockUser(roles = "ADMIN")
    public void createCantina() throws Exception {
        log.debug("createCantina Test");
        
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setVenditoreId(1);
        req.setPosizioneId(1);
        mockMvc.perform(post("/rest/api/cantina/create")
        		.with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isCreated());
    }
	
    @Test
	@Order(8)
    @WithMockUser(roles = "ADMIN")
	public void createStatusTest() throws Exception{
		log.debug("createStatusTest");
		StatusReq req = new StatusReq();
		req.setDescrizione("testDescrizione");
		req.setNome("Test");
			ResponseEntity<ResponseDTO> response = sC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
    @Test
	@Order(9)
	@WithMockUser(roles = "ADMIN")
	public void createAlcolicoTest() throws Exception {
		log.debug("createAlcolicoTest");
		AlcolicoReq req = new AlcolicoReq();
		req.setId_venditore(1);
		req.setNome("TestVino");
		req.setId_tipologia_alcolico(1);
		req.setId_colore(1);
		req.setPrezzo(10.0);
		mockMvc.perform(post("/rest/api/alcolico/create")
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}
    //TODO CAMBIARE I NUMERI 
	@Test
	@Order(10)
	@WithMockUser(roles = "ADMIN")
	public void createOrdAlcTest() throws Exception{
		log.debug("createOrdAlcTest");
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
	@Order(11)
	public void updateOrdAlcTest() throws Exception{
		OrdineAlcolicoReq req = new OrdineAlcolicoReq();
		req.setId(1);
		req.setQuantita(4);
			ResponseEntity<ResponseDTO> response = oaC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(12)
	public void listOrdAlcTest() throws Exception {
		ResponseEntity<?> response = oaC.list(4, null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<OrdineAlcolicoDTO> lS= (List<OrdineAlcolicoDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (13)
	public void getByIdTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = oaC.getOrdineAlcolicoById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		OrdineAlcolicoDTO dto = (OrdineAlcolicoDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(14)
	public void deleteOrdAlcTest() throws Exception {
		ResponseEntity<ResponseDTO> response = oaC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
