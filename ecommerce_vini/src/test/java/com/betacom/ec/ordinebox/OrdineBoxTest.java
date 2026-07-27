package com.betacom.ec.ordinebox;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.ec.dto.input.BoxReq;
import com.betacom.ec.dto.input.CantinaReq;
import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.betacom.ec.dto.input.OrdineReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.StatusReq;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.BoxDTO;
import com.betacom.ec.dto.output.CantinaDTO;
import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.dto.output.RuoloDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.models.Utente;
import com.betacom.ec.services.interfaces.IBoxService;
import com.betacom.ec.services.interfaces.ICantinaService;
import com.betacom.ec.services.interfaces.IOrdineService;
import com.betacom.ec.services.interfaces.IRuoloService;
import com.betacom.ec.services.interfaces.IStatusService;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.services.interfaces.IVenditoreService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrdineBoxTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IRuoloService ruoloS;

	@Autowired
	private IVenditoreService venditoreS;
	
	@Autowired
	private ICantinaService cantinaS;

	@Autowired
	private IBoxService boxS;

	@Autowired
	private IStatusService statusS;

	@Autowired
	private IUtenteService utenteS;

	@Autowired
	private IOrdineService ordineS;

	private Integer ordineId;
	private Integer boxId;
	private Integer statusId;
	private Integer cantinaId;

	@BeforeEach
	public void setupDatabase() throws Exception {
		log.debug("Setup DB per OrdineBox");

		RuoloRequest ruolo = new RuoloRequest();
		ruolo.setNome("seller");
		ruolo.setCanManage(false);
		ruolo.setCanSell(true);
		ruolo.setCanBuy(true);
		ruoloS.create(ruolo);
		Integer ruoloId = ruoloS.listAll().stream().mapToInt(RuoloDTO::getId).max().getAsInt();

		VenditoreRequest venditore = new VenditoreRequest();
		venditore.setNome("Caio");
		venditore.setCognome("Ilario");
		venditore.setEmail("venditore.ordinebox@test.com");
		venditore.setPassword("password123");
		venditore.setDataNascita("08/08/1996");
		venditore.setIdRuolo(ruoloId);
		venditore.setPartitaIva("A99");
		venditoreS.create(venditore);
		Integer venditoreId = venditoreS.list().stream().mapToInt(VenditoreDTO::getId).max().getAsInt();


		CantinaReq cantina = new CantinaReq();
		cantina.setNome("Cantina Test");
		cantina.setVenditoreId(venditoreId);
		cantina.setPosizione("indirizzo");
		cantinaS.create(cantina);
		cantinaId = cantinaS.listBySearchString(null, null).stream().mapToInt(CantinaDTO::getId).max().getAsInt();

		BoxReq box = new BoxReq();
		box.setNome("Box Test");
		box.setSconto(10.0);
		box.setCantinaId(cantinaId);
		boxS.create(box);
		boxId = boxS.list(null, null).stream().mapToInt(BoxDTO::getId).max().getAsInt();

		StatusReq statusReq = new StatusReq();
		statusReq.setNome("Creato");
		statusReq.setDescrizione("testDescrizione");
		statusS.create(statusReq);
		statusId = statusS.listWithParameters(null, null).stream().mapToInt(StatusDTO::getId).max().getAsInt();

		UtenteRequest utenteReq = new UtenteRequest();
		utenteReq.setNome("Mario");
		utenteReq.setCognome("Rossi");
		utenteReq.setEmail("cliente.ordinebox@test.com");
		utenteReq.setPassword("password123");
		utenteReq.setDataNascita("08/08/1996");
		utenteReq.setIdRuolo(ruoloId);
		Utente utente = utenteS.create(utenteReq);
		Integer utenteId = utente.getId();

		OrdineReq ordine = new OrdineReq();
		ordine.setData_ordine(LocalDate.now());
		ordine.setTotale(0.0);
		ordine.setId_utente(utenteId);
		ordine.setId_status(statusId);
		ordine.setIndirizzoDestinazione("Via Test 1");
		ordineS.create(ordine);
		ordineId = ordineS.listWithParameters(null, null, null, null, null).stream().mapToInt(OrdineDTO::getId).max().getAsInt();
	}

	@Test
	public void createOrdineBoxTest() throws Exception {
		log.debug("createOrdineBoxTest");
		OrdineBoxRequest req = new OrdineBoxRequest();
		req.setOrdineId(ordineId);
		req.setBoxId(boxId);
		req.setStatusId(statusId);
		req.setCantinaId(cantinaId);
		req.setQuantita(1);
		mockMvc.perform(post("/rest/api/ordine-box/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	public void listOrdineBoxTest() throws Exception {
		log.debug("listOrdineBoxTest");
		mockMvc.perform(get("/rest/api/ordine-box/list")
				).andExpect(status().isOk());
	}
}
