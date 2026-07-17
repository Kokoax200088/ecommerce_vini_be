package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.VenditoreDTO;
import com.betacom.ec.models.Venditore;

@Component
public class VenditoreMap {
	
	private final CantinaMap cantinaMap;

    public VenditoreMap(CantinaMap cantinaMap) {
        this.cantinaMap = cantinaMap;
    }
	public  List<VenditoreDTO> buildVenditoreDTOList(List<Venditore> listVenditore){
		return listVenditore.stream().map(item -> buildVenditoreDTO(item)
				).toList();
	}

	public  VenditoreDTO buildVenditoreDTO(Venditore venditore) {
		return VenditoreDTO.builder()
				.id(venditore.getId())
				.listAlcolico(AlcolicoMap.buildAlcolicoDTOList(venditore.getListAlcolico()))
				.listCantina(cantinaMap.buildCantinaDTOList(venditore.getListCantina()))
				.partitaIva(venditore.getPartitaIva())
				.build();
	}
}
