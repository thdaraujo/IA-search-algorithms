import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

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
		super(problem, "Busca em Largura (BL)");
	}
	
	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

	@Override
	public List<IState> search() {
		IPuzzleProblem problem = this.getProblem();
		Node s = problem.getFirst();
		List<IState> path = new Stack<IState>();
		
    	if(this.isMeta(s.getState(), problem)){
    		System.out.println("initial state is meta!");
    		path = pathToRoot(s);
    		return path;
    	}
    	
        Queue<Node> q = new LinkedList<Node>();
        HashSet<Node> visited= new HashSet<Node>();
        
        visited.add(s);
        q.add(s);
        
        while (!q.isEmpty()) {
            Node v = q.remove();
            //for (Node w : G.getAdjacentNodes(v)) {
            
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
                	
                	path = pathToRoot(w);
                	return path;
                }
            }
        }
        return path;
    }
}
