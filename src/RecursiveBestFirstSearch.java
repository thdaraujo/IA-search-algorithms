import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class RecursiveBestFirstSearch extends Search {

	public RecursiveBestFirstSearch(IPuzzleProblem problem, ISolution solution){
		super(problem, "RBFS", solution);
		solution.setAlgorithmName(this.getSearchAlgorithm());
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
