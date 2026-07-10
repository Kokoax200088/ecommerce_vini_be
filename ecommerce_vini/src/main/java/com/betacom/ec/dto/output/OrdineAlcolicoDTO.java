package com.betacom.ec.dto.output;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrdineAlcolicoDTO {
	private Integer id;
	private LocalDate data_ordine;
	private OrdineDTO ordine;
	private List<AlcolicoDTO> listAlcolico;
	private StatusDTO status;
	//private CantinaDTO cantina;
}
