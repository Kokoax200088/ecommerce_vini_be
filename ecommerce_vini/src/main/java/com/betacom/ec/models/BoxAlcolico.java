package com.betacom.ec.models;

import java.util.List;

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

@Getter
@Setter
@Entity
@Table(name = "box_alcolico")
public class BoxAlcolico {
	//id generato automaticamente per la quantità di un certo vino in una cantina
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//collegamento box
	@ManyToOne
	@JoinColumn (
			name="id_box",
			foreignKey = @ForeignKey(name ="fk_boxAlcolico_box" )	
			)
	private Box box;
	
	//collegamento alcolico
	@ManyToOne
	@JoinColumn (
			name="id_alcolico",
			foreignKey = @ForeignKey(name ="fk_boxAlcolico_alcolico" )	
			)
	private Alcolico alcolico;
	
	@Column(
			name = "quantita",
			nullable = false
			)
	private Integer quantita;
}
