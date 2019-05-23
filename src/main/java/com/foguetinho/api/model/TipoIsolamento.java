package com.foguetinho.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="transaction_type")
public class TipoIsolamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CD_TRANSACTION_TYPE")
    @SequenceGenerator(sequenceName = "SEQ_CD_TRANSACTION_TYPE", 
                       name = "SEQ_CD_TRANSACTION_TYPE", allocationSize = 1, initialValue = 1)
	@Column(name="cd_transaction_type")
	private int id;
	
	@NotNull
	@Column(name="ds_transaction_type")
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
