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
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}
	
	/*
	private List<IState> dfs(Node s, IPuzzleProblem problem){
		visited.add(s);
		if(this.isMeta(s.getState(), problem)){
    		System.out.println("initial state is meta!");
    		return pathToRoot(s);
    	}
		
		IState state = s.getState();
		for(Object legalAction: problem.getLegalActions(state)){
        	Node w = problem.makeChild(s, legalAction, state);
        	System.out.println("Explorando: " + w.getState().getStateDefinition());
            if (!visited.contains(w)) {
            	return dfs(w, problem);
            }
		}
		System.out.println("meta not found!");
		return new LinkedList<IState>();
	}
	*/
	
	/*1  procedure DFS(G,v):
		2      label v as discovered
		3      for all edges from v to w in G.adjacentEdges(v) do
		4          if vertex w is not labeled as discovered then
		5              recursively call DFS(G,w)
	*/
	
	/*
	1  procedure DFS-iterative(G,v):
		2      let S be a stack
		3      S.push(v)
		4      while S is not empty
		5            v ← S.pop() 
		6            if v is not labeled as discovered:
		7                label v as discovered
		8                for all edges from v to w in G.adjacentEdges(v) do
		9                    S.push(w)
	*/
	
	public List<IState> dfs_iterative(Node s, IPuzzleProblem problem)
	{
		Stack<Node> stack = new Stack<Node>();
		stack.push(s);
		List<IState> path = new LinkedList<IState>();
		
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
	
	
	
}
