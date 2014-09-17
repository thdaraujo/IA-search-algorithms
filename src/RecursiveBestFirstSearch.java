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
		super(problem, "Recursive Best-First Search (RBFS)");
	}
	
	@Override
	public List<IState> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		// TODO Auto-generated method stub
		return false;
	}

}
