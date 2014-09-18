import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class DepthFirstLimitedSearch extends Search {

	private int limit;
	private static List<IState> cutoffResult = null;
	private HashSet<Node> visited;
	
	public DepthFirstLimitedSearch(IPuzzleProblem problem, int limit){
		super(problem, "Busca em Profundidade Limitada (BPL)");
		
		this.limit = limit;
		visited = new HashSet<Node>();
	}
	
	@Override
	public List<IState> search() {
		IPuzzleProblem problem = this.getProblem();
		return dfs_recursive(problem.getFirst(), problem, this.limit);
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}
	
	private List<IState> dfs_recursive(Node s, IPuzzleProblem problem, int limit){
		
		if(this.isMeta(s.getState(), problem)){
    		return pathToRoot(s);
    	}
		else if(limit == 0){
			return Collections.emptyList();
		}
		else{
						
			IState state = s.getState();
			for(Object legalAction: problem.getLegalActions(state)){
	        	Node w = problem.makeChild(s, legalAction, state);
	        	System.out.println("Explorando: " + w.getState().getStateDefinition());
	        	List<IState> result = dfs_recursive(w, problem, limit - 1);
	        		
	        	if(result != null && !result.isEmpty()){
	        		return result;
	        	}
			}
		}
		return Collections.emptyList();
	}
}
