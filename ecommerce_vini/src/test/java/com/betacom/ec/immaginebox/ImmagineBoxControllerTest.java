package com.betacom.ec.immaginebox;

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

import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest (properties = {"app.upload.dir=${java.io.tmpdir}/ecommerce-test-uploads"})
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImmagineBoxControllerTest {

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private BoxController boxC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB inizio per ImmagineBoxControllerTest", this.getClass().getName());
        
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

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo cantina");
        cantinaC.create(cantinaReq);

        BoxReq boxReq = new BoxReq();
        boxReq.setId(1);
        boxReq.setNome("Box Base Controller");
        boxReq.setSconto(10.0);
        boxReq.setCantinaId(cantinaReq.getId());
        boxC.create(boxReq);

        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test-base-box.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "dummy box image".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-box/create")
                .file(file)
                .param("id_box", "1"))
                .andDo(log());

        log.debug("Setup DB completo per ImmagineBoxControllerTest");
    }
    
    @AfterEach
    public void pulisciDisco() throws IOException {
            String tempDir = System.getProperty("java.io.tmpdir") + "/ecommerce-test-uploads";
            FileSystemUtils.deleteRecursively(Paths.get(tempDir));
            log.debug("Cartella temporanea eliminata.");
    }

    @Test
    public void createImmagineBox() throws Exception {
        log.debug("Test: createImmagineBox (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "nuova-immagine-box.png", 
                MediaType.IMAGE_PNG_VALUE, 
                "contenuto fittizio png".getBytes()
        );

        MvcResult result = mockMvc.perform(multipart("/rest/api/immagine-box/create")
                .file(file)
                .param("id_box", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void updateImmagineBox() throws Exception {
        log.debug("Test: updateImmagineBox (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "immagine-box-aggiornata.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "contenuto aggiornato".getBytes()
        );

        MvcResult result = mockMvc.perform(multipart("/rest/api/immagine-box/update")
                .file(file)
                .param("id", "1")
                .param("id_box", "1")
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
    public void getImmagineBoxById() throws Exception {
        log.debug("Test: getImmagineBoxById (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/immagine-box/getById")
                .param("id", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void listImmagineBox() throws Exception {
        log.debug("Test: listImmagineBox (Controller)");
        
        MvcResult result = mockMvc.perform(get("/rest/api/immagine-box/list")
                .param("idBox", "1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }

    @Test
    public void deleteImmagineBox() throws Exception {
        log.debug("Test: deleteImmagineBox (Controller)");
        
        MvcResult result = mockMvc.perform(delete("/rest/api/immagine-box/delete/1"))
                .andDo(log())
                .andReturn();
                
        int statusCode = result.getResponse().getStatus();
        assertEquals(200, statusCode, "Codice HTTP " + statusCode);
    }
}