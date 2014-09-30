import java.util.LinkedList;
import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class IDAStarSearch extends Search {

	public IDAStarSearch(IPuzzleProblem problem, ISolution solution) {
		super(problem, "IDA*", solution);
		solution.setAlgorithmName(this.getSearchAlgorithm());
	}
	
	@Override
	public List<Node> search() {
		
		IPuzzleProblem problem = this.getProblem();
		Node root = problem.getFirst();
		float bound = root.getHeuristics();
		
		while(true){
			SearchResult result = idaStar(root, 0, bound);
			if(result.isGoalFound()){
				return pathToRoot(result.getGoalNode());
			}
			if(result.getF() == Float.POSITIVE_INFINITY){
				return new LinkedList<Node>(); //not found
			}
			bound = result.getF();
		}
	}
	
	private SearchResult idaStar(Node n, float g, float bound){
		IPuzzleProblem problem = this.getProblem();
		
		n.printNode();
		
		float f = g + n.getHeuristics();
		if(f > bound) return new SearchResult(f);
		
		if(this.isMeta(n.getState(), problem)){
			return new SearchResult(0, true, n);
		}
		
		float min = Float.POSITIVE_INFINITY;
		for(Node sucessor : problem.getDescendants(n)){
        	
        	SearchResult result = idaStar(sucessor, g + sucessor.getCostStep(), bound);
        	if(result.isGoalFound()) return result;
        	if(result.getF() < min) min = result.getF();
		}
		return new SearchResult(min);
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

	private class SearchResult{
		private float f;
		private boolean isGoalFound;
		private Node goalNode;
		
		public SearchResult(float f, boolean isGoalFound, Node goalNode){
			this.f = f;
			this.isGoalFound = isGoalFound;
			this.goalNode = goalNode;
		}
		
		public SearchResult(float f){
			this(f, false, null);
		}
		
		public float getF(){
			return this.f;
		}
		
		public boolean isGoalFound(){
			return this.isGoalFound;
		}
		
		public Node getGoalNode(){
			return this.goalNode;
		}
	}
}
