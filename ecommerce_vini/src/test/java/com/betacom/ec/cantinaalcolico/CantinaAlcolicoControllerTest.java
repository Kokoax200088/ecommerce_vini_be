package com.betacom.ec.cantinaalcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
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

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.CantinaAlcolicoController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaAlcolicoReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CantinaAlcolicoControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;
    @Autowired private CantinaAlcolicoController cantalcC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per CantinaAlcolicoControllerTest", this.getClass().getName());
        
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
        vendReq.setEmail("venditore.cantinaalcolico@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        log.debug("Creazione venditore: {}", vendReq);
        venditoreC.create(vendReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo");
        log.debug("Creazione cantina: {}", cantinaReq);
        cantinaC.create(cantinaReq);
        
        TipologiaAlcolicoReq tReq = new TipologiaAlcolicoReq();
        tReq.setId(1);
        tReq.setNome("Vino");
        tReq.setDescrizione("Vino Test");
        log.debug("Creazione tipologia alcolico: {}", tReq);
        tipologiaC.create(tReq);
        
        ColoreReq coReq = new ColoreReq();
        coReq.setId(1);
        coReq.setNome("Rosso");
        coReq.setDescrizione("Vino Rosso");
        log.debug("Creazione colore: {}", coReq);
        coloreC.create(coReq);
        
        AlcolicoReq aReq = new AlcolicoReq();
        aReq.setId_alcolico(1);
        aReq.setAnnata(2022);
        aReq.setDescrizione("Vino Test Controller");
        aReq.setId_venditore(vendReq.getId());
        aReq.setGradazione(12);
        aReq.setNome("Vino Controller");
        aReq.setPrezzo(20.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        log.debug("Creazione alcolico: {}", aReq);
        alcolicoC.create(aReq);
        
        CantinaAlcolicoReq caReq = new CantinaAlcolicoReq();
        caReq.setId(2);
        caReq.setCantinaId(1);
        caReq.setAlcolicoId(1);
        caReq.setQuantita(75);
        log.debug("Creazione cantina alcolico: {}", caReq);
        cantalcC.create(caReq);
        
        log.debug("Setup DB completo per CantinaAlcolicoControllerTest");
    }

    @Test
    public void createCantinaAlcolico() throws Exception {
        log.debug("Test: createCantinaAlcolico (Controller)");
        
        CantinaAlcolicoReq req = new CantinaAlcolicoReq();
        req.setId(3);
        req.setCantinaId(1);
        req.setAlcolicoId(1);
        req.setQuantita(75);

        MvcResult result = mockMvc.perform(post("/rest/api/cantina-alcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();
        
        int statusCode = result.getResponse().getStatus();
        assertEquals(201, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updateCantinaAlcolico() throws Exception {
        log.debug("Test: updateCantinaAlcolico (Controller)");
        
        CantinaAlcolicoReq req = new CantinaAlcolicoReq();
        req.setId(1);
        req.setCantinaId(1);
        req.setAlcolicoId(1);
        req.setQuantita(150);

        MvcResult result = mockMvc.perform(put("/rest/api/cantina-alcolico/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getCantinaAlcolicoById() throws Exception {
        log.debug("Test: getCantinaAlcolicoById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/cantina-alcolico/get/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listCantinaAlcolico() throws Exception {
        log.debug("Test: listCantinaAlcolico (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/cantina-alcolico/list")
                .param("idCantina", "1")
                .param("idAlcolico", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteCantinaAlcolico() throws Exception {
        log.debug("Test: deleteCantinaAlcolico (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/cantina-alcolico/delete/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}