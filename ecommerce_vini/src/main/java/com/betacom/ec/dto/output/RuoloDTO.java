package com.betacom.ec.dto.output;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RuoloDTO {
	private Integer id;
	private String nome;
	private Boolean canManage;
	private Boolean canSell;
	private Boolean canBuy;
	
	private List<UtenteDTO> listUtente;
}
