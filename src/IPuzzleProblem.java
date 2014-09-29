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
	public int N();
	public String toString();
	
	public List<Object> getLegalActions(IState state);
	public Node makeChild(Node n, Object action, IState state);
	public boolean goalTest(IState state);
	
	public float getHeuristics1(String stateDefinition);
	public float getHeuristics2(String stateDefinition);
}