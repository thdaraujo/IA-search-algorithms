import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.omg.CORBA.Environment;


/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class ReguaPuzzleProgram {

	/**
	 * Programa recebe um arquivo com a definicao do problema da Regua Puzzle.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		printHello();
		
		if(args.length != 2) {
			 System.err.println("[ERRO] O caminho do arquivo e a sigla do algoritmo de busca são parâmetros esperados!");
			 System.out.println("Exemplo: C:/testes/entrada.txt BP");
			 System.exit(1);
		}
		File f = new File(args[0]);
		String searchParam = args[1];
		
		List<String> lines = readFile(f);
		
		IPuzzleProblem problem = new ReguaPuzzleProblem(lines);
		List<Object> legalActions = problem.getLegalActions(problem.getFirst().getState());
		
		for(Object action : legalActions){
			System.out.println(action.toString());
		}
		
		int limit = 100;
		ISearch search = SearchFactory.getSearch(searchParam, problem, limit);
		List<IState> path = search.search();
		
		for(IState state : path){
			System.out.println(">> " + state.getStateDefinition() + " Custo: " + state.getCostTotal() + " Heuristica: " + state.getHeuristics());
		}
		
	}
	
	private static void printHello() {
		System.out.println("EP 1 - IA - Algoritmos de Busca");
		System.out.println("Thiago Araujo");
		System.out.println("O programa deve receber 2 parâmetros: entrada.txt ALGORITMO-DE-BUSCA");
		System.out.println("Exemplo: C:/testes/entrada.txt BP");
		System.out.println("");
	}

	private static List<String> readFile(File fin) throws IOException {
		// Construct BufferedReader from FileReader
		BufferedReader br = new BufferedReader(new FileReader(fin));
	    List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();
		
		return lines;
	}
}
