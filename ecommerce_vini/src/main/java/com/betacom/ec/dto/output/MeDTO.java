package com.betacom.ec.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeDTO {
	private String id;
	private String role;
//	private Boolean mailValidate; //not sure about the usage, just copied from the repo
}
