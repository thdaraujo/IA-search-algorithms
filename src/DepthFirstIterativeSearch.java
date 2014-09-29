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

	public DepthFirstIterativeSearch(IPuzzleProblem problem){
		super(problem, "Busca em Profundidade Iterativa (BPI)", new ReguaPuzzleSolution("BPI"));
	}
	
	@Override
	public List<Node> search() {
		IPuzzleProblem problem = this.getProblem();
		int limit = 1, maximum = 100;
		List<Node> path = new LinkedList<Node>();
		
		ISearch depthFirstLimitedSearch;
		while(limit < maximum && (path == null || path.isEmpty())){
			depthFirstLimitedSearch = new DepthFirstLimitedSearch(problem, limit++);
			path = depthFirstLimitedSearch.search();
			
			int found = path != null? path.size() : 0;
			//System.out.println(limit + " BPI encontrou caminho com " + found + " nodes.");
			
		}
		return path;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

}
