import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
		if(args.length != 1) {
			  System.err.println("file path required as argument!");
			  System.exit(1);
		}
		File f = new File(args[0]);
		List<String> lines = readFile(f);
		
		IPuzzleProblem problem = new ReguaPuzzleProblem(lines);
		List<Object> legalActions = problem.getLegalActions(problem.getFirst().getState());
		
		for(Object action : legalActions){
			System.out.println(action.toString());
		}
		
		ISearch search = new BreadthFirstSearch(problem);
		search.search(null);
		
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
