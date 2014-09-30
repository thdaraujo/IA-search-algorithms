import java.util.LinkedList;
import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class DepthFirstIterativeSearch extends Search {

	public DepthFirstIterativeSearch(IPuzzleProblem problem, ISolution solution){
		super(problem, "BPI", solution);
		solution.setAlgorithmName(this.getSearchAlgorithm());
	}
	
	@Override
	public List<Node> search() {
		IPuzzleProblem problem = this.getProblem();
		int limit = 1, maximum = 100;
		List<Node> path = new LinkedList<Node>();
		
		ISearch depthFirstLimitedSearch;
		while(limit < maximum && (path == null || path.isEmpty())){
			depthFirstLimitedSearch = new DepthFirstLimitedSearch(problem, this.getSolution(), limit++);
			path = depthFirstLimitedSearch.search();
			
			int found = path != null? path.size() : 0;
		}
		return path;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

}
