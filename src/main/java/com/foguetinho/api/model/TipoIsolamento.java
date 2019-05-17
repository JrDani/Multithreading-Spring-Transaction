package com.foguetinho.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="transaction_type")
public class TipoIsolamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cd_transaction_type")
	private int id;
	
	@NotNull
	@Column(name="ds_transactional_type")
	private String dsTransactionType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDsTransactionType() {
		return dsTransactionType;
	}

	public void setDsTransactionType(String dsTransactionType) {
		this.dsTransactionType = dsTransactionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoIsolamento other = (TipoIsolamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
