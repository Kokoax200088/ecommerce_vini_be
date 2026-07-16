package com.betacom.ec.mapping;

import java.util.List;

import com.betacom.ec.dto.output.RuoloDTO;
import com.betacom.ec.models.Ruolo;

public class RuoloMap {
	public static List<RuoloDTO> buildRuoloDTOList(List<Ruolo> listRuolo){
		return listRuolo.stream().map(item -> buildRuoloDTO(item)
				).toList();
	}
	
	public static RuoloDTO buildRuoloDTO (Ruolo ruolo) {
		return RuoloDTO.builder()
				.id(ruolo.getId())
				.nome(ruolo.getNome())
				.canManage(ruolo.getCanManage())
				.canBuy(ruolo.getCanBuy())
				.canSell(ruolo.getCanSell())
				.listUtente(UtenteMap.buildUtenteDTOList(ruolo.getListUtente()))
				.build();
	}
}
