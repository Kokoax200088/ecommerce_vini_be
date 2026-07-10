package com.betacom.ec.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteRequest {
	
	@NotNull (groups = ValidationGroups.Update.class , message ="utente.id.missing")
	private Integer id;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="utente.nome.missing")
	private String nome;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="utente.cognome.missing")
	private String cognome;
	
	@Email
	@NotNull (groups = {ValidationGroups.Create.class, ValidationGroups.Login.class} , message ="utente.email.missing")
	private String email;
	
	@NotNull (groups = {ValidationGroups.Create.class, ValidationGroups.Login.class} , message ="utente.password.missing")
	private String password;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="utente.dataNascita.missing")
	private String dataNascita;
	
	@NotNull (groups = ValidationGroups.Create.class , message ="utente.ruolo.missing")
	private Integer idRuolo;
}
