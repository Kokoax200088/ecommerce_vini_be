package com.betacom.ec.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "ordine")
public class Spedizione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="corriere", nullable=false)
	private String corriere;
	
	@Column(name="codice_tracciamento", nullable=false)
	private String codice_tracciamento;
	
	@ManyToOne(optional= true)
    @JoinColumn(name = "id_cantina", referencedColumnName= "id")
	private Cantina cantina;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	private Cliente cliente;
	
	@OneToOne(
			cascade = CascadeType.REMOVE,
			orphanRemoval = true			
			)
	@JoinColumn(
			name="Status",
			referencedColumnName = "id",
			foreignKey = @ForeignKey(name ="fk_status_ordine" )
			)
	private Status status;
}
