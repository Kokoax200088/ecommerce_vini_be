package com.betacom.ec.cantinaalcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.CantinaAlcolicoReq;
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
public class CantinaAlcolicoControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per CantinaAlcolico (Controller)");
        
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
        vendReq.setEmail("venditore.cantinaalcolico@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Controller");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
        
        TipologiaAlcolicoReq tReq = new TipologiaAlcolicoReq();
        tReq.setId(1);
        tReq.setNome("Vino");
        tReq.setDescrizione("Vino Test");
        tipologiaC.create(tReq);
        
        ColoreReq coReq = new ColoreReq();
        coReq.setId(1);
        coReq.setNome("Rosso");
        coReq.setDescrizione("Vino Rosso");
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
        alcolicoC.create(aReq);
    }

    @Test
    public void createCantinaAlcolico() throws Exception {
        log.debug("Test: createCantinaAlcolico (Controller)");
        
        CantinaAlcolicoReq req = new CantinaAlcolicoReq();
        req.setCantinaId(1);
        req.setAlcolicoId(1);
        req.setQuantita(75);

        mockMvc.perform(post("/rest/api/cantina-alcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCantinaAlcolico() throws Exception {
        log.debug("Test: updateCantinaAlcolico (Controller)");
        
        CantinaAlcolicoReq createReq = new CantinaAlcolicoReq();
        createReq.setCantinaId(1);
        createReq.setAlcolicoId(1);
        createReq.setQuantita(75);
        mockMvc.perform(post("/rest/api/cantina-alcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        CantinaAlcolicoReq req = new CantinaAlcolicoReq();
        req.setId(1);
        req.setCantinaId(1);
        req.setAlcolicoId(1);
        req.setQuantita(150);

        mockMvc.perform(put("/rest/api/cantina-alcolico/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getCantinaAlcolicoById() throws Exception {
        log.debug("Test: getCantinaAlcolicoById (Controller)");
        
        mockMvc.perform(get("/rest/api/cantina-alcolico/get")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listCantinaAlcolico() throws Exception {
        log.debug("Test: listCantinaAlcolico (Controller)");
        
        mockMvc.perform(get("/rest/api/cantina-alcolico/list")
                .param("idCantina", "1")
                .param("idAlcolico", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCantinaAlcolico() throws Exception {
        log.debug("Test: deleteCantinaAlcolico (Controller)");
        
        mockMvc.perform(delete("/rest/api/cantina-alcolico/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}