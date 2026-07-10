package com.betacom.ec.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "degustazione")
public class Degustazione {
	//id degustazione generato automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//nome degustazione non opzionale
	@Column(
			name = "nome",
			nullable = false
			)
	private String nome;
	
	//descrizione della degustazione
	@Column(
			name = "descrizione",
			nullable=true
	)
	private String descrizione;
	
	//prezzo degustazione non opzionale
	@Column(
			name = "prezzo",
			nullable = false
			)
	private Double prezzo;
	
	//data di inizio degustazione non opzionale
	@Column(
			name = "data_inizio",
			nullable = false
			)
	private LocalDateTime dataInizio;
	
	//data di fine degustazione non opzionale
	@Column(
			name = "data_fine",
			nullable = false
			)
	private LocalDateTime dataFine;
	
	//collegamento alla cantina
	@ManyToOne
	@JoinColumn(
			name = "id_cantina",
			foreignKey = @ForeignKey(name = "fk_degustazione_cantina")
			)
	private Cantina cantina;
	
	
}
