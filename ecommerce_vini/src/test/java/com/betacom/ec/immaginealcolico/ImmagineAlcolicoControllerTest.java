package com.betacom.ec.immaginealcolico;

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
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.ImmagineAlcolicoReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImmagineAlcolicoControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private AlcolicoController alcolicoC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineAlcolico (Controller)");
        
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
        vReq.setEmail("venditore.immagine@controller.com");
        vReq.setIdRuolo(rSeller.getId());
        vReq.setPartitaIva("A99");
        vReq.setPassword("password123");
        vReq.setDataNascita("08/08/1996");
        venditoreC.create(vReq);
        
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
        aReq.setImmagine("immagine_vino_controller.jpg");
        aReq.setId_caratteristiche(null);
        aReq.setProvenienza("Italia");
        aReq.setAnnata(2023);
        aReq.setDescrizione("Vino Test Immagine Controller");
        aReq.setId_venditore(vReq.getId());
        aReq.setGradazione(13);
        aReq.setNome("Vino Controller");
        aReq.setPrezzo(20.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        alcolicoC.create(aReq);
    }

    @Test
    public void createImmagineAlcolico() throws Exception {
        log.debug("Test: createImmagineAlcolico (Controller)");
        
        ImmagineAlcolicoReq req = new ImmagineAlcolicoReq();
        req.setId_alcolico(1);
        // NOTA: 'file' viene lasciato nullo intenzionalmente per evitare il crash di serializzazione JSON con Jackson

        mockMvc.perform(post("/rest/api/immagine-alcolico/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineAlcolico() throws Exception {
        log.debug("Test: updateImmagineAlcolico (Controller)");
        
        ImmagineAlcolicoReq req = new ImmagineAlcolicoReq();
        req.setId(1);
        req.setId_alcolico(1);

        mockMvc.perform(patch("/rest/api/immagine-alcolico/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineAlcolicoById() throws Exception {
        log.debug("Test: getImmagineAlcolicoById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-alcolico/getById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineAlcolico() throws Exception {
        log.debug("Test: listImmagineAlcolico (Controller)");
        
         mockMvc.perform(get("/rest/api/immagine-alcolico/list")
                .param("idAlcolico", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineAlcolico() throws Exception {
        log.debug("Test: deleteImmagineAlcolico (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-alcolico/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}