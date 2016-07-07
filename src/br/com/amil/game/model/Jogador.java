package br.com.amil.game.model;

public class Jogador {

	private String 	nome;
	private Integer vitorias;
	private Integer derotas;
	
	public Jogador(){
		super();
	}
	public Jogador(String nome) {
		super();
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String jogador) {
		this.nome = jogador;
	}
	public Integer getVitorias() {
		return vitorias;
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	public Integer getDerotas() {
		return derotas;
	}
	public void setDerotas(Integer derotas) {
		this.derotas = derotas;
	}
	
	
}
