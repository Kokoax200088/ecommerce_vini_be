package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.DegustazioneDTO;
import com.betacom.ec.models.Degustazione;
@Component
public class DegustazioneMap {
	
	private final ImmagineDegustazioneMap immMap;

    public DegustazioneMap(ImmagineDegustazioneMap immMap) {
        this.immMap = immMap;
    }
	
	public  List<DegustazioneDTO> buildDegustazioneDTOList(List<Degustazione> lPA) {
		return lPA.stream()
				.map(r -> buildDegustazioneDTO(r)).toList();
	}
	
	public  DegustazioneDTO buildDegustazioneDTO(Degustazione p) { // TODO: aggiungere
		return DegustazioneDTO.builder()
				.id(p.getId())
				.alcolici(AlcolicoMap.buildAlcolicoDTOList(p.getListAlcolico()))
				.immagini(immMap.buildImmagineDegustazioneDTOList(p.getListImmagine()))
				.dataInizio(p.getDataInizio())
				.dataFine(p.getDataFine())
				.descrizione(p.getDescrizione())
				.id_cantina(p.getCantina().getId()) 
				.build();
	}
}
