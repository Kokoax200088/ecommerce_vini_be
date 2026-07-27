package com.betacom.ec.immaginedegustazione;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.DegustazioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImmagineDegustazioneControllerTest {

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB isolato per ImmagineDegustazione (Controller)", this.getClass().getName());
        
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
        vendReq.setEmail("venditore.immaginedegustazione@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo cantina"); 
        cantinaC.create(cantinaReq);

        DegustazioneReq degustazioneReq = new DegustazioneReq();
        degustazioneReq.setNome("Degustazione Base");
        degustazioneReq.setDescrizione("Degustazione Base Controller");
        degustazioneReq.setCantinaId(cantinaReq.getId());
        degustazioneReq.setDataInizio("20/07/2026 10:00:00");
        degustazioneReq.setDataFine("20/07/2026 12:00:00");
        degustazioneReq.setPrezzo(30.0);
        degustazioneC.create(degustazioneReq);

        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test-base-degustazione.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "dummy degustazione image".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-degustazione/create")
                .file(file)
                .param("id_degustazione", "1"))
                .andDo(log())
                .andExpect(status().isOk());

        log.debug("Setup DB completo per ImmagineDegustazioneControllerTest");
    }

    @Test
    public void createImmagineDegustazione() throws Exception {
        log.debug("Test: createImmagineDegustazione (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "nuova-immagine-degustazione.png", 
                MediaType.IMAGE_PNG_VALUE, 
                "contenuto fittizio png".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-degustazione/create")
                .file(file)
                .param("id_degustazione", "1"))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineDegustazione() throws Exception {
        log.debug("Test: updateImmagineDegustazione (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "immagine-degustazione-aggiornata.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "contenuto aggiornato".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-degustazione/update")
                .file(file)
                .param("id", "1")
                .param("id_degustazione", "1")
                .with(request -> {
                    request.setMethod("PATCH");
                    return request;
                }))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineDegustazioneById() throws Exception {
        log.debug("Test: getImmagineDegustazioneById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-degustazione/getImmagineDegustazioneById")
                .param("id", "1"))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineDegustazione() throws Exception {
        log.debug("Test: listImmagineDegustazione (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-degustazione/list")
                .param("idDegustazione", "1")) 
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineDegustazione() throws Exception {
        log.debug("Test: deleteImmagineDegustazione (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-degustazione/delete/1"))
                .andDo(log())
                .andExpect(status().isOk());
    }
}