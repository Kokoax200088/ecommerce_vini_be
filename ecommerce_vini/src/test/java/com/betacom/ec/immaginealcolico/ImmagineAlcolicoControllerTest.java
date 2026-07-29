package com.betacom.ec.immaginealcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest (properties = {"app.upload.dir=${java.io.tmpdir}/ecommerce-test-uploads"})
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImmagineAlcolicoControllerTest {

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private TipologiaAlcolicoController tipologiaC;
    @Autowired private ColoreController coloreC;
    @Autowired private AlcolicoController alcolicoC;
    
    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per ImmagineAlcolicoControllerTest", this.getClass().getName());
        
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
        vendReq.setEmail("venditore.imgalcolico@controller.com");
        vendReq.setIdRuolo(rSeller.getId());
        vendReq.setPartitaIva("A99");
        vendReq.setPassword("password123");
        vendReq.setDataNascita("08/08/1996");
        venditoreC.create(vendReq);

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
        aReq.setDescrizione("Vino per Immagine Test");
        aReq.setId_venditore(vendReq.getId());
        aReq.setGradazione(12);
        aReq.setNome("Vino Controller Immagine");
        aReq.setPrezzo(20.0);
        aReq.setId_tipologia_alcolico(1);
        aReq.setId_colore(1);
        alcolicoC.create(aReq);
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test-base.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "dummy image content".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-alcolico/create")
                .file(file)
                .param("id_alcolico", "1"))
                .andDo(log());

        log.debug("Setup DB completo per ImmagineAlcolicoControllerTest");
    }
    
    @AfterEach
    public void pulisciDisco() throws IOException {
            String tempDir = System.getProperty("java.io.tmpdir") + "/ecommerce-test-uploads";
            FileSystemUtils.deleteRecursively(Paths.get(tempDir));
            log.debug("Cartella temporanea eliminata.");
    }

    @Test
    public void createImmagineAlcolico() throws Exception {
        log.debug("Test: createImmagineAlcolico (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "nuova-immagine.png", 
                MediaType.IMAGE_PNG_VALUE, 
                "contenuto fittizio png".getBytes()
        );

        MvcResult result = mockMvc.perform(multipart("/rest/api/immagine-alcolico/create")
                .file(file)
                .param("id_alcolico", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updateImmagineAlcolico() throws Exception {
        log.debug("Test: updateImmagineAlcolico (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "immagine-aggiornata.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "contenuto aggiornato".getBytes()
        );

        MvcResult result = mockMvc.perform(multipart("/rest/api/immagine-alcolico/update")
                .file(file)
                .param("id", "1")
                .param("id_alcolico", "1")
                .with(request -> {
                    request.setMethod("PATCH"); 
                    return request;
                }))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void getImmagineAlcolicoById() throws Exception {
        log.debug("Test: getImmagineAlcolicoById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/immagine-alcolico/getById")
                .param("id", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listImmagineAlcolico() throws Exception {
        log.debug("Test: listImmagineAlcolico (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/immagine-alcolico/list")
                .param("idAlcolico", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteImmagineAlcolico() throws Exception {
        log.debug("Test: deleteImmagineAlcolico (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/immagine-alcolico/delete/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}