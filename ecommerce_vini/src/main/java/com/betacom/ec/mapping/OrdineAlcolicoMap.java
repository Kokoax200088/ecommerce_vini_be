package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.models.OrdineAlcolico;

public class OrdineAlcolicoMap {
	public static List<OrdineAlcolicoDTO> buildOrdineAlcolicoDTOList(List<OrdineAlcolico> lO){
		return lO.stream()
				.map (a -> buildOrdineAlcolicoDTO(a)
						).toList();
		
	}
	public static OrdineAlcolicoDTO buildOrdineAlcolicoDTO(OrdineAlcolico o) {
		return OrdineAlcolicoDTO.builder()
				.id(o.getId())
				.id_status(o.getStatus().getId())
				.id_alcolico(o.getAlcolico().getId())
				.id_cantina(o.getCantina().getId())
				.id_ordine(o.getOrdine().getId())
				.build();
	}
}
