package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.OrdineAlcolicoDTO;
import com.betacom.ec.dto.output.SpedizioneDTO;
import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.models.Spedizione;

public class SpedizioneMap {
	public static List<SpedizioneDTO> buildSpedizioneDTOList(List<Spedizione> lS){
		return lS.stream()
				.map (a -> buildSpedizioneDTO(a)
						).toList();
		
	}
	public static SpedizioneDTO buildSpedizioneDTO(Spedizione o) {
		StatusDTO sDTO = StatusDTO.builder()
				.id(o.getId())
				.descrizione(o.getStatus().getDescrizione())
				.nome(o.getStatus().getNome())
				.build();
		OrdineAlcolicoDTO oDTO = OrdineAlcolicoDTO.builder()
				.id(o.getOrdineAlcolico().getId())
				.status(sDTO)
			//	.listAlcolico(AlcolicoMap.buildAlcolicoDTOList(o.getOrdineAlcolico().getAlcolico()))  CHIEDERE A DANIELE ALCOLICOMAP
				.build();
		return SpedizioneDTO.builder()
				.id(o.getId())
				.corriere(o.getCorriere())
				.codice_tracciamento(o.getCodice_tracciamento())
				.status(sDTO)
				.ordine_alcolico(oDTO)
				.build();
	}
}
