import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class UniformCostSearch extends Search {

	private Frontier frontier;
	private HashSet<Node> visited;
	
	public UniformCostSearch(IPuzzleProblem problem) {
		super(problem, "Busca de Custo Uniforme (BCU)");
		
		visited = new HashSet<Node>();
		frontier = new Frontier(createPathCostComparator());
	}
	
	@Override
	public List<IState> search() {
		IPuzzleProblem problem = this.getProblem();
		Node root = problem.getFirst();
		frontier.add(root, root.getCostTotal());
		
		while(!frontier.isEmpty()){
			
			Node v = frontier.poll();
			
			System.out.println("explorando " + v.getState().getStateDefinition() 
					+ " custo: " + v.getCostTotal() 
					+ " h:" + v.getHeuristics());
			
			IState state = v.getState();
			if(this.isMeta(state, problem)){
				return pathToRoot(v);
			}
			visited.add(v);
			
			for(Object legalAction: problem.getLegalActions(state)){
            	Node w = problem.makeChild(v, legalAction, state);
                if (!visited.contains(w)) {
                	if(!frontier.contains(w)){
                		frontier.add(w, w.getCostTotal());
                	}
                	else if(frontier.getValue(w) > w.getCostTotal()){
                		frontier.replace(w, w.getCostTotal());
                	}
                }
            }
		}
		return new LinkedList<>();
	}
	
	private static Comparator<Node> createPathCostComparator() {
		return new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				float thisWeight = node1.getCostTotal();  
			    float thatWeight = node2.getCostTotal();  
			    if(Math.abs(thisWeight - thatWeight) > 0.001){   
			        return Double.compare(thisWeight, thatWeight);
			    }  
			    else{  
			        return Long.compare(node1.getTimeStamp(), node2.getTimeStamp());  
			    }  
			}
		};
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

}
