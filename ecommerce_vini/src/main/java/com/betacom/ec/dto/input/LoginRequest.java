package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequest {
	
	@NotNull (groups = ValidationGroups.Create.class , message ="login_invalid")
	private String email;
	@NotNull (groups = ValidationGroups.Create.class , message ="login_invalid")
	private String password;

}
