package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RuoloRequest {
	
	private Integer id;
	
	@NotNull(groups=ValidationGroups.Create.class, message="nome_missing")
	private String nome;
	@NotNull(groups=ValidationGroups.Create.class, message="canManage_missing")
	private Boolean canManage;
	@NotNull(groups=ValidationGroups.Create.class, message="canSell_missing")
	private Boolean canSell;
	@NotNull(groups=ValidationGroups.Create.class, message="canBuy_missing")
	private Boolean canBuy;
}
