package br.com.amil.game.model;

import java.time.Instant;

public class Combate {

	private Instant horario;
	private Jogador vencedor;
	private Jogador perdedor;
	private String arma;
	
	public Instant getHorario() {
		return horario;
	}
	public void setHorario(Instant horario) {
		this.horario = horario;
	}
	public Jogador getVencedor() {
		return vencedor;
	}
	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}
	public Jogador getPerdedor() {
		return perdedor;
	}
	public void setPerdedor(Jogador perdedor) {
		this.perdedor = perdedor;
	}
	public String getArma() {
		return arma;
	}
	public void setArma(String arma) {
		this.arma = arma;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arma == null) ? 0 : arma.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((perdedor == null) ? 0 : perdedor.hashCode());
		result = prime * result + ((vencedor == null) ? 0 : vencedor.hashCode());
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
		Combate other = (Combate) obj;
		if (arma == null) {
			if (other.arma != null)
				return false;
		} else if (!arma.equals(other.arma))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (perdedor == null) {
			if (other.perdedor != null)
				return false;
		} else if (!perdedor.equals(other.perdedor))
			return false;
		if (vencedor == null) {
			if (other.vencedor != null)
				return false;
		} else if (!vencedor.equals(other.vencedor))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Combate [horario=" + horario + ", vencedor=" + vencedor + ", perdedor=" + perdedor + ", arma=" + arma
				+ "]";
	}
}
