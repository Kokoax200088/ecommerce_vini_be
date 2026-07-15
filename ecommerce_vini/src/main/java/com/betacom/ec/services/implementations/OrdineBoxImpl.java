package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.OrdineBoxRequest;
import com.betacom.ec.dto.output.OrdineBoxDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.OrdineBoxMap;
import com.betacom.ec.models.Box;
import com.betacom.ec.models.Cantina;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.OrdineBox;
import com.betacom.ec.models.Status;
import com.betacom.ec.repository.IBoxRepository;
import com.betacom.ec.repository.ICantinaRepository;
import com.betacom.ec.repository.IOrdineBoxRepository;
import com.betacom.ec.repository.IOrdineRepository;
import com.betacom.ec.repository.IStatusRepository;
import com.betacom.ec.services.interfaces.IOrdineBoxService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrdineBoxImpl implements IOrdineBoxService {

	private final IOrdineBoxRepository obR;
	private final IBoxRepository bR;
	private final ICantinaRepository cR;
	private final IOrdineRepository oR;
	private final IStatusRepository sR;

	@Transactional
	@Override
	public void create(OrdineBoxRequest req) throws Exception {
		log.debug("create {}", req);

		OrdineBox ob = new OrdineBox();
		Box b = bR.findById(req.getBoxId())
				.orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
		ob.setBox(b);
		Cantina c = cR.findById(req.getCantinaId())
				.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
		ob.setCantina(c);
		Ordine o = oR.findById(req.getOrdineId())
				.orElseThrow(() -> new EcommerceVinoException("ordine.notFnd"));
		ob.setOrdine(o);
		Status s = sR.findById(req.getStatusId())
				.orElseThrow(() -> new EcommerceVinoException("status.notFnd"));
		ob.setStatus(s);
		ob.setQuantita(req.getQuantita());

		obR.save(ob);
	}

	@Transactional
	@Override
	public void update(OrdineBoxRequest req) throws Exception {
		log.debug("update {}", req);

		OrdineBox ob = obR.findById(req.getId())
				.orElseThrow(() -> new EcommerceVinoException("ordineBox.notFnd"));

		if (req.getBoxId() != null) {
			Box b = bR.findById(req.getBoxId())
					.orElseThrow(() -> new EcommerceVinoException("box.notFnd"));
			ob.setBox(b);
		}
		if (req.getCantinaId() != null) {
			Cantina c = cR.findById(req.getCantinaId())
					.orElseThrow(() -> new EcommerceVinoException("cantina.notFnd"));
			ob.setCantina(c);
		}
		if (req.getOrdineId() != null) {
			Ordine o = oR.findById(req.getOrdineId())
					.orElseThrow(() -> new EcommerceVinoException("ordine.notFnd"));
			ob.setOrdine(o);
		}
		if (req.getStatusId() != null) {
			Status s = sR.findById(req.getStatusId())
					.orElseThrow(() -> new EcommerceVinoException("status.notFnd"));
			ob.setStatus(s);
		}
		Optional.ofNullable(req.getQuantita()).ifPresent(ob::setQuantita);

		obR.save(ob);
	}

	@Transactional
	@Override
	public void remove(Integer id_ordine_box) throws Exception {
		log.debug("remove {}", id_ordine_box);

		OrdineBox ob = obR.findById(id_ordine_box)
				.orElseThrow(() -> new EcommerceVinoException("ordineBox.notFnd"));

		obR.delete(ob);
	}

	@Override
	public List<OrdineBoxDTO> listWithParameters(Integer quantita, Integer id_ordine, Integer id_status, Integer id_box, Integer id_cantina) {
		log.debug("listWithParameters {} {} {} {} {}", quantita, id_ordine, id_status, id_box, id_cantina);

		List<OrdineBox> lOB = obR.searchWithParameters(quantita, id_ordine, id_status, id_box, id_cantina);
		return OrdineBoxMap.buildOrdineBoxDTOList(lOB);
	}

	@Override
	public OrdineBoxDTO getById(Integer id_ordine_box) throws Exception {
		log.debug("getById {}", id_ordine_box);

		OrdineBox ob = obR.findById(id_ordine_box)
				.orElseThrow(() -> new EcommerceVinoException("ordineBox.notFnd"));

		return OrdineBoxMap.buildOrdineBoxDTO(ob);
	}
}
