package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.StatusDTO;
import com.betacom.ec.models.Status;

public class StatusMap {
	public static List<StatusDTO> buildStatusDTOList(List<Status> lS){
		return lS.stream()
				.map (a -> buildStatusDTO(a)
						).toList();
	}
	public static StatusDTO buildStatusDTO(Status s) {
		return StatusDTO.builder()
				.id(s.getId())
				.nome(s.getNome())
				.descrizione(s.getDescrizione())
				.build();
	}
}
