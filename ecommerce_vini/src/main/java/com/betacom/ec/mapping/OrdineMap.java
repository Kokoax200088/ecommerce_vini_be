package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.models.Ordine;

@Component
public class OrdineMap {
	private final OrdineAlcolicoMap ordAlcMap;
	private final OrdineBoxMap ordBoxMap;
	private final UtenteMap utenteMap;

    public OrdineMap(OrdineAlcolicoMap ordAlcMap, OrdineBoxMap ordBoxMap, UtenteMap utenteMap) {
        this.ordAlcMap = ordAlcMap;
        this.ordBoxMap = ordBoxMap;
        this.utenteMap = utenteMap;
    }

	public  List<OrdineDTO> buildOrdineDTOList(List<Ordine> lO){
		return lO.stream()
				.map (a -> buildOrdineDTO(a)
						).toList();
	}

	public  OrdineDTO buildOrdineDTO(Ordine o) {
		return OrdineDTO.builder()
				.id(o.getId())
				.data_ordine(o.getData_ordine())
				.totale(o.getTotale())
				.status(StatusMap.buildStatusDTO(o.getStatus()))
				.utente(utenteMap.buildUtenteDTO(o.getUtente()))
				.ordineAlcolico(ordAlcMap.buildOrdineAlcolicoDTOList(o.getListOrdineAlcolico()))
				.ordineBox(ordBoxMap.buildOrdineBoxDTOList(o.getListOrdineBox()))
				.ordineDeg(OrdineDegustazioneMap.buildOrdineDegustazioneDTOList(o.getListOrdineDegustazione()))
				.indirizzo_destinazione(o.getIndirizzoDestinazione())
				.build();
	}
}