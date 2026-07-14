package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "cantina_alcolico")
public class CantinaAlcolico {
	
	//id generato automaticamente per la quantità di un certo vino in una cantina
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		//collegamento cantina
		@ManyToOne
		@JoinColumn (
				name="id_cantina",
				foreignKey = @ForeignKey(name ="fk_cantina_alcolico" )	
				)
		private Cantina cantina;
		
		//collegamento alcolico
		
		@ManyToOne
		@JoinColumn (
				name="id_alcolico",
				foreignKey = @ForeignKey(name ="fk_cantina_alcolico_bottiglia" )	
				)
		private Alcolico alcolico;
		
		@Column(
				name = "quantita",
				nullable = false
				)
		private Integer quantita;
}
