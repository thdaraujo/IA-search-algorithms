import java.util.Comparator;
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
	private Frontier openList, closedList;

	public AStarSearch(IPuzzleProblem problem){
		super(problem, "Busca A* (A*)", new ReguaPuzzleSolution("A*"));
		
		clear();
	}

	private void clear() {
		openList = new Frontier(createPathCostComparator());
		closedList = new Frontier(createPathCostComparator());
	}
	
	@Override
	public List<Node> search() {
		
		IPuzzleProblem problem = this.getProblem();
		Node root = problem.getFirst();
		openList.add(root, f(root));
		
		while(!openList.isEmpty()){
			Node current = openList.poll();
			
			System.out.println("explorando " + current.getState().getStateDefinition() 
					+ " custo: " + current.getCostTotal() 
					+ " h:" + current.getHeuristics());
			
			IState state = current.getState();
			if(this.isMeta(state, problem)){
				return pathToRoot(current);
			}
			
			closedList.add(current, f(current));
			
			for(Object legalAction: problem.getLegalActions(state)){
            	Node sucessor = problem.makeChild(current, legalAction, state);
            	if(closedList.contains(sucessor)) continue;
            	openList.add(sucessor, f(sucessor));
			}
		}
		return new LinkedList<>();
	}
	
	private float f(Node w) {
		return w != null? w.getCostTotal() + w.getHeuristics() : Float.POSITIVE_INFINITY;
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
			    if(Math.abs(thisWeight - thatWeight) > 0.001){  
			        return Double.compare(thisWeight, thatWeight);
			    }  
			    else{  
			        return Long.compare(node1.getTimeStamp(), node2.getTimeStamp());  
			    }  
			}
		};
	}
}
