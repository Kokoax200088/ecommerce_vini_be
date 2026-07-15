package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.CaratteristicaDTO;
import com.betacom.ec.models.Caratteristica;

public class CaratteristicaMap {

	public static List<CaratteristicaDTO> buildCaratteristicaDTOList(List<Caratteristica> lC) {
		return lC.stream()
				.map(c -> buildCaratteristicaDTO(c)).toList();
	}

	public static CaratteristicaDTO buildCaratteristicaDTO(Caratteristica c) {
		return CaratteristicaDTO.builder()
				.id(c.getId())
				.nome(c.getNome())
				.descrizione(c.getDescrizione())
				.build();
	}
}
