package com.foguetinho.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="REG_TELEFONICO")
public class RegTelefonico {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CD_REG_TELEFONICO")
    @SequenceGenerator(sequenceName = "SEQ_CD_REG_TELEFONICO", 
                       name = "SEQ_CD_REG_TELEFONICO", allocationSize = 1, initialValue = 1)
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
	
	@NotNull
	@Column(name="vl_custo")
	private Double custo;
	
	@NotNull
	@Column(name="tm_ligacao")
	private String tempo_ligacao;

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

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public String getTempo_ligacao() {
		return tempo_ligacao;
	}

	public void setTempo_ligacao(String tempo_ligacao) {
		this.tempo_ligacao = tempo_ligacao;
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
