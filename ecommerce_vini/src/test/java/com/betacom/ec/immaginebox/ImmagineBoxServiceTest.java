package com.betacom.ec.immaginebox;

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

import com.betacom.ec.controllers.AlcolicoController;
import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.ColoreController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.TipologiaAlcolicoController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.ImmagineAlcolicoReq;
import com.betacom.ec.dto.input.ImmagineBoxReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ImmagineAlcolicoDTO;
import com.betacom.ec.dto.output.ImmagineBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IImmagineAlcolicoService;
import com.betacom.ec.services.interfaces.IImmagineBoxService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class ImmagineBoxServiceTest {

    @Autowired private IImmagineBoxService immS;
    
    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private CantinaController cantinaC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private BoxController BoxC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineBox (Service)");
        
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
        vReq.setEmail("venditore.immagine@service.com");
        vReq.setIdRuolo(rSeller.getId());
        vReq.setPartitaIva("A99");
        vReq.setPassword("password123");
        vReq.setDataNascita("08/08/1996");
        venditoreC.create(vReq);
        
        PosizioneReq posReq = new PosizioneReq();
        posReq.setId(1);
        posReq.setLatitudine(11.11);
        posReq.setLongitudine(22.22);
        posReq.setDescrizione("Posizione Degustazione Controller");
        posizioneC.create(posReq);

        CantinaReq cantinaReq = new CantinaReq();
        cantinaReq.setId(1);
        cantinaReq.setNome("Cantina Base Controller");
        cantinaReq.setVenditoreId(vReq.getId());
        cantinaReq.setPosizioneId(posReq.getId());
        cantinaC.create(cantinaReq);
        
        BoxReq bReq = new BoxReq();
        bReq.setId(1);
        bReq.setNome("Box Test");
        bReq.setSconto(10.0);
        bReq.setCantinaId(cantinaReq.getId());
        BoxC.create(bReq);
        
    }
    
    @Test
    public void createImmagineBoxTest() {
        log.debug("Test: createImmagineAlcolico (Service)");
        try {
            ImmagineBoxReq req = new ImmagineBoxReq();
            req.setId_box(1);
            
            // Simula un file 
            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", 
                    "bottiglia.jpg", 
                    "image/jpeg", 
                    "contenuto fittizio".getBytes());
            req.setFile(mockFile);
            
            immS.create(req);
            
            List<ImmagineBoxDTO> list = immS.list(1);
            assertNotNull(list);
            assertEquals(1, list.size());
        } catch (Exception e) {
            throw new AssertionError("Errore in createBoxAlcolicoTest: " + e.getMessage());
        }
    }

    @Test
    public void updateImmagineBoxoTest() {
        log.debug("Test: updateImmagineBox (Service)");
        try {
            ImmagineBoxReq createReq = new ImmagineBoxReq();
            createReq.setId_box(1);
            MockMultipartFile mockFile1 = new MockMultipartFile("file", "bottiglia.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile1);
            immS.create(createReq);

            List<ImmagineBoxDTO> list = immS.list(1);
            Integer generatedId = list.get(0).getId();

            ImmagineBoxReq updateReq = new ImmagineBoxReq();
            updateReq.setId(generatedId);
            updateReq.setId_box(1);
            MockMultipartFile mockFile2 = new MockMultipartFile("file", "bottiglia_aggiornata.jpg", "image/jpeg", "nuovo contenuto".getBytes());
            updateReq.setFile(mockFile2);
            
            immS.update(updateReq);
            
            ImmagineBoxDTO dto = immS.getById(generatedId);
            assertNotNull(dto.getUrl());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateImmagineBoxTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteImmagineBoxTest() {
        log.debug("Test: deleteImmagineBox (Service)");
        try {
            ImmagineBoxReq createReq = new ImmagineBoxReq();
            createReq.setId_box(1);
            MockMultipartFile mockFile = new MockMultipartFile("file", "bottiglia.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile);
            immS.create(createReq);
            
            List<ImmagineBoxDTO> list = immS.list(1);
            Integer generatedId = list.get(0).getId();

            immS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> immS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteImmagineBoxTest: " + e.getMessage());
        }
    }
}