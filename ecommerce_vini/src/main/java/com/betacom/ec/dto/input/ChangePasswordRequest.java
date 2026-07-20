package com.betacom.ec.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordRequest {
	
	@NotNull //CHECK in teoria sono tutti campi non nulli se vogliamo cambiare la password no?
	private String email;
	
	@NotNull
	private String oldPassword;
	
	@NotNull
	private String newPassword;
}
