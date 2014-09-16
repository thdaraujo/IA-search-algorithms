import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public interface IPuzzleProblem {

	public enum PuzzleType{
		ReguaPuzzle,
		TravessiaPonte
	}
	
	public Node getFirst();
	
	public PuzzleType getPuzzleType();
	public String searchAlgorithm();
	public List<IState> getSolution();
	public String printSolution();
	public int N();
	public String toString();
	
	public List<Object> getLegalActions(IState state);
	public Node makeChild(Node n, Object action, String stateDefinition);
	public boolean goalTest(IState state);
	
}