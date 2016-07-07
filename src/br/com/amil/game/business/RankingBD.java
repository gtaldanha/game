package br.com.amil.game.business;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.amil.game.model.Combate;
import br.com.amil.game.model.Jogador;
import br.com.amil.game.model.Partida;

public class RankingBD {
	
	
	public List<Jogador> getPlacar(Partida partida){
		
		List<Jogador> placar = new ArrayList<Jogador>();
		
		List<Combate> combates = partida.getCombates();
		Map<String, Integer> vencedores = getVencedores(combates);
		Map<String, Integer> perdedores = getPerdedores(combates);
		
		List<String> jogadores = getJogadores(vencedores, perdedores);		
		
		jogadores.remove("<WORLD>");
		
		for(String jogador:jogadores){
			
			Jogador score = new Jogador();
			
			if(vencedores.containsKey(jogador)){
				score.setVitorias(vencedores.get(jogador));
			}else{
				score.setVitorias(0);
			}
			
			if(perdedores.containsKey(jogador)){
				score.setDerotas(perdedores.get(jogador));
			}else{
				score.setDerotas(0);
			}
			
			score.setNome(jogador);
			placar.add(score);
		}
		
		placar.sort( (Comparator.comparing(Jogador::getVitorias)).reversed() );
		return placar;
	}	
	
	/*
	public Map<String, Integer[]> getPlacar(Partida partida){
		
		Map<String, Integer[]> placar= new HashMap<String, Integer[]>();
		
		List<Combate> combates = partida.getCombates();
		Map<String, Integer> vencedores = getVencedores(combates);
		Map<String, Integer> perdedores = getPerdedores(combates);
		
		List<String> jogadores = getJogadores(vencedores, perdedores);		
		
		for(String jogador:jogadores){
			
			Integer[] totais = new Integer[2];
			
			if(vencedores.containsKey(jogador)){
				totais[0] = vencedores.get(jogador);
			}else{
				totais[0] = 0;
			}
			
			if(perdedores.containsKey(jogador)){
				totais[1] = perdedores.get(jogador);
			}else{
				totais[1] = 0;
			}
			
			placar.put(jogador, totais);
		}
		
		placar.remove("<WORLD>");
		return placar;
	}
	*/
	private List<String> getJogadores(Map<String, Integer> vencedores, Map<String, Integer> perdedores) {
	
		Set<String> v = vencedores.keySet();
		Set<String> p = perdedores.keySet();
		
		List<String> jogadores = new ArrayList<String>();
		for(String  nome:v){
			jogadores.add(nome);
		}
		for(String  nome:p){
			if(!jogadores.contains(nome)){
				jogadores.add(nome);
			}
		}
		return jogadores;
	}
	
	private Map<String, Integer> getVencedores(List<Combate> combates){
		
		Map<String, Integer> vencedores = new HashMap<String, Integer>();
		
		combates.sort( (c1, c2) -> c1.getVencedor().getNome().compareTo(c2.getVencedor().getNome()));
		
		String atual = "";
		Integer total = 1;
		for(Combate combate:combates){
			
			if(total==1){
				atual = combate.getVencedor().getNome();
			}
			
			if(!atual.equals(combate.getVencedor().getNome())){
				vencedores.put(atual, total);
				total=1;
				atual = combate.getVencedor().getNome();
			}else{
				total++;
			}
		}
		vencedores.put(atual, total);
		
		return vencedores;
	}
	
	public Map<String, Integer> getPerdedores(List<Combate> combates){
		
		Map<String, Integer> perdedores= new HashMap<String, Integer>();	
		combates.sort( (c1, c2) -> c1.getPerdedor().getNome().compareTo(c2.getPerdedor().getNome()));
	
		String atual = "";
		Integer total = 1;
		
		for(Combate combate:combates){
			
			if(total==1){
				atual = combate.getPerdedor().getNome();
			}
			
			if(!atual.equals(combate.getPerdedor().getNome())){
				perdedores.put(atual, total);
				total=1;
				atual = combate.getPerdedor().getNome();
			}else{
				total++;
			}
		}
		perdedores.put(atual, total);
		return perdedores;
	}
	
	
	public String getArmaPreferida(List<Combate> combates, Jogador vencedor){
		
		combates.sort( (c1, c2) -> c1.getArma().compareTo(c2.getArma()));

		String atual = "";
		String armaMaisUsada = "";
		Integer total = 1;
		Integer maior = 1;
		
		for(Combate combate:combates){
			
			
			if(vencedor.getNome().equals(combate.getVencedor().getNome())){

				if(total==1){
					atual = combate.getArma();
				}
				
				if(atual.equals(combate.getArma())){
					total++;
				}else{
					if(total>=maior){
						armaMaisUsada = atual;
						maior=total;
					}
					total=1;
					atual = combate.getArma();
				}
			}
		}
		if(total>=maior){
			armaMaisUsada = atual;
		}		
		return armaMaisUsada;
	}

	
	public List<String> getInvictos(List<Combate> combates){
		
		List<String> invictos = new ArrayList<String>();
		
		Map<String, Integer> vencedores = getVencedores(combates);
		Map<String, Integer> perdedores = getPerdedores(combates);
		
		Set<String> nomesDosVencedores = vencedores.keySet();
		Set<String> nomesDosPerdedores = perdedores.keySet();
		
		for(String vencedor: nomesDosVencedores){
			
			if(!nomesDosPerdedores.contains(vencedor)){
				invictos.add(vencedor); 
			}
		}
		return invictos;
	}	
	
	
	public List<Combate> getMaiorSequenciaDeVitorias(List<Combate> combates, Jogador jogador){
		
		List<Combate> sequenciaAtual = new ArrayList<Combate>();
		List<Combate> maiorSequencia = new ArrayList<Combate>();
		for(Combate combate:combates){
			
			if(combate.getVencedor().equals(jogador)){
			
				sequenciaAtual.add(combate);
			
			}else if(combate.getPerdedor().equals(jogador)){

				if(maiorSequencia.isEmpty() || sequenciaAtual.size() > maiorSequencia.size()){
					
					maiorSequencia = sequenciaAtual;
					sequenciaAtual = new ArrayList<Combate>();
				}
			}
		}
		return maiorSequencia;
	}
	
	   
	public List<Jogador> getJogadoresAward(List<Combate> combates){
		
		Integer indexInicio 	= new Integer(0);
		Integer totalDeVitorias = new Integer(0);
		Instant horarioInicio = null;
		Jogador atual = null;
		List<Jogador> premiados = new ArrayList<Jogador>();
		
		for(Combate combate: combates){
			
			if(horarioInicio==null){
				horarioInicio = combate.getHorario();
				atual = combate.getVencedor();
				indexInicio++;
			}
			
			Instant horarioAtual = combate.getHorario().minusSeconds(300);
			if(horarioInicio.isAfter(horarioAtual)){
				if(totalDeVitorias>=5){
					premiados.add(atual);
				}
				horarioInicio = null;
				totalDeVitorias = 0;
			}else{
				if(atual.equals(combate.getVencedor())){
					totalDeVitorias++;
				}
			}
		}
		return premiados;
	}
}
