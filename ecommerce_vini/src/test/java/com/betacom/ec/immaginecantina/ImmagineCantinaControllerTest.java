package com.betacom.ec.immaginecantina;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Paths;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest (properties = {"app.upload.dir=${java.io.tmpdir}/ecommerce-test-uploads"})
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImmagineCantinaControllerTest {

    @Autowired private MockMvc mockMvc;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("{} - Setup DB isolato per ImmagineCantina (Controller)", this.getClass().getName());
        
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

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vendReq.getId());
        cantinaReq.setPosizione("indirizzo cantina"); 
        cantinaC.create(cantinaReq);

        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test-base-cantina.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "dummy cantina image".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-cantina/create")
                .file(file)
                .param("id_cantina", "1"))
                .andDo(log())
                .andExpect(status().isOk());

        log.debug("Setup DB completo per ImmagineCantinaControllerTest");
    }
    
    @AfterEach
    public void pulisciDisco() throws IOException {
            String tempDir = System.getProperty("java.io.tmpdir") + "/ecommerce-test-uploads";
            FileSystemUtils.deleteRecursively(Paths.get(tempDir));
            log.debug("Cartella temporanea eliminata.");
    }

    @Test
    public void createImmagineCantina() throws Exception {
        log.debug("Test: createImmagineCantina (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "nuova-immagine-cantina.png", 
                MediaType.IMAGE_PNG_VALUE, 
                "contenuto fittizio png".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-cantina/create")
                .file(file)
                .param("id_cantina", "1"))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void updateImmagineCantina() throws Exception {
        log.debug("Test: updateImmagineCantina (Controller)");
        
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "immagine-cantina-aggiornata.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "contenuto aggiornato".getBytes()
        );

        mockMvc.perform(multipart("/rest/api/immagine-cantina/update")
                .file(file)
                .param("id", "1")
                .param("id_cantina", "1")
                .with(request -> {
                    request.setMethod("PATCH"); 
                    return request;
                }))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void getImmagineCantinaById() throws Exception {
        log.debug("Test: getImmagineCantinaById (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-cantina/getById")
                .param("id", "1"))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void listImmagineCantina() throws Exception {
        log.debug("Test: listImmagineCantina (Controller)");
        
        mockMvc.perform(get("/rest/api/immagine-cantina/list")
                .param("idCantina", "1"))
                .andDo(log())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteImmagineCantina() throws Exception {
        log.debug("Test: deleteImmagineCantina (Controller)");
        
        mockMvc.perform(delete("/rest/api/immagine-cantina/delete/1"))
                .andDo(log())
                .andExpect(status().isOk());
    }
}