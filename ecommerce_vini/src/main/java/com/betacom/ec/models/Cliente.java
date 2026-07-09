package com.betacom.ec.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table (name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue	(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JoinColumn(
			name = "id_utente",
			referencedColumnName = "id",
			foreignKey = @ForeignKey (name = "fk_utente_cliente")
			)
	private Utente utente;
	
	@Column(name="indirizzo",
			nullable=false)
	private String indirizzo;
}
