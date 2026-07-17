package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.ImmagineDegustazioneDTO;
import com.betacom.ec.models.ImmagineDegustazione;
import com.betacom.ec.services.interfaces.IUploadService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class ImmagineDegustazioneMap {
	private final IUploadService uplS;
	    public  List<ImmagineDegustazioneDTO> buildImmagineDegustazioneDTOList(List<ImmagineDegustazione> listImmagineDegustazione){
	        return listImmagineDegustazione.stream().map(item -> buildImmagineDegustazioneDTO(item)
	                ).toList();
	    }
	    
	    public  ImmagineDegustazioneDTO buildImmagineDegustazioneDTO (ImmagineDegustazione imgDeg) {
	        return ImmagineDegustazioneDTO.builder()
	                .id(imgDeg.getId())
	                .id_degustazione(imgDeg.getDegustazione().getId())
	                .url(imgDeg.getUrl() == null ? null : uplS.buildUrl(imgDeg.getUrl()))
	                .build();
	    }
}
