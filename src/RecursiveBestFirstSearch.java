import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class RecursiveBestFirstSearch extends Search {

	public RecursiveBestFirstSearch(IPuzzleProblem problem){
		super(problem, "Recursive Best-First Search (RBFS)", new ReguaPuzzleSolution("RBFS"));
	}
	
	@Override
	public List<Node> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		// TODO Auto-generated method stub
		return false;
	}

}
