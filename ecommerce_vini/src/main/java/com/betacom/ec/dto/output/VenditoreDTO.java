package com.betacom.ec.dto.output;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenditoreDTO{
	private Integer id;
	private String partitaIva;
	
	//attributi in join
	private List<CantinaDTO> listCantina;
	private List<AlcolicoDTO> listAlcolico;
}
