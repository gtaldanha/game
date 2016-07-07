package br.com.amil.game.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.amil.game.model.Combate;
import br.com.amil.game.model.Jogador;
import br.com.amil.game.model.Partida;

public class LeitorBD {
	
	private static final String token = "match";
	
	
	public List<Partida> ler(String nomeDoArquivo){
		List<String> linhas = lerArquivo(nomeDoArquivo);
		return lerLinhas(linhas);
	}
	
	
	private List<String> lerArquivo(String nomeDoArquivo){
		
		List<String> linhas = new ArrayList<String>();
		
	    try {
	      FileReader fr = new FileReader(nomeDoArquivo);
	      BufferedReader bf = new BufferedReader(fr);
	 
	      String linha = bf.readLine();
	      while (linha != null) {
	    	  linhas.add(linha);
	        linha = bf.readLine(); 
	      }
	      fr.close();
	    } catch (IOException e) {
	        System.err.printf("Erro leitura do arquivo " + e.getMessage());
	    }
	    return linhas; 
	}	
	
	private List<Partida> lerLinhas(List<String> linhas){
		
		Partida partida = null;
		List<Partida> partidas = new ArrayList<Partida>();
		
		for(String linha: linhas){
			
			//Inicio ou fim da partida
			if(linha.toLowerCase().contains(token)){
				
				if(partida == null){
				
					partida = getInicioDaPartida(linha);
				
				}else{
					
					partida.setFim(getData(linha));
					partidas.add(partida);
					partida = null;
				}
				
			//Linha de Combate	
			}else{

				if(partida.getCombates() == null){
					partida.setCombates(new ArrayList<Combate>());
				}
				partida.getCombates().add(getCombate(linha));
				
			}
		}
		return partidas;
	}
	
	private Combate getCombate(String linha) {

		Combate combate = new Combate();
		combate.setHorario(getData(linha.substring(0,18)));
		
		String dados = linha.substring(22);
		
		String[] nomes = dados.split(" killed ");
		combate.setVencedor(new Jogador(nomes[0]));
		combate.setPerdedor(new Jogador(nomes[1].split(" ")[0]));
		combate.setArma(nomes[1].split(" ")[nomes.length]);
		
		return combate;
	}
	
	
	private Partida getInicioDaPartida(String linha) {
		
		Partida partida = new Partida();
		partida.setInicio(getData(linha.substring(0,18)));
		partida.setNome(linha.substring(33, linha.length()-13));
		return partida;
	}

	private Instant getData(String srtData) {
		
        DateFormat df 			= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");		
       
        try {
			return df.parse(srtData).toInstant();
		} catch (ParseException e) {
			return Instant.now();
		}
	}	
}
