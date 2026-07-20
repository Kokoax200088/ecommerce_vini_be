package com.betacom.ec.posizione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PosizioneControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired RuoloController ruoloC;
    @Autowired VenditoreController venditoreC;
    @Autowired CantinaController cantinaC;
    @Autowired PosizioneController PosizioneC;
    
    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup: Ruolo, Venditore, Cantina");
        
        RuoloRequest rSeller = new RuoloRequest();
		rSeller.setId(1);
		rSeller.setNome("seller");
		rSeller.setCanManage(false);
		rSeller.setCanBuy(false);
		rSeller.setCanSell(true);
		ruoloC.create(rSeller);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(1);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("c.maio.service@gmail.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("abete1");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino");
        PosizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina di Prova Service");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createPosizione() throws Exception {
        PosizioneReq req = new PosizioneReq();
        req.setLatitudine(45.0);
        req.setLongitudine(9.0);
        req.setDescrizione("Torino");
        req.setCantinaId(1);

        mockMvc.perform(post("/rest/api/posizione/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updatePosizione() throws Exception {
        PosizioneReq req = new PosizioneReq();
        req.setId(1);
        req.setLatitudine(46.0);
        req.setLongitudine(10.0);
        req.setDescrizione("Milano");
        req.setCantinaId(1);

        mockMvc.perform(put("/rest/api/posizione/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPosizione() throws Exception {
        mockMvc.perform(get("/rest/api/posizione/get")
        		.param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listPosizione() throws Exception {
        mockMvc.perform(get("/rest/api/posizione/list")
                .param("descrizione", "Milano"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePosizione() throws Exception {
        mockMvc.perform(delete("/rest/api/posizione/delete")
        		.param("id", "1"))
                .andExpect(status().isOk());
    }
}