package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table(name = "posizione")
@ToString
public class Posizione {
	//id posizione generato automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//latitudine tipo float non opzionale
	@Column(
			name = "latitudine",
			nullable = false
			)
	private Double latitudine;
	
	//longitudine tipo float non opzionale
	@Column(
			name = "longitudine",
			nullable = false
			)
	private Double longitudine;
	
	//descrizione posizione tipo stringa opzionale
	@Column(
			name = "descrizione",
			nullable = true
			)
	private String descrizione;

	//collegamento con Cantina
	@ToString.Exclude
	@OneToOne(
			mappedBy = "posizione",
			fetch = FetchType.LAZY 
			)
	private Cantina cantina;
	
	
}


