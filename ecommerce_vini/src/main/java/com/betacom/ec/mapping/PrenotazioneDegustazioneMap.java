package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;
import com.betacom.ec.models.PrenotazioneDegustazione;

public class PrenotazioneDegustazioneMap {
	public static List<PrenotazioneDegustazioneDTO> buildPrenotazioneDegustazioneDTOList(List<PrenotazioneDegustazione> lPD){
		return lPD.stream()
				.map (a -> buildPrenotazioneDegustazioneDTO(a)
						).toList();
	}
	
	public static PrenotazioneDegustazioneDTO buildPrenotazioneDegustazioneDTO(PrenotazioneDegustazione pd) {
		return PrenotazioneDegustazioneDTO.builder()
				.id(pd.getId())
//				.cantina(CantinaMap.buildCantinaDTO(pd.getCantina()))
				.degustazione(DegustazioneMap.buildDegustazioneDTO(pd.getDegustazione()))
				.ordine(OrdineMap.buildOrdineDTO(pd.getOrdine()))
				.status(StatusMap.buildStatusDTO(pd.getStatus()))
				.build();
	}
}
