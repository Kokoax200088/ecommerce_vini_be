package com.betacom.ec.spedizionebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.ec.EcommerceViniApplication;
import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.OrdineAlcolicoController;
import com.betacom.ec.controllers.OrdineController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.SpedizioneAlcolicoController;
import com.betacom.ec.controllers.SpedizioneBoxController;
import com.betacom.ec.controllers.StatusController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.SpedizioneBoxReq;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.dto.output.SpedizioneBoxDTO;
import com.betacom.ec.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=EcommerceViniApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpedizioneBoxTest {
	@Autowired
	private SpedizioneBoxController sbC;
	
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
		req.setEmail("mario.rossia@tiscali.net");
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
        req.setEmail("casa.maio@gmail.com");
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
    public void createBox() throws Exception {
        log.debug("createBox Test");
        
        BoxReq req = new BoxReq();
        req.setNome("Boxlandia");
        req.setCantinaId(2);
        mockMvc.perform(post("/rest/api/box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isCreated());
    }
	
	@Test
	@Order(8)
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
	@Order(9)
	public void createOrdineBoxTest() throws Exception {
		log.debug("createOrdineBoxTest");
		OrdineBoxRequest req = new OrdineBoxRequest();
		req.setOrdineId(2);
		req.setBoxId(1);
		req.setStatusId(3);
		req.setCantinaId(3);
		req.setQuantita(1);
		mockMvc.perform(post("/rest/api/ordine-box/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}
	@Test
	@Order(10)
	public void createSpedizioneBoxTest() throws Exception{
		log.debug("createSpeBoxTest");
		SpedizioneBoxReq req = new SpedizioneBoxReq();
		req.setCodice_tracciamento("no traccia");
		req.setCorriere("corriere");
		req.setId_ordbox(1);
		req.setId_cantina(3);
		req.setId_cliente(2);
		req.setId_status(4);
			ResponseEntity<ResponseDTO> response = sbC.create(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(11)
	public void updateSpedizioneBoxTest() throws Exception{
		SpedizioneBoxReq req = new SpedizioneBoxReq();
		req.setId(1);
		req.setCodice_tracciamento("UPDATE");
			ResponseEntity<ResponseDTO> response = sbC.update(req);
			assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Order(12)
	public void listSpedizioneBoxTest() throws Exception {
		ResponseEntity<?> response = sbC.list(null,"UPDATE",null,null,null,null,null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		List<SpedizioneBoxDTO> lS= (List<SpedizioneBoxDTO>) response.getBody(); //non ho capito il warning
		Assertions.assertThat(lS.size()).isGreaterThan(0);
		
		lS.forEach(item -> log.debug(item.toString()));
	}
	
	@Test
	@Order (13)
	public void getByIdSpedizioneBoxTest() throws Exception {
		log.debug("getByIdTest");
		
		ResponseEntity<Object> response = sbC.getSpedizioneBoxById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		SpedizioneBoxDTO dto = (SpedizioneBoxDTO)response.getBody();
		log.debug(dto.toString());
	}
	
	@Test
	@Order(14)
	public void deleteSpedizioneBoxTest() throws Exception {
		ResponseEntity<ResponseDTO> response = sbC.delete(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
