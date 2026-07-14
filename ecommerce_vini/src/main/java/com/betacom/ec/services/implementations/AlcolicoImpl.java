package com.betacom.ec.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.AlcolicoMap;
import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Colore;
import com.betacom.ec.models.TipologiaAlcolico;
import com.betacom.ec.models.Venditore;
import com.betacom.ec.repository.IAlcolicoRepository;
import com.betacom.ec.repository.IColoreRepository;
import com.betacom.ec.repository.ITipologiaAlcolicoRepository;
import com.betacom.ec.services.interfaces.IAlcolicoService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AlcolicoImpl implements IAlcolicoService {

	private final IAlcolicoRepository alcolicoR;
	private final ITipologiaAlcolicoRepository tipologiaR;
	private final IColoreRepository coloreR;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void create(AlcolicoReq req) throws Exception {
		log.debug("create {}", req);

		TipologiaAlcolico tipologia = tipologiaR.findById(req.getId_tipologia_alcolico())
				.orElseThrow(() -> new EcommerceVinoException("tipologia.notFnd"));
		Colore colore = coloreR.findById(req.getId_colore())
				.orElseThrow(() -> new EcommerceVinoException("colore.notFnd"));

		Alcolico a = new Alcolico();
		a.setNome(req.getNome());
		a.setAnnata(req.getAnnata());
		if (req.getId_venditore() != null)
			a.setVenditore(em.getReference(Venditore.class, req.getId_venditore()));
		a.setTipologia_alcolico(tipologia);
		a.setColore(colore);
		a.setGradazione(req.getGradazione());
		a.setDescrizione(req.getDescrizione());
		a.setProvenienza(req.getProvenienza());
		a.setPrezzo(req.getPrezzo());

		alcolicoR.save(a);
	}

	@Transactional
	@Override
	public void update(AlcolicoReq req) throws Exception {
		log.debug("update {}", req);

		if (req.getId_alcolico() == null)
			throw new EcommerceVinoException("alcolico.idMandatory");

		Alcolico a = alcolicoR.findById(req.getId_alcolico())
				.orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));

		if (req.getId_tipologia_alcolico() != null) {
			TipologiaAlcolico tipologia = tipologiaR.findById(req.getId_tipologia_alcolico())
					.orElseThrow(() -> new EcommerceVinoException("tipologia.notFnd"));
			a.setTipologia_alcolico(tipologia);
		}
		if (req.getId_colore() != null) {
			Colore colore = coloreR.findById(req.getId_colore())
					.orElseThrow(() -> new EcommerceVinoException("colore.notFnd"));
			a.setColore(colore);
		}
		if (req.getId_venditore() != null)
			a.setVenditore(em.getReference(Venditore.class, req.getId_venditore()));

		a.setNome(req.getNome());
		a.setAnnata(req.getAnnata());
		a.setGradazione(req.getGradazione());
		a.setDescrizione(req.getDescrizione());
		a.setProvenienza(req.getProvenienza());
		a.setPrezzo(req.getPrezzo());

		alcolicoR.save(a);
	}

	@Transactional
	@Override
	public void remove(Integer id_alcolico) throws Exception {
		log.debug("remove {}", id_alcolico);

		Alcolico a = alcolicoR.findById(id_alcolico)
				.orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));

		alcolicoR.delete(a);
	}

	@Override
	public List<AlcolicoDTO> listAll() {
		log.debug("listAll");

		return AlcolicoMap.buildAlcolicoDTOList(alcolicoR.findAll());
	}

	@Override
	public AlcolicoDTO getById(Integer id_alcolico) throws Exception {
		log.debug("getById {}", id_alcolico);

		Alcolico a = alcolicoR.findById(id_alcolico)
				.orElseThrow(() -> new EcommerceVinoException("alcolico.notFnd"));

		return AlcolicoMap.buildAlcolicoDTO(a);
	}
}
