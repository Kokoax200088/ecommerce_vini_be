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
public class StatusRequest {
	
	private Integer id;
	@NotNull(groups=ValidationGroups.Create.class, message="nome_missing")
	private String nome;
	@NotNull(groups=ValidationGroups.Create.class, message="descrizione_status_missing")
	private String descrizione;
}
