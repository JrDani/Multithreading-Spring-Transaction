package com.foguetinho.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="reg_telefonico")
public class RegTelefonico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cd_reg_telefonico")
	private Long id;
	
	@NotNull
	@Column(name="nr_telefone_origem")
	private Long telefoneOrigem;
	
	@NotNull
	@Column(name="nr_telefone_destino")
	private Long telefoneDestino;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="tp_transaction")
	private TipoIsolamento tipoIsolamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTelefoneOrigem() {
		return telefoneOrigem;
	}

	public void setTelefoneOrigem(Long telefoneOrigem) {
		this.telefoneOrigem = telefoneOrigem;
	}

	public Long getTelefoneDestino() {
		return telefoneDestino;
	}

	public void setTelefoneDestino(Long telefoneDestino) {
		this.telefoneDestino = telefoneDestino;
	}

	public TipoIsolamento getTipoIsolamento() {
		return tipoIsolamento;
	}

	public void setTipoIsolamento(TipoIsolamento tipoIsolamento) {
		this.tipoIsolamento = tipoIsolamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RegTelefonico other = (RegTelefonico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
