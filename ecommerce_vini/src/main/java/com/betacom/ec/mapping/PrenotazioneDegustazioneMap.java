package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.PrenotazioneDegustazioneDTO;
import com.betacom.ec.models.PrenotazioneDegustazione;

@Component
public class PrenotazioneDegustazioneMap {
	
	private final CantinaMap cantinaMap;
	private final OrdineMap ordMap;

    public PrenotazioneDegustazioneMap(CantinaMap cantinaMap, OrdineMap ordMap) {
        this.cantinaMap = cantinaMap;
        this.ordMap = ordMap;
    }
	public  List<PrenotazioneDegustazioneDTO> buildPrenotazioneDegustazioneDTOList(List<PrenotazioneDegustazione> lPD){
		return lPD.stream()
				.map (a -> buildPrenotazioneDegustazioneDTO(a)
						).toList();
	}
	
	public  PrenotazioneDegustazioneDTO buildPrenotazioneDegustazioneDTO(PrenotazioneDegustazione pd) {
		return PrenotazioneDegustazioneDTO.builder()
				.id(pd.getId())
				.cantina(cantinaMap.buildCantinaDTO(pd.getCantina()))
				.degustazione(DegustazioneMap.buildDegustazioneDTO(pd.getDegustazione()))
				.ordine(ordMap.buildOrdineDTO(pd.getOrdine()))
				.status(StatusMap.buildStatusDTO(pd.getStatus()))
				.build();
	}
}
