package com.betacom.ec.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CantinaAlcolico {
	
	//id generato automaticamente per la quantità di un certo vino in una cantina
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
