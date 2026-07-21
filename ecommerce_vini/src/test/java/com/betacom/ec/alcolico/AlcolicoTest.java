package com.betacom.ec.alcolico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.input.ColoreReq;
import com.betacom.ec.dto.input.RuoloRequest;
import com.betacom.ec.dto.input.TipologiaAlcolicoReq;
import com.betacom.ec.dto.input.VenditoreRequest;
import com.betacom.ec.dto.output.ColoreDTO;
import com.betacom.ec.dto.output.RuoloDTO;
import com.betacom.ec.dto.output.TipologiaAlcolicoDTO;
import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.services.interfaces.IColoreService;
import com.betacom.ec.services.interfaces.IRuoloService;
import com.betacom.ec.services.interfaces.ITipologiaAlcolicoService;
import com.betacom.ec.services.interfaces.IVenditoreService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AlcolicoTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IRuoloService ruoloS;

	@Autowired
	private IVenditoreService venditoreS;

	@Autowired
	private IColoreService coloreS;

	@Autowired
	private ITipologiaAlcolicoService tipologiaS;

	private Integer venditoreId;
	private Integer coloreId;
	private Integer tipologiaId;

	@BeforeEach
	public void setupDatabase() throws Exception {
		log.debug("Setup DB per Alcolico");

		RuoloRequest ruolo = new RuoloRequest();
		ruolo.setNome("seller");
		ruolo.setCanManage(false);
		ruolo.setCanSell(true);
		ruolo.setCanBuy(false);
		ruoloS.create(ruolo);
		Integer ruoloId = ruoloS.listAll().stream().mapToInt(RuoloDTO::getId).max().getAsInt();

		VenditoreRequest venditore = new VenditoreRequest();
		venditore.setNome("Caio");
		venditore.setCognome("Ilario");
		venditore.setEmail("venditore.alcolico@test.com");
		venditore.setPassword("password123");
		venditore.setDataNascita("08/08/1996");
		venditore.setIdRuolo(ruoloId);
		venditore.setPartitaIva("A99");
		venditoreS.create(venditore);
		venditoreId = venditoreS.list().stream().mapToInt(VenditoreDTO::getId).max().getAsInt();

		ColoreReq colore = new ColoreReq();
		colore.setNome("Rosso");
		colore.setDescrizione("testDescrizione");
		coloreS.create(colore);
		coloreId = coloreS.listAll().stream().mapToInt(ColoreDTO::getId).max().getAsInt();

		TipologiaAlcolicoReq tipologia = new TipologiaAlcolicoReq();
		tipologia.setNome("Vino");
		tipologia.setDescrizione("testDescrizione");
		tipologiaS.create(tipologia);
		tipologiaId = tipologiaS.listAll().stream().mapToInt(TipologiaAlcolicoDTO::getId).max().getAsInt();
	}

	@Test
	public void createAlcolicoTest() throws Exception {
		log.debug("createAlcolicoTest");
		AlcolicoReq req = new AlcolicoReq();
		req.setId_venditore(venditoreId);
		req.setNome("TestVino");
		req.setId_tipologia_alcolico(tipologiaId);
		req.setId_colore(coloreId);
		req.setPrezzo(10.0);
		mockMvc.perform(post("/rest/api/alcolico/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated());
	}

	@Test
	public void listAlcolicoTest() throws Exception {
		log.debug("listAlcolicoTest");
		mockMvc.perform(get("/rest/api/alcolico/list")
				).andExpect(status().isOk());
	}
}
