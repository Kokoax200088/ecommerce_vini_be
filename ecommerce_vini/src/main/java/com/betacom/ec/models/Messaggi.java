package com.betacom.ec.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table (name="messaggi_sistema")
public class Messaggi {
	
	@EmbeddedId
	private MessageID msgId;
	
	private String messaggio;
}

