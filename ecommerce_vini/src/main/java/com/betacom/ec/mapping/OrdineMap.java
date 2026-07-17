package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.models.Ordine;

@Component
public class OrdineMap {
	private final OrdineAlcolicoMap ordAlcMap;

    public OrdineMap(OrdineAlcolicoMap ordAlcMap) {
        this.ordAlcMap = ordAlcMap;
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
				.utente(UtenteMap.buildUtenteDTO(o.getUtente()))
				.ordineAlcolico(ordAlcMap.buildOrdineAlcolicoDTOList(o.getListOrdineAlcolico()))
				.build();
	}
}
