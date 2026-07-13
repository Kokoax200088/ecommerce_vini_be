package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.models.OrdineAlcolico;

public class OrdineAlcolicoMap {
	public static List<OrdineAlcolicoDTO> buildOrdineAlcolicoDTOList(List<OrdineAlcolico> lO){
		return lO.stream()
				.map (a -> buildOrdineAlcolicoDTO(a)
						).toList();
		
	}
	public static OrdineAlcolicoDTO buildOrdineAlcolicoDTO(OrdineAlcolico o) {
		StatusDTO sDTO = StatusDTO.builder()
				.id(o.getId())
				.descrizione(o.getStatus().getDescrizione())
				.nome(o.getStatus().getNome())
				.build();
		return OrdineAlcolicoDTO.builder()
				.id(o.getId())
				.status(sDTO)
				//.listAlcolico(AlcolicoMap.buildAlcolicoDTOList(o.getAlcolico()) CHIEDERE A DANI ALCOLICOMAP 
				.build();
	}
}
