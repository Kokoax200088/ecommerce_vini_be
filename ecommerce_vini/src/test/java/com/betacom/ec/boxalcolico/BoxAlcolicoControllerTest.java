package com.betacom.ec.boxalcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        log.debug("Setup DB isolato per BoxAlcolico (Controller)");

        RuoloRequest rSeller = new RuoloRequest();
        rSeller.setId(1);
        rSeller.setNome("seller");
        rSeller.setCanManage(false);
        rSeller.setCanBuy(false);
        rSeller.setCanSell(true);
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
        venditoreC.create(vReq);
        
        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.1111);
        posReq.setLongitudine(22.2222);
        posReq.setDescrizione("Torino Service");
        PosizioneC.create(posReq);

        CantinaReq cReq = new CantinaReq();
        cReq.setId(1);
        cReq.setPosizioneId(posReq.getId());
        cReq.setNome("Cantina Test");
        cReq.setVenditoreId(vReq.getId());
        cantinaC.create(cReq);

        BoxReq bReq = new BoxReq();
        bReq.setId(1);
        bReq.setNome("Box Test");
        bReq.setSconto(10.0);
        bReq.setCantinaId(cReq.getId());
        BoxC.create(bReq);
        
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
        aReq.setAnnata(2020);
        aReq.setDescrizione("Vino Rosso");
        aReq.setId_venditore(vReq.getId());
        aReq.setGradazione(13);
        aReq.setNome("Merlot");
        aReq.setPrezzo(15.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        AlcolicoC.create(aReq);
    }

    @Test
    public void createBoxAlcolico() throws Exception {
        log.debug("Test: createBoxAlcolico");
        BoxAlcolicoReq req = new BoxAlcolicoReq();
        req.setId(1); 
        req.setBoxId(1);
        req.setAlcolicoId(1);
        req.setQuantita(5);

        mockMvc.perform(post("/rest/api/boxalcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBoxAlcolico() throws Exception {
        log.debug("Test: updateBoxAlcolico");
        
        BoxAlcolicoReq createReq = new BoxAlcolicoReq();
        createReq.setId(1);
        createReq.setBoxId(1);
        createReq.setAlcolicoId(1);
        createReq.setQuantita(5);
        mockMvc.perform(post("/rest/api/boxalcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        BoxAlcolicoReq updateReq = new BoxAlcolicoReq();
        updateReq.setId(1); 
        updateReq.setBoxId(1);
        updateReq.setAlcolicoId(1);
        updateReq.setQuantita(15);

        mockMvc.perform(patch("/rest/api/boxalcolico/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andExpect(status().isOk());
    }

    @Test
    public void getBoxAlcolicoById() throws Exception {
        log.debug("Test: getBoxAlcolicoById");
        
        BoxAlcolicoReq createReq = new BoxAlcolicoReq();
        createReq.setId(1);
        createReq.setBoxId(1);
        createReq.setAlcolicoId(1);
        createReq.setQuantita(5);
        mockMvc.perform(post("/rest/api/boxalcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        mockMvc.perform(get("/rest/api/boxalcolico/getBoxAlcolicoById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listBoxAlcolico() throws Exception {
        log.debug("Test: listBoxAlcolico");
        mockMvc.perform(get("/rest/api/boxalcolico/list")
                .param("quantita", "5"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBoxAlcolico() throws Exception {
        log.debug("Test: deleteBoxAlcolico");
        
        BoxAlcolicoReq createReq = new BoxAlcolicoReq();
        createReq.setId(1);
        createReq.setBoxId(1);
        createReq.setAlcolicoId(1);
        createReq.setQuantita(5);
        mockMvc.perform(post("/rest/api/boxalcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)));

        mockMvc.perform(delete("/rest/api/boxalcolico/delete")
        						.param("id", "1"))
                .andExpect(status().isOk());
    }
}