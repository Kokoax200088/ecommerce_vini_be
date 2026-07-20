package com.betacom.ec.immaginebox;

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

import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ImmagineBoxReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ImmagineBoxControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private BoxController boxC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineBox (Controller)");
        
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
        vendReq.setEmail("venditore.immaginebox@controller.com");
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

        BoxReq boxReq = new BoxReq();
        boxReq.setId(1);
        boxReq.setNome("Box Base Controller");
        boxReq.setSconto(10.0);
        boxReq.setCantinaId(cantinaReq.getId());
        boxC.create(boxReq);
    }

    @Test
    public void createImmagineBox() throws Exception {
        log.debug("Test: createImmagineBox (Controller)");
        
        ImmagineBoxReq req = new ImmagineBoxReq();
        req.setId(1);
        req.setId_box(1);

        mockMvc.perform(post("/rest/api/immagine-box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineBox() throws Exception {
        log.debug("Test: updateImmagineBox (Controller)");
        
        ImmagineBoxReq req = new ImmagineBoxReq();
        req.setId(1);
        req.setId_box(1);

        mockMvc.perform(patch("/rest/api/immagine-box/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineBoxById() throws Exception {
        log.debug("Test: getImmagineBoxById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-box/getById")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineBox() throws Exception {
        log.debug("Test: listImmagineBox (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-box/list")
                .param("idBox", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineBox() throws Exception {
        log.debug("Test: deleteImmagineBox (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-box/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}