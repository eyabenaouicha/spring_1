package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Operation implements Serializable{
	public static final int VERSEMENT = 1;
    public static final int VIREMENT = 0;
    public static final int RETRAIT = 2;
  @Id
  @GeneratedValue
  private Long numeroOperation;
  private Date dateOperation;
  private double montant;
  private int type;
  @ManyToOne
  @JoinColumn(name="codecompte")
    private Compte compte;
	
	public Operation(Long numeroOperation, Date dateOperation, double montant, int type, Compte compte) {
		super();
		this.numeroOperation = numeroOperation;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.type = type;
		this.compte = compte;
	}
	public Operation(Long numeroOperation, Date dateOperation, double montant, int type) {
		super();
		this.numeroOperation = numeroOperation;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.type = type;
		
	}
	public Operation( Date dateOperation, double montant, int type, Compte compte) {
		super();
		
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.type = type;
		this.compte = compte;
	}
	
	public Operation(Date dateOperation, double montant, int type) {
		super();
		
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.type = type;
		
	}
	public Operation() {
		super();
	}
	public Long getNumeroOperation() {
		return numeroOperation;
	}
	public void setNumeroOperation(Long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
}
