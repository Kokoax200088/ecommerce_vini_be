package com.betacom.ec.cantina;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CantinaControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per CantinaController", this.getClass().getName());
        
        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        log.debug("Creazione ruolo seller: {}", rSeller);
        ruoloC.create(rSeller);

        VenditoreRequest vendReq = new VenditoreRequest();
        vendReq.setId(1);
        vendReq.setNome("Caio");
        vendReq.setCognome("Ilario");
        vendReq.setEmail("venditore.cantina@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        log.debug("Creazione venditore: {}", vendReq);
        venditoreC.create(vendReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo");
        cantinaReq.setDescrizione("descrizione");
        log.debug("Creazione cantina: {}", cantinaReq);
        cantinaC.create(cantinaReq);

        log.debug("Setup DB completo per CantinaController");
    }

    @Test
    public void createCantina() throws Exception {
        log.debug("Test: createCantina (Controller)");

        CantinaReq req = new CantinaReq();
        req.setId(2);
        req.setNome("Cantina Controller Nuova");
        req.setDescrizione("descrizione");
        req.setVenditoreId(1);
        req.setPosizione("indirizzo");

        MvcResult result = mockMvc.perform(post("/rest/api/cantina/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(201, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updateCantina() throws Exception {
        log.debug("Test: updateCantina (Controller)");
        
        CantinaReq req = new CantinaReq();
        req.setId(1);
        req.setNome("Cantina Controller Aggiornata");
        req.setVenditoreId(1);
        req.setPosizione("posizione nuova");
        req.setDescrizione("descrizione nuova");

        MvcResult result = mockMvc.perform(put("/rest/api/cantina/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getCantinaById() throws Exception {
        log.debug("Test: getCantinaById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/cantina/get/1"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listCantina() throws Exception {
        log.debug("Test: listCantina (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/cantina/list")
                .param("nomeCantina", "Base"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteCantina() throws Exception {
        log.debug("Test: deleteCantina (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/cantina/remove/1"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}