package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.AlcolicoReq;
import com.betacom.ec.dto.output.AlcolicoDTO;
import com.betacom.ec.exception.EcommerceVinoException;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlcolicoImpl implements IAlcolicoService {

	private final IAlcolicoRepository alcolicoR;
	private final ITipologiaAlcolicoRepository tipologiaR;
	private final IColoreRepository coloreR;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(AlcolicoReq req) throws EcommerceVinoException {
		log.debug("create {}", req);

		TipologiaAlcolico tipologia = tipologiaR.findById(req.getId_tipologia_alcolico())
				.orElseThrow(() -> new EcommerceVinoException("tipologia_alcolico_not_found"));
		Colore colore = coloreR.findById(req.getId_colore())
				.orElseThrow(() -> new EcommerceVinoException("colore_not_found"));

		Alcolico a = new Alcolico();
		a.setNome(req.getNome());
		a.setAnnata(req.getAnnata());
		if (req.getId_venditore() != null)
			a.setId_venditore(em.getReference(Venditore.class, req.getId_venditore()));
		a.setTipologia_alcolico(tipologia);
		a.setColore(colore);
		a.setGradazione(req.getGradazione());
		a.setDescrizione(req.getDescrizione());
		a.setProvenienza(req.getProvenienza());
		a.setImmagine(req.getImmagine());
		a.setPrezzo(req.getPrezzo());

		alcolicoR.save(a);
	}

	@Override
	public void update(AlcolicoReq req) throws EcommerceVinoException {
		log.debug("update {}", req);

		if (req.getId_alcolico() == null)
			throw new EcommerceVinoException("alcolico_id_mandatory");

		Alcolico a = alcolicoR.findById(req.getId_alcolico())
				.orElseThrow(() -> new EcommerceVinoException("alcolico_not_found"));

		if (req.getId_tipologia_alcolico() != null) {
			TipologiaAlcolico tipologia = tipologiaR.findById(req.getId_tipologia_alcolico())
					.orElseThrow(() -> new EcommerceVinoException("tipologia_alcolico_not_found"));
			a.setTipologia_alcolico(tipologia);
		}
		if (req.getId_colore() != null) {
			Colore colore = coloreR.findById(req.getId_colore())
					.orElseThrow(() -> new EcommerceVinoException("colore_not_found"));
			a.setColore(colore);
		}
		if (req.getId_venditore() != null)
			a.setId_venditore(em.getReference(Venditore.class, req.getId_venditore()));

		a.setNome(req.getNome());
		a.setAnnata(req.getAnnata());
		a.setGradazione(req.getGradazione());
		a.setDescrizione(req.getDescrizione());
		a.setProvenienza(req.getProvenienza());
		a.setImmagine(req.getImmagine());
		a.setPrezzo(req.getPrezzo());

		alcolicoR.save(a);
	}

	@Override
	public void remove(Integer id_alcolico) throws EcommerceVinoException {
		log.debug("remove {}", id_alcolico);

		Alcolico a = alcolicoR.findById(id_alcolico)
				.orElseThrow(() -> new EcommerceVinoException("alcolico_not_found"));

		alcolicoR.delete(a);
	}

	@Override
	public List<AlcolicoDTO> listAll() {
		log.debug("listAll");

		return alcolicoR.findAll().stream()
				.map(this::buildDTO)
				.collect(Collectors.toList());
	}

	@Override
	public AlcolicoDTO getById(Integer id_alcolico) throws EcommerceVinoException {
		log.debug("getById {}", id_alcolico);

		Alcolico a = alcolicoR.findById(id_alcolico)
				.orElseThrow(() -> new EcommerceVinoException("alcolico_not_found"));

		return buildDTO(a);
	}

	private AlcolicoDTO buildDTO(Alcolico a) {
		return AlcolicoDTO.builder()
				.id_alcolico(a.getId_alcolico())
				.id_venditore(a.getId_venditore() != null ? a.getId_venditore().getId() : null)
				.nome(a.getNome())
				.annata(a.getAnnata())
				.id_tipologia_alcolico(a.getTipologia_alcolico() != null
						? a.getTipologia_alcolico().getId_tipologia_alcolico()
						: null)
				.id_colore(a.getColore() != null ? a.getColore().getId_colore() : null)
				.gradazione(a.getGradazione())
				.descrizione(a.getDescrizione())
				.provenienza(a.getProvenienza())
				.immagine(a.getImmagine())
				.prezzo(a.getPrezzo())
				.build();
	}
}
