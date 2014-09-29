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
 *
 */
public class BreadthFirstSearch extends Search {

	public BreadthFirstSearch(IPuzzleProblem problem){
		super(problem, "Busca em Largura (BL)", new ReguaPuzzleSolution("BL"));
	}
	
	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

	@Override
	public List<Node> search() {
		IPuzzleProblem problem = this.getProblem();
		Node s = problem.getFirst();
		List<Node> path = new Stack<Node>();
		
    	if(this.isMeta(s.getState(), problem)){
    		path = pathToRoot(s);
    		return path;
    	}
    	
        Queue<Node> q = new LinkedList<Node>();
        HashSet<Node> visited= new HashSet<Node>();
        
        visited.add(s);
        q.add(s);
        
        while (!q.isEmpty()) {
            Node v = q.poll();
            
            IState state = v.getState();
            
            v.printNode();
            
            for(Object legalAction: problem.getLegalActions(state)){
            	Node w = problem.makeChild(v, legalAction, state);
                if (!visited.contains(w)) {
                    visited.add(w);
                    q.add(w);
                }
                if(this.isMeta(w.getState(), problem)){
                	path = pathToRoot(w);
                	return path;
                }
            }
        }
        return path;
    }
}
