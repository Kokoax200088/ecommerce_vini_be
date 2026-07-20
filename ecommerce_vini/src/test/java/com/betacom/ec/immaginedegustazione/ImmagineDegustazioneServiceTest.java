package com.betacom.ec.immaginedegustazione;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.controllers.CantinaController;
import com.betacom.ec.controllers.DegustazioneController;
import com.betacom.ec.controllers.PosizioneController;
import com.betacom.ec.controllers.RuoloController;
import com.betacom.ec.controllers.VenditoreController;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.DegustazioneReq;
import com.betacom.ec.dto.input.ImmagineDegustazioneReq;
import com.betacom.ec.dto.input.PosizioneReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ImmagineDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.services.interfaces.IImmagineDegustazioneService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class ImmagineDegustazioneServiceTest {

    @Autowired private IImmagineDegustazioneService immagineDegustazioneS;

    @Autowired private RuoloController ruoloC;
    @Autowired private VenditoreController venditoreC;
    @Autowired private PosizioneController posizioneC;
    @Autowired private CantinaController cantinaC;
    @Autowired private DegustazioneController degustazioneC;

    @BeforeEach
    public void setupDatabase() throws Exception {
        log.debug("Setup DB isolato per ImmagineDegustazioneService");

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
        vReq.setEmail("c.maio.imgdeg@service.com");
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

        DegustazioneReq dReq = new DegustazioneReq();
        dReq.setId(1);
        dReq.setDescrizione("Degustazione Test");
        dReq.setCantinaId(cReq.getId());
        dReq.setDataInizio(LocalDateTime.of(2026, 7, 20, 10, 0));
        dReq.setDataFine(LocalDateTime.of(2026, 7, 20, 12, 0));
        dReq.setPrezzo(30.0);
        degustazioneC.create(dReq);
    }

    @Test
    public void createImmagineDegustazioneTest() {
        log.debug("Create ImmagineDegustazione (Service)");
        try {
            ImmagineDegustazioneReq req = new ImmagineDegustazioneReq();
            req.setId_degustazione(1);
            
            MockMultipartFile mockFile = new MockMultipartFile(
                    "file", 
                    "degustazione.jpg", 
                    "image/jpeg", 
                    "contenuto fittizio".getBytes());
            req.setFile(mockFile);

            immagineDegustazioneS.create(req);
            
            List<ImmagineDegustazioneDTO> list = immagineDegustazioneS.listWithParameters(1);
            assertNotNull(list);
            assertEquals(1, list.size());
        } catch (Exception e) {
            throw new AssertionError("Errore in createImmagineDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void updateImmagineDegustazioneTest() {
        log.debug("Update ImmagineDegustazione (Service)");
        try {
            ImmagineDegustazioneReq createReq = new ImmagineDegustazioneReq();
            createReq.setId_degustazione(1);
            MockMultipartFile mockFile1 = new MockMultipartFile("file", "degustazione.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile1);
            immagineDegustazioneS.create(createReq);

            List<ImmagineDegustazioneDTO> list = immagineDegustazioneS.listWithParameters(1);
            Integer generatedId = list.get(0).getId();

            ImmagineDegustazioneReq updateReq = new ImmagineDegustazioneReq();
            updateReq.setId(generatedId);
            updateReq.setId_degustazione(1);
            MockMultipartFile mockFile2 = new MockMultipartFile("file", "degustazione_aggiornata.jpg", "image/jpeg", "nuovo contenuto".getBytes());
            updateReq.setFile(mockFile2);

            immagineDegustazioneS.update(updateReq);
            
            ImmagineDegustazioneDTO dto = immagineDegustazioneS.getById(generatedId);
            assertNotNull(dto.getUrl());
        } catch (Exception e) {
            throw new AssertionError("Errore in updateImmagineDegustazioneTest: " + e.getMessage());
        }
    }

    @Test
    public void deleteImmagineDegustazioneTest() {
        log.debug("Delete ImmagineDegustazione (Service)");
        try {
            ImmagineDegustazioneReq createReq = new ImmagineDegustazioneReq();
            createReq.setId_degustazione(1);
            MockMultipartFile mockFile = new MockMultipartFile("file", "degustazione.jpg", "image/jpeg", "contenuto".getBytes());
            createReq.setFile(mockFile);
            immagineDegustazioneS.create(createReq);
            
            List<ImmagineDegustazioneDTO> list = immagineDegustazioneS.listWithParameters(1);
            Integer generatedId = list.get(0).getId();

            immagineDegustazioneS.delete(generatedId);
            assertThrows(EcommerceVinoException.class, () -> immagineDegustazioneS.getById(generatedId));
        } catch (Exception e) {
            throw new AssertionError("Errore in deleteImmagineDegustazioneTest: " + e.getMessage());
        }
    }
}