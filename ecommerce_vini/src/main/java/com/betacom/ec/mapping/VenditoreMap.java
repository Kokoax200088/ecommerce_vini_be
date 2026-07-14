package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.models.Venditore;

public class VenditoreMap {
	public static List<VenditoreDTO> buildVenditoreDTOList(List<Venditore> listVenditore){
		return listVenditore.stream().map(item -> buildVenditoreDTO(item)
				).toList();
	}

	public static VenditoreDTO buildVenditoreDTO(Venditore venditore) {
		return VenditoreDTO.builder()
				.id(venditore.getId())
				.listAlcolico(AlcolicoMap.buildAlcolicoDTOList(venditore.getListAlcolico()))
//				.listCantina(CantinaMap.buildCantinaDTOList(venditore.getListCantina()))
				.build();
	}
}
