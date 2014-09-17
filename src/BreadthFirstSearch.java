import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.sun.xml.internal.ws.addressing.ProblemAction;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class BreadthFirstSearch extends Search {

	public BreadthFirstSearch(IPuzzleProblem problem){
		super(problem, "Breadth First Search");
	}
	
	@Override
	public List<IState> search(IState meta) {
		// TODO
		
		bfs(this.getProblem().getFirst());
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}
	
	
	
	private static final int INFINITY = Integer.MAX_VALUE;
    private HashSet<Node> visited;  // marked[v] = is there an s->v path?
    
    // BFS from single source
    private void bfs(Node s) {
    	
    	if(this.isMeta(s.getState(), this.getProblem())){
    		System.out.println("initial state is meta!");
    		return;
    	}
    	
        Queue<Node> q = new LinkedList<Node>();
        visited = new HashSet<Node>();
        
        visited.add(s);
        q.add(s);
        
        while (!q.isEmpty()) {
            Node v = q.remove();
            //for (Node w : G.getAdjacentNodes(v)) {
            
            IPuzzleProblem problem = this.getProblem();
            IState state = v.getState();
            for(Object legalAction: problem.getLegalActions(state)){
            	Node w = problem.makeChild(v, legalAction, state);
                if (!visited.contains(w)) {
                    visited.add(w);
                    q.add(w);
                }
                if(this.isMeta(w.getState(), problem)){
                	System.out.println("meta state found!");
                	System.out.println("Parent: " + w.getParent().getState().getStateDefinition());
                	System.out.println(w.getState().getStateDefinition());
                	
                	List<Node> path = pathToRoot(w);
                	return;
                }
            }
        }
    }
   
    /**
     * Returns the path from <tt>meta</tt> to <tt>initial state</tt>, or
     * <tt>null</tt> if no such path.
     * @param meta
     * @return the sequence of vertices on a shortest path, as an Iterable
     */
    public Stack<Node> pathToRoot(Node meta) {
        Stack<Node> path = new Stack<Node>();
        path.add(meta);
        Node x = meta.getParent();
        while(x != null){
        	path.push(x);
        	x = x.getParent();
        }
        path.push(x);
        return path;
    }
	
	
	
	
	
	
	
}
