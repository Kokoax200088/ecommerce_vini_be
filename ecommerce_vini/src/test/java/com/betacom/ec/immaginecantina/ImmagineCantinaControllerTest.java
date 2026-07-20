package com.betacom.ec.immaginecantina;

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

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ImmagineCantinaReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImmagineCantinaControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineCantina (Controller)");
        
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
        vendReq.setEmail("venditore.immaginecantina@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Base Controller");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
    }

    @Test
    public void createImmagineCantina() throws Exception {
        log.debug("Test: createImmagineCantina (Controller)");
        
        ImmagineCantinaReq req = new ImmagineCantinaReq();
        req.setId_cantina(1);

        mockMvc.perform(post("/rest/api/immagine-cantina/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineCantina() throws Exception {
        log.debug("Test: updateImmagineCantina (Controller)");
        
        ImmagineCantinaReq req = new ImmagineCantinaReq();
        req.setId(1);
        req.setId_cantina(1);

        mockMvc.perform(patch("/rest/api/immagine-cantina/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineCantinaById() throws Exception {
        log.debug("Test: getImmagineCantinaById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-cantina/getById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineCantina() throws Exception {
        log.debug("Test: listImmagineCantina (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-cantina/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineCantina() throws Exception {
        log.debug("Test: deleteImmagineCantina (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-cantina/delete/1"))
                .andExpect(status().isOk());
    }
}