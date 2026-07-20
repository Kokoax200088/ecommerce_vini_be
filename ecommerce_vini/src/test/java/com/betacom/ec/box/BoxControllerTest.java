package com.betacom.ec.box;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ClienteController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ClienteRequest;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BoxControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private ClienteController clienteC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private PosizioneController PosizioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB inizio per BoxController");

        RuoloRequest rUser = new RuoloRequest();
        rUser.setId(1);
        rUser.setNome("user");
        rUser.setCanBuy(true);
        rUser.setCanManage(false);
        rUser.setCanSell(false);
        log.debug("Creazione ruolo user: {}", rUser);
        ruoloC.create(rUser);

        ClienteRequest clienteReq = new ClienteRequest();
        clienteReq.setId(1);
        clienteReq.setNome("Mario");
        clienteReq.setCognome("Rossi");
        clienteReq.setEmail("mario.rossi.service@tiscali.net");
        clienteReq.setIdRuolo(rUser.getId());
        clienteReq.setPassword("abete1");
        clienteReq.setIndirizzo("via Roma, 1 Torino TO");
        clienteReq.setDataNascita("21/11/2005");
        log.debug("Creazione cliente: {}", clienteReq);
        clienteC.create(clienteReq);

		RuoloRequest rSeller = new RuoloRequest();
		rSeller.setId(2);
		rSeller.setNome("seller");
		rSeller.setCanManage(false);
		rSeller.setCanBuy(false);
		rSeller.setCanSell(true);
				log.debug("Creazione ruolo seller: {}", rSeller);
		ruoloC.create(rSeller);
		
        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(2);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio.service@gmail.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("abete1");
        vendReq.setDataNascita("08/08/1996");
        log.debug("Creazione venditore: {}", vendReq);
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino Service");
        log.debug("Creazione posizione: {}", posReq);
        PosizioneC.create(posReq);
        
        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina di Prova Service");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        log.debug("Creazione cantina: {}", cantinaReq);
        cantinaC.create(cantinaReq);
        
        createBox();
        
        
    }
    
    public void createBox() throws Exception {
    	log.debug("Test: createBox");
        BoxReq req = new BoxReq();
        req.setId(1);
        req.setNome("Box Degustazione Lusso");
        req.setSconto(15.0);
        req.setCantinaId(1);
    	
    	MvcResult result = mockMvc.perform(post("/rest/api/box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
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
        req.setId(1);
        req.setNome("Box Degustazione Lusso Aggiornato");
        req.setSconto(25.0);
        req.setCantinaId(1);
        
        mockMvc.perform(put("/rest/api/box/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
                ).andExpect(status().isOk());
    }

    @Test
    public void getBoxById() throws Exception {
        log.debug("Test: getBoxById");
        mockMvc.perform(get("/rest/api/box/get")
        						.param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listBox() throws Exception {
        log.debug("Test: listBox (senza filtri)");
        mockMvc.perform(get("/rest/api/box/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void listBoxFiltered() throws Exception {
        log.debug("Test: listBox (con filtri param)");
        mockMvc.perform(get("/rest/api/box/list")
                .param("nome", "Lusso")
                .param("idCantina", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBox() throws Exception {
        log.debug("Test: deleteBox");
        mockMvc.perform(delete("/rest/api/box/delete")
        						.param("id", "1"))
                .andExpect(status().isOk());
    }
}