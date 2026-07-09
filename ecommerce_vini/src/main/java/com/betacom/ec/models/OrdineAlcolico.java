package com.betacom.ec.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

@Setter
@Getter
@Entity
@ToString
@Table(name = "ordine_alcolico")
public class OrdineAlcolico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="data_ordine", nullable=false)
	private LocalDate data_ordine;
	
	@ManyToOne
	@JoinColumn(name="id_ordine",
			foreignKey= @ForeignKey(name="fk_ordine_alcolico_ordine")
			)
	private Ordine ordine;
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(
			name="alcolico_ordine_alcolico",
			joinColumns = @JoinColumn (name = "ordine_alcolico_id" ),
			inverseJoinColumns = @JoinColumn (name = "alcolico_id")
			)
	private List<Alcolico> listAlcolico;
	@OneToOne
	@JoinColumn(
		name="status",
		referencedColumnName = "id",
		foreignKey = @ForeignKey(name ="fk_status_ordine_alcolico" )
			)
	private Status status;
	
	@OneToOne
	@JoinColumn(
			name="cantina",
			referencedColumnName="id",
			foreignKey = @ForeignKey(name="fk_ordine_alcolico_cantina")
			)
	private Cantina cantina;
	
}
