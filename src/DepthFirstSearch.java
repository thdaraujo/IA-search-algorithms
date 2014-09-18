import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 */

/**
 * @author thiagoaraujo
 * Depth-First search on a graph
 *
 */
public class DepthFirstSearch extends Search {

	private HashSet<Node> visited;
	
	public DepthFirstSearch(IPuzzleProblem problem) {
		super(problem, "Busca em Profundidade (BP)");
		visited = new HashSet<Node>();
	}
	
	@Override
	public List<IState> search() {
		IPuzzleProblem problem = this.getProblem();
		return dfs_iterative(problem.getFirst(), problem);
		
		//recursive
		/*
		Node meta = dfs_recursive(problem.getFirst(), problem);
		
		if(meta != null){
			System.out.println("Meta found!");
			return pathToRoot(meta);
		}
		else{
			return new LinkedList<IState>();
		}
		*/
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}
	
	public List<IState> dfs_iterative(Node s, IPuzzleProblem problem)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.push(s);
		List<IState> path = new LinkedList<IState>();
		
		visited.add(s);
		
		while(!stack.isEmpty()){
			Node v = stack.pop();
			
			if(this.isMeta(v.getState(), problem)){
				path = pathToRoot(v);
				return path;
			}
				
			IState state = v.getState();
            for(Object legalAction: problem.getLegalActions(state)){
            	Node w = problem.makeChild(v, legalAction, state);
            	System.out.println("Explorando: " + w.getState().getStateDefinition());
                if (!visited.contains(w)) {
                    visited.add(w);
                    stack.push(w);
                }
            }
		}
		return path;
	}
	
	private Node dfs_recursive(Node s, IPuzzleProblem problem){
		
		visited.add(s);
		
		if(this.isMeta(s.getState(), problem)){
    		return s;
    	}
		
		IState state = s.getState();
		for(Object legalAction: problem.getLegalActions(state)){
        	Node w = problem.makeChild(s, legalAction, state);
        	System.out.println("Explorando: " + w.getState().getStateDefinition());

        	if (!visited.contains(w)) {
        		Node meta = dfs_recursive(w, problem);
        		if(meta != null) return meta;
            }
		}
		return null;
	}
}
