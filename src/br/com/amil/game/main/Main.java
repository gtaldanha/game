package br.com.amil.game.main;

import java.util.List;
import java.util.Scanner;

import br.com.amil.game.business.LeitorBD;
import br.com.amil.game.business.RankingBD;
import br.com.amil.game.model.Combate;
import br.com.amil.game.model.Jogador;
import br.com.amil.game.model.Partida;

public class Main {

	
	//simulação dos dados de entrada do arquivo
	/*
	public static List<String> getLinhas(){
	
		List<String> linhas = new ArrayList<String>();
		
		linhas.add("23/04/2013 15:34:22 - New match 11348965 has started");
		linhas.add("23/04/2013 15:36:04 - Roman killed Nick using M18");
		linhas.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
		linhas.add("23/04/2013 15:37:04 - Roman killed Nick using M17");
		linhas.add("23/04/2013 15:37:33 - <WORLD> killed Nick by DROWN");
		linhas.add("23/04/2013 15:38:04 - Roman killed Sparta using M16");
		linhas.add("23/04/2013 15:38:33 - Nick killed Sparta using M16");
		linhas.add("23/04/2013 15:39:04 - Roman killed Kropfa using M18");
		linhas.add("23/04/2013 15:39:33 - Nick killed Kropfa using M17");
		linhas.add("23/04/2013 15:40:04 - Roman killed <WORLD> using M18");
		linhas.add("23/04/2013 15:40:33 - Sparta killed Nick using M16");
		linhas.add("23/04/2013 15:41:04 - Sparta killed Jack using M17");
		linhas.add("23/04/2013 15:41:33 - Sparta killed Roman using M17");
		
		
		linhas.add("23/04/2013 15:59:22 - Match 11348965 has ended");
		
		return linhas;
	}
	*/
	
	
	public static void main(String args[]) throws Exception{
		
		LeitorBD leitor = new LeitorBD();
		RankingBD ranking = new RankingBD();
		Scanner scanner = new Scanner(System.in);
		String arquivo =  scanner.nextLine();
		
		List<Partida> partidas = leitor.ler(arquivo);
		
		for(Partida partida:partidas){
			System.out.println("-------------------------------------------------");
			System.out.println("Nome da partida : " + partida.getNome());
			
			List<Jogador> scores = ranking.getPlacar(partida);
			int i = 1;
			for (Jogador score : scores)
			{
				System.out.println(i++ + " - " + score.getNome() + " matou " + score.getVitorias() + " vez(es) e foi morto " + score.getDerotas() + " vez(es)");
			}
			
			System.out.println("Arma mais utilizada pelo vencedor : " + ranking.getArmaPreferida(partida.getCombates(), scores.get(0)));
			System.out.println("-------------------------------------------------");
			System.out.println("Jogadores que mataram 5 vezes em 1 minuto na partida.");
			
			List<Jogador> premiados = ranking.getJogadoresAward(partida.getCombates());
			for(Jogador jogador:premiados){
				System.out.println(jogador.getNome());
			}
			System.out.println("-------------------------------------------------");
			System.out.println("Maior sequência de assassinatos efetuadas pelo jogador " + scores.get(0).getNome() + " sem morrer.");
			List<Combate> sequencia = ranking.getMaiorSequenciaDeVitorias(partida.getCombates(), scores.get(0));
			for(Combate combate: sequencia){
				System.out.println(combate.toString());
			}
			System.out.println("-------------------------------------------------");
		}
	}
}
