package com.betacom.ec.mapping;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.ec.dto.output.RuoloDTO;
import com.betacom.ec.models.Ruolo;

@Component
public class RuoloMap {
	
	private final UtenteMap utenteMap;

	public RuoloMap(UtenteMap utenteMap) {
		this.utenteMap = utenteMap;
	}
	public  List<RuoloDTO> buildRuoloDTOList(List<Ruolo> listRuolo){
		return listRuolo.stream().map(item -> buildRuoloDTO(item)
				).toList();
	}
	
	public  RuoloDTO buildRuoloDTO (Ruolo ruolo) {
		return RuoloDTO.builder()
				.id(ruolo.getId())
				.nome(ruolo.getNome())
				.canManage(ruolo.getCanManage())
				.canBuy(ruolo.getCanBuy())
				.canSell(ruolo.getCanSell())
				.listUtente(utenteMap.buildUtenteDTOList(ruolo.getListUtente()))
				.build();
	}
}
