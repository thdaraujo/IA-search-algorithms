import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class AStarSearch extends Search {
	private Frontier frontier;
	private Frontier openList, closedList;
	private HashSet<Node> visited;

	public AStarSearch(IPuzzleProblem problem){
		super(problem, "Busca A* (A*)");
		
		visited = new HashSet<Node>();
		openList = new Frontier(createPathCostComparator());
		closedList = new Frontier(createPathCostComparator());
	}
	
	@Override
	public List<IState> search() {
		IPuzzleProblem problem = this.getProblem();
		Node root = problem.getFirst();
		openList.add(root, 0);
		
		while(!openList.isEmpty()){
			Node current = openList.poll();
			
			IState state = current.getState();
			if(this.isMeta(state, problem)){
				return pathToRoot(current);
			}
			
			closedList.add(current, f(current));
			
			for(Object legalAction: problem.getLegalActions(state)){
            	Node sucessor = problem.makeChild(current, legalAction, state);
                
            	if(closedList.contains(sucessor)) continue;
            	
            	float tentative_g_score = current.getCostTotal() + sucessor.getCostStep();
            	if(!openList.contains(sucessor) || tentative_g_score < sucessor.getCostTotal()){
            		if(!openList.contains(sucessor)) openList.add(sucessor, f(sucessor));
            	}
            	
			}
		}
		return new LinkedList<>();
	}
	
	private float f(Node w) {
		return w != null? w.getCostTotal() + w.getHeuristics() : Float.MAX_VALUE;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}
	
	private static Comparator<Node> createPathCostComparator() {
		return new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				float thisWeight = node1.getCostTotal() + node1.getHeuristics();  
			    float thatWeight = node2.getCostTotal() + node2.getHeuristics();  
			    if(Math.abs(thisWeight - thatWeight) < 0.001){  
			        return Double.compare(thisWeight, thatWeight);
			    }  
			    else{  
			        return Long.compare(node1.getTimeStamp(), node2.getTimeStamp());  
			    }  
			}
		};
	}
	
	
	
	

}
