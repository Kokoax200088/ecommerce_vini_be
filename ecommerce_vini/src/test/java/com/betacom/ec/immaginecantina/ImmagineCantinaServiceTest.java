package com.betacom.ec.immaginecantina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
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
import com.betacom.ec.dto.output.ImmagineCantinaDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IImmagineCantinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class ImmagineCantinaServiceTest {

    @Autowired private IImmagineCantinaService immagineCantinaS;

    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineCantinaService");

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
        vReq.setEmail("c.maio.imgcantina@service.com");
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
        posizioneC.create(posReq);

        CantinaReq cReq = new CantinaReq();
        cReq.setId(1);
        cReq.setNome("Cantina Test");
        cReq.setVenditoreId(vReq.getId());
        cReq.setPosizioneId(posReq.getId());
        cantinaC.create(cReq);
    }

    @Test
    public void createImmagineCantinaTest() {
        log.debug("Create ImmagineCantina (Service)");
        try {
            ImmagineCantinaReq req = new ImmagineCantinaReq();
            req.setId_cantina(1);
            
            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", 
                    "cantina.jpg", 
                    "image/jpeg", 
                    "contenuto fittizio".getBytes());
            req.setFile(mockFile);

            immagineCantinaS.create(mockFile, 1);
            
            List<ImmagineCantinaDTO> list = immagineCantinaS.listBySearchString(1);
            assertNotNull(list);
            assertEquals(1, list.size());
        } catch (Exception e) {
            throw new AssertionError("Errore in createImmagineCantinaTest: " + e.getMessage());
        }
    }

    @Test
    public void updateImmagineCantinaTest() {
        log.debug("Update ImmagineCantina (Service)");
        try {
            ImmagineCantinaReq createReq = new ImmagineCantinaReq();
            createReq.setId_cantina(1);
            MockMultipartFile mockFile1 = new MockMultipartFile("file", "cantina.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile1);
            immagineCantinaS.create(mockFile1, 1);

            List<ImmagineCantinaDTO> list = immagineCantinaS.listBySearchString(1);
            Integer generatedId = list.get(0).getId();

            ImmagineCantinaReq updateReq = new ImmagineCantinaReq();
            updateReq.setId(generatedId);
            updateReq.setId_cantina(1);
            MockMultipartFile mockFile2 = new MockMultipartFile("file", "cantina_aggiornata.jpg", "image/jpeg", "nuovo contenuto".getBytes());
            updateReq.setFile(mockFile2);

            immagineCantinaS.update(updateReq);
            
            ImmagineCantinaDTO dto = immagineCantinaS.getById(generatedId);
            assertNotNull(dto.getUrl());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateImmagineCantinaTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteImmagineCantinaTest() {
        log.debug("Delete ImmagineCantina (Service)");
        try {
            ImmagineCantinaReq createReq = new ImmagineCantinaReq();
            createReq.setId_cantina(1);
            MockMultipartFile mockFile = new MockMultipartFile("file", "cantina.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile);
            immagineCantinaS.create(mockFile, 1);
            
            List<ImmagineCantinaDTO> list = immagineCantinaS.listBySearchString(1);
            Integer generatedId = list.get(0).getId();

            immagineCantinaS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> immagineCantinaS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteImmagineCantinaTest: " + e.getMessage());
        }
    }
}