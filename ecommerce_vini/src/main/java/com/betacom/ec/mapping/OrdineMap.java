package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.models.Ordine;

public class OrdineMap {
	public static List<OrdineDTO> buildOrdineDTOList(List<Ordine> lO){
		return lO.stream()
				.map (a -> buildOrdineDTO(a)
						).toList();
		
	}
	public static OrdineDTO buildOrdineDTO(Ordine o) {
		StatusDTO sDTO = StatusDTO.builder()
				.id(o.getId())
				.descrizione(o.getStatus().getDescrizione())
				.nome(o.getStatus().getNome())
				.build();
		return OrdineDTO.builder()
				.id(o.getId())
				.data_ordine(o.getData_ordine())
				.totale(o.getTotale())
				.indirizzoDestinazione(o.getIndirizzoDestinazione())
				.status(sDTO)
				.ordineAlcolico(OrdineAlcolicoMap.buildOrdineAlcolicoDTOList(o.getListOrdineAlcolico()))
				.build();
	}
}
