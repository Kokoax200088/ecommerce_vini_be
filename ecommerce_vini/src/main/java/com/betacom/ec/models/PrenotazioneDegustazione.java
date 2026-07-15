package com.betacom.ec.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

@Setter
@Getter
@Entity
@ToString
@Table(name = "prenotazione_degustazione")
public class PrenotazioneDegustazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_ordine",
			foreignKey= @ForeignKey(name="fk_ordine_alcolico_ordine")
			)
	private Ordine ordine;
	
	@ManyToOne
	@JoinColumn (
			name="id_box",
			foreignKey = @ForeignKey(name ="fk_ordine_box" )	
			)
	private Box box;
	
	@ManyToOne
	@JoinColumn(
		name="status",
		foreignKey = @ForeignKey(name ="fk_status_ordine_alcolico" )
			)
	private Status status;
	
	@ManyToOne
	@JoinColumn(
			name="cantina",
			foreignKey = @ForeignKey(name="fk_ordine_alcolico_cantina")
			)
	private Cantina cantina;
	
}
