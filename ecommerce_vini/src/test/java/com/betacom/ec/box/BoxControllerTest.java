package com.betacom.ec.box;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IClienteRepository;
import com.betacom.ec.repository.IRuoloRepository;
import com.betacom.ec.repository.IVenditoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BoxControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private ClienteController clienteC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    
    @Autowired private IBoxRepository boxRepo;
    @Autowired private IRuoloRepository ruoloRepo;
    @Autowired private IClienteRepository clienteRepo;
    @Autowired private ICantinaRepository cantinaRepo;
    @Autowired private IVenditoreRepository venditoreRepo;
    
    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per BoxController",this.getClass().getName());
        
    	RuoloRequest rUser = new RuoloRequest();
        rUser.setNome("user");
        rUser.setCanBuy(true);
        rUser.setCanManage(false);
        rUser.setCanSell(false);
        log.debug("Creazione ruolo user: {}", rUser);
        ruoloC.create(rUser);

        ClienteRequest clienteReq = new ClienteRequest();
        clienteReq.setNome("Mario");
        clienteReq.setCognome("Rossi");
        clienteReq.setEmail("mario.rossi.service@tiscali.net");
        clienteReq.setIdRuolo(ruoloRepo.findByNome("user").getId());
        clienteReq.setPassword("abete1");
        clienteReq.setIndirizzo("via Roma, 1 Torino TO");
        clienteReq.setDataNascita("21/11/2005");
        log.debug("Creazione cliente: {}", clienteReq);
        clienteC.create(clienteReq);

		RuoloRequest rSeller = new RuoloRequest();
		rSeller.setNome("seller");
		rSeller.setCanManage(false);
		rSeller.setCanBuy(false);
		rSeller.setCanSell(true);
				log.debug("Creazione ruolo seller: {}", rSeller);
		ruoloC.create(rSeller);
		
        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio.service@gmail.com");
        vendReq.setIdRuolo(ruoloRepo.findByNome("seller").getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("abete1");
        vendReq.setDataNascita("08/08/1996");
        log.debug("Creazione venditore: {}", vendReq);
        venditoreC.create(vendReq);
        
        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setNome("Cantina di Prova Service");
        //cantinaReq.setVenditoreId(venditoreRepo.findByEmail("c.maio.service@gmail.com").getId());
        cantinaReq.setPosizione("posizione");
        log.debug("Creazione cantina: {}", cantinaReq);
        cantinaC.create(cantinaReq);
        
        log.debug("Test: createBox");
        BoxReq boxReq = new BoxReq();
        boxReq.setNome("Box Degustazione Lusso");
        boxReq.setSconto(15.0);
        boxReq.setCantinaId(cantinaRepo.searchByFilter("Cantina di Prova Service", null).getFirst().getId());
    	
    	MvcResult result = mockMvc.perform(post("/rest/api/box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boxReq)))
                .andDo(log())
                .andReturn(); 
        
        int statusCode = result.getResponse().getStatus();
        assertEquals(201,result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
            
        log.debug("Setup DB completo per BoxController");
    }

    @Test
    public void updateBox() throws Exception {
    	
        log.debug("Test: updateBox");
        BoxReq req = new BoxReq();
        req.setNome("Box Degustazione Lusso Aggiornato");
        req.setSconto(25.0);
        req.setCantinaId(cantinaRepo.searchByFilter("Cantina di Prova Service", null).getFirst().getId());
        
        MvcResult result = mockMvc.perform(put("/rest/api/box/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn(); 
        
        int statusCode = result.getResponse().getStatus();
        assertEquals(200,result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
    }

    @Test
    public void getBoxById() throws Exception {
    	log.debug("Test: getBoxById");    	
    	MvcResult result = mockMvc.perform(
    			get("/rest/api/box/get/{id}",boxRepo.searchByFilter("Box Degustazione Lusso", null).getFirst().getId()))
                .andDo(log())
                .andReturn(); 
        
        int statusCode = result.getResponse().getStatus();
        assertEquals(200,result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
    }

    @Test
    public void listBox() throws Exception {
        log.debug("Test: listBox (senza filtri)");

        MvcResult result = mockMvc.perform(get("/rest/api/box/list"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
    }

    @Test
    public void listBoxFiltered() throws Exception {
        log.debug("Test: listBox (con filtri param)");

        MvcResult result = mockMvc.perform(get("/rest/api/box/list")
                .param("nome", "Lusso")
                .param("idCantina", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
    }

    @Test
    public void deleteBox() throws Exception {
        log.debug("Test: deleteBox");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/box/delete/{id}",boxRepo.searchByFilter("Box Degustazione Lusso", null).getFirst().getId()))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, result.getResponse().getStatus(), "Codice HTTP  " + statusCode);
    }
}