package com.betacom.ec.spedizionealcolico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.OrdineAlcolicoController;
import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.SpedizioneAlcolicoController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.CaratteristicaReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.OrdineAlcolicoReq;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.SpedizioneAlcolicoReq;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneAlcolicoDTO;
import com.betacom.ec.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioneAlcolicoTest {
	@Autowired
	private SpedizioneAlcolicoController saC;
	
	@Autowired
	private OrdineAlcolicoController oaC;
	
	@Autowired
	private OrdineController oC;
	
	@Autowired
	private ClienteController clienteController;
	
	
	@Autowired
    private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private StatusController sC;
	
	@Autowired
	private RuoloController ruoloController;
	
	@Autowired
	private VenditoreController vController;
	
	
	@Test
	@Order(1)
	@WithMockUser(roles = "ADMIN")
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
    @Order(2)
    @WithMockUser(roles = "ADMIN")
    public void createCliente (){
		log.debug("createCliente");
		
		ClienteRequest req = new ClienteRequest();
		req.setNome("Mario");
		req.setCognome("Rossi");
		req.setDataNascita("21/11/2005");
		req.setEmail("mario.rossi@tiscali.net");
		req.setIdRuolo(1);
		req.setPassword("abete1");
		
		req.setIndirizzo("via Roma, 1 Torino TO");
		
		try {
			ResponseEntity<ResponseDTO> response = clienteController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
	}
    
    @Test
    @Order(3)
    @WithMockUser(roles = "ADMIN")
    public void createRuoloSeller() throws Exception {
        log.debug("createRuolo Seller");
        
        RuoloRequest req = new RuoloRequest();
        req.setId(2);
        req.setNome("seller");
        req.setCanManage(false);
        req.setCanBuy(false);
        req.setCanSell(true);
        
        try {
			ResponseEntity<ResponseDTO> response = ruoloController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
    }

    @Test
    @Order(4)
    @WithMockUser(roles = "ADMIN")
    public void createVenditore() throws Exception {
        log.debug("createVenditore Test");
        
        VenditoreRequest req = new VenditoreRequest();
        req.setNome("Caio Maio");
        req.setDataNascita("08/08/1996");
        req.setCognome("Ilario");
        req.setEmail("ca.maio@gmail.com");
        req.setIdRuolo(2);
        req.setPartitaIva("A99");
        req.setPassword("a");
        try {
			ResponseEntity<ResponseDTO> response = vController.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			throw new AssertionError("Errore: " + e.getMessage());
		}
    }

    @Test
    @Order(5)
    @WithMockUser(roles = "ADMIN")
    public void createCantina() throws Exception {
        log.debug("createCantina Test");
        
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Villa Turistica");
        req.setVenditoreId(1);
        req.setPosizione("indirizzo");
        mockMvc.perform(post("/rest/api/cantina/create")
        		.with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isCreated());
    }
    
    @Test
	@Order(6)
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
	@Order(7)
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
	@Order(8)
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
	@Order(9)
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
	@Order(10)
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
	
	@Test
	@Order(11)
	public void createOrdineTest() throws Exception{
		OrdineReq req = new OrdineReq();
		req.setData_ordine(Utilities.stringToDate("19/03/2020"));
		req.setIndirizzoDestinazione("Via testing");
		req.setTotale(50.0);
		req.setId_utente(1);
		req.setId_status(3);
		ResponseEntity<ResponseDTO> response = oC.create(req);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(12)
	@WithMockUser(roles = "ADMIN")
	public void createOrdAlcTest() throws Exception{
		log.debug("createOrdAlcTest");
		OrdineAlcolicoReq req = new OrdineAlcolicoReq();
		req.setData_ordine(Utilities.stringToDate("17/07/2026"));
		req.setQuantita(2);
		req.setAlcolicoId(2);
		req.setCantinaId(2);
		req.setOrdineId(2);
		req.setStatusId(3);
			ResponseEntity<ResponseDTO> response = oaC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	@Order(13)
	@WithMockUser(roles = "ADMIN")
	public void createSpeAlcTest() throws Exception{
		log.debug("createSpeAlcTest");
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setCodice_tracciamento("who?");
		req.setCorriere("brt");
		req.setId_cantina(2);
		req.setId_cliente(1);
		req.setId_ordine_alcolico(2);
		req.setId_status(3);
			ResponseEntity<ResponseDTO> response = saC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(14)
	@WithMockUser(roles = "ADMIN")
	public void updateSpeAlcTest() throws Exception{
		SpedizioneAlcolicoReq req = new SpedizioneAlcolicoReq();
		req.setId(1);
		req.setCorriere("NO");
			ResponseEntity<ResponseDTO> response = saC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(15)
	@WithMockUser(roles = "ADMIN")
	public void listSpeAlcTest() throws Exception {
		ResponseEntity<?> response = saC.list("NO",null, null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<SpedizioneAlcolicoDTO> lS= (List<SpedizioneAlcolicoDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	/*@Test
	@Order (16)
	@WithMockUser(roles = "ADMIN")
	public void getByIdTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = null; //= saC.getSpedizioneAlcolicoById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		SpedizioneAlcolicoDTO dto = (SpedizioneAlcolicoDTO)response.getBody();
		log.debug(dto.toString());
	}
	*/
	@Test
	@Order(17)
	@WithMockUser(roles = "ADMIN")
	public void deleteSpeAlcTest() throws Exception {
		ResponseEntity<ResponseDTO> response = saC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
