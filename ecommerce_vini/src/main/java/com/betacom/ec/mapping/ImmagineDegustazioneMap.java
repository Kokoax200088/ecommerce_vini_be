package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.ImmagineDegustazioneDTO;
import com.betacom.ec.models.ImmagineDegustazione;

public class ImmagineDegustazioneMap {
	    public static List<ImmagineDegustazioneDTO> buildImmagineDegustazioneDTOList(List<ImmagineDegustazione> listImmagineDegustazione){
	        return listImmagineDegustazione.stream().map(item -> buildImmagineDegustazioneDTO(item)
	                ).toList();
	    }
	    
	    public static ImmagineDegustazioneDTO buildImmagineDegustazioneDTO (ImmagineDegustazione immagineCantina) {
	        return ImmagineDegustazioneDTO.builder()
	                .id(immagineCantina.getId())
	                .id_degustazione(immagineCantina.getDegustazione().getId())
	                .url(immagineCantina.getUrl())
	                .build();
	    }
}
