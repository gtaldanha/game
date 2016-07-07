package br.com.amil.game.model;

import java.time.Instant;
import java.util.List;

public class Partida {

	private String nome;
	private Instant inicio;
	private Instant fim;
	private List<Combate> combates;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Instant getInicio() {
		return inicio;
	}
	public void setInicio(Instant inicio) {
		this.inicio = inicio;
	}
	public Instant getFim() {
		return fim;
	}
	public void setFim(Instant fim) {
		this.fim = fim;
	}
	public List<Combate> getCombates() {
		return combates;
	}
	public void setCombates(List<Combate> combates) {
		this.combates = combates;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((combates == null) ? 0 : combates.hashCode());
		result = prime * result + ((fim == null) ? 0 : fim.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Partida other = (Partida) obj;
		if (combates == null) {
			if (other.combates != null)
				return false;
		} else if (!combates.equals(other.combates))
			return false;
		if (fim == null) {
			if (other.fim != null)
				return false;
		} else if (!fim.equals(other.fim))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Partida [nome=" + nome + ", inicio=" + inicio + ", fim=" + fim + ", combates=" + combates + "]";
	}
}
