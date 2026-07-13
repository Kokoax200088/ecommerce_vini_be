package com.betacom.ec.dto.output;

import java.util.List;

import com.betacom.ec.models.Alcolico;
import com.betacom.ec.models.Ordine;
import com.betacom.ec.models.Status;

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
	//private OrdineDTO ordine;
	private StatusDTO status;
	private List<AlcolicoDTO> listAlcolico;
	//private CantinaDTO cantina;
}
