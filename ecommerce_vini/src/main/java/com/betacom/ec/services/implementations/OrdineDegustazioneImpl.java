package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.OrdineDegustazioneRequest;
import com.betacom.ec.dto.output.OrdineDegustazioneDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.OrdineDegustazioneMap;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Degustazione;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.OrdineDegustazione;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IDegustazioneRepository;
import com.betacom.ec.repository.IOrdineDegustazioneRepository;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IOrdineDegustazioneService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineDegustazioneImpl implements IOrdineDegustazioneService {

	private final IOrdineDegustazioneRepository odR;
	private final IDegustazioneRepository dR;
	private final ICantinaRepository cR;
	private final IOrdineRepository oR;
	private final IStatusRepository sR;

	@Transactional
	@Override
	public void create(OrdineDegustazioneRequest req) throws Exception {
		log.debug("create {}", req);

		OrdineDegustazione od = new OrdineDegustazione();
		Degustazione d = dR.findById(req.getDegustazioneId())
				.orElseThrow(() -> new EcommerceVinoException("degustazione.notFnd"));
		od.setDegustazione(d);
		Cantina c = cR.findById(req.getCantinaId())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		od.setCantina(c);
		Ordine o = oR.findById(req.getOrdineId())
				.orElseThrow(() -> new EcommerceVinoException("ordine.notFnd"));
		od.setOrdine(o);
		Status s = sR.findById(req.getStatusId())
				.orElseThrow(() -> new EcommerceVinoException("status.notFnd"));
		od.setStatus(s);
		od.setQuantita(req.getQuantita());

		odR.save(od);
	}

	@Transactional
	@Override
	public void update(OrdineDegustazioneRequest req) throws Exception {
		log.debug("update {}", req);

		OrdineDegustazione od = odR.findById(req.getId())
				.orElseThrow(() -> new EcommerceVinoException("ordineDegustazione.notFnd"));

		if (req.getDegustazioneId() != null) {
			Degustazione d = dR.findById(req.getDegustazioneId())
					.orElseThrow(() -> new EcommerceVinoException("degustazione.notFnd"));
			od.setDegustazione(d);
		}
		if (req.getCantinaId() != null) {
			Cantina c = cR.findById(req.getCantinaId())
					.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
			od.setCantina(c);
		}
		if (req.getOrdineId() != null) {
			Ordine o = oR.findById(req.getOrdineId())
					.orElseThrow(() -> new EcommerceVinoException("ordine.notFnd"));
			od.setOrdine(o);
		}
		if (req.getStatusId() != null) {
			Status s = sR.findById(req.getStatusId())
					.orElseThrow(() -> new EcommerceVinoException("status.notFnd"));
			od.setStatus(s);
		}
		Optional.ofNullable(req.getQuantita()).ifPresent(od::setQuantita);

		odR.save(od);
	}

	@Transactional
	@Override
	public void remove(Integer id_ordine_degustazione) throws Exception {
		log.debug("remove {}", id_ordine_degustazione);

		OrdineDegustazione od = odR.findById(id_ordine_degustazione)
				.orElseThrow(() -> new EcommerceVinoException("ordineDegustazione.notFnd"));

		odR.delete(od);
	}

	@Override
	public List<OrdineDegustazioneDTO> listWithParameters(Integer quantita, Integer id_ordine, Integer id_status, Integer id_degustazione, Integer id_cantina) {
		log.debug("listWithParameters {} {} {} {} {}", quantita, id_ordine, id_status, id_degustazione, id_cantina);

		List<OrdineDegustazione> lOD = odR.searchWithParameters(quantita, id_ordine, id_status, id_degustazione, id_cantina);
		return OrdineDegustazioneMap.buildOrdineDegustazioneDTOList(lOD);
	}

	@Override
	public OrdineDegustazioneDTO getById(Integer id_ordine_degustazione) throws Exception {
		log.debug("getById {}", id_ordine_degustazione);

		OrdineDegustazione od = odR.findById(id_ordine_degustazione)
				.orElseThrow(() -> new EcommerceVinoException("ordineDegustazione.notFnd"));

		return OrdineDegustazioneMap.buildOrdineDegustazioneDTO(od);
	}
}
