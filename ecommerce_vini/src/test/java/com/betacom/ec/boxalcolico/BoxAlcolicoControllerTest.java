package com.betacom.ec.boxalcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.BoxAlcolicoReq;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.PosizioneReq;
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
public class BoxAlcolicoControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private BoxController BoxC;
    @Autowired private AlcolicoController AlcolicoC;
    @Autowired private PosizioneController PosizioneC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;
    
    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per BoxAlcolicoController", this.getClass().getName());

        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
        log.debug("Creazione ruolo seller: {}", rSeller);
        ruoloC.create(rSeller);

        VenditoreRequest vReq = new VenditoreRequest();
        vReq.setId(1);
        vReq.setNome("Caio");
        vReq.setCognome("Ilario");
        vReq.setEmail("c.maio@gmail.com");
        vReq.setIdRuolo(rSeller.getId());
        vReq.setPartitaIva("A99");
        vReq.setPassword("password123");
        vReq.setDataNascita("08/08/1996");
        log.debug("Creazione venditore: {}", vReq);
        venditoreC.create(vReq);
        
        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino Service");
        log.debug("Creazione posizione: {}", posReq);
        PosizioneC.create(posReq);

        CantinaReq cReq = new CantinaReq();
        cReq.setId(1);
        cReq.setPosizioneId(posReq.getId());
        cReq.setNome("Cantina Test");
        cReq.setVenditoreId(vReq.getId());
        log.debug("Creazione cantina: {}", cReq);
        cantinaC.create(cReq);

        BoxReq bReq = new BoxReq();
        bReq.setId(1);
        bReq.setNome("Box Test");
        bReq.setSconto(10.0);
        bReq.setCantinaId(cReq.getId());
        log.debug("Creazione box: {}", bReq);
        BoxC.create(bReq);
        
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
        aReq.setAnnata(2020);
        aReq.setDescrizione("Vino Rosso");
        aReq.setId_venditore(vReq.getId());
        aReq.setGradazione(13);
        aReq.setNome("Merlot");
        aReq.setPrezzo(15.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        log.debug("Creazione alcolico: {}", aReq);
        AlcolicoC.create(aReq);

        log.debug("Test: createBoxAlcolico (durante setup)");
        BoxAlcolicoReq boxAlcolicoReq = new BoxAlcolicoReq();
        boxAlcolicoReq.setId(1); 
        boxAlcolicoReq.setBoxId(1);
        boxAlcolicoReq.setAlcolicoId(1);
        boxAlcolicoReq.setQuantita(5);

        MvcResult result = mockMvc.perform(post("/rest/api/boxalcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boxAlcolicoReq)))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);

        log.debug("Setup DB completo per BoxAlcolicoController");
    }

    @Test
    public void updateBoxAlcolico() throws Exception {
        log.debug("Test: updateBoxAlcolico");

        BoxAlcolicoReq updateReq = new BoxAlcolicoReq();
        updateReq.setId(1); 
        updateReq.setBoxId(1);
        updateReq.setAlcolicoId(1);
        updateReq.setQuantita(15);

        MvcResult result = mockMvc.perform(patch("/rest/api/boxalcolico/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getBoxAlcolicoById() throws Exception {
        log.debug("Test: getBoxAlcolicoById");

        MvcResult result = mockMvc.perform(get("/rest/api/boxalcolico/getBoxAlcolicoById")
                .param("id", "1"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listBoxAlcolico() throws Exception {
        log.debug("Test: listBoxAlcolico");

        MvcResult result = mockMvc.perform(get("/rest/api/boxalcolico/list")
                .param("quantita", "5"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteBoxAlcolico() throws Exception {
        log.debug("Test: deleteBoxAlcolico");

        MvcResult result = mockMvc.perform(delete("/rest/api/boxalcolico/delete/1"))
                .andDo(log())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}