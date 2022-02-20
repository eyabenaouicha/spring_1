package com.example.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;






	@Entity
	@Table(name = "COMPTE")
	public class Compte implements Serializable {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long code;
		private double solde;
		private Date datecreation;
		@OneToMany(fetch = FetchType.EAGER,mappedBy="compte")
	    @JsonIgnore
	    private Collection<Operation> operations;
		
		
		@OneToOne(targetEntity=Client.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		@JoinColumn(name="client_id")
		private Client client_id;

		
		
		public Compte(long code, double solde, Date datecreation, Client client_id) {
			super();
			this.code = code;
			this.solde = solde;
			this.datecreation = datecreation;
			this.operations = operations;
			this.client_id = client_id;
		}
		public Client getClient_id() {
			return client_id;
		}
		public void setClient_id(Client client_id) {
			this.client_id = client_id;
		}
		public Compte() {
			super();
		}	
		public Compte(double solde, Date datecreation) {
			super();
			
			this.solde = solde;
			this.datecreation = datecreation;
		}
		
	public Collection<Operation> getOperations() {
			return operations;
		}
		public void setOperations(Collection<Operation> operations) {
			this.operations = operations;
		}
	public Compte(long code, double solde, Date datecreation) {
		super();
		this.code = code;
		this.solde = solde;
		this.datecreation = datecreation;
	}

		
	

public long getCode() {
	return code;
}
public void setCode(long code) {
	this.code = code;
}
public double getSolde() {
	return solde;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public Date getDatecreation() {
	return datecreation;
}
public void setDatecreation(Date datecreation) {
	this.datecreation = datecreation;
}



}
