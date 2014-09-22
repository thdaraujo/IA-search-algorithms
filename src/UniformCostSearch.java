import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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

	private PriorityQueue<Node> frontier;
	private HashMap<Node, Float> frontierValues;
	private HashSet<Node> visited;
	
	public UniformCostSearch(IPuzzleProblem problem) {
		super(problem, "Busca de Custo Uniforme (BCU)");
		
		visited = new HashSet<Node>();
		frontierValues = new HashMap<Node, Float>();
		
		frontier = new PriorityQueue<Node>(100, new Comparator<Node>() {
	        public int compare(Node node1, Node node2) {
	        	IState a = node1.getState(),
	        			b = node2.getState();
	        	int result = Float.compare(a.getCostTotal(), b.getCostTotal());
	        	return result;
	        }
	    });
	}
	
	@Override
	public List<IState> search() {
		float cost = 0;
		IPuzzleProblem problem = this.getProblem();
		Node root = problem.getFirst();
		frontier.add(root);
		
		while(!frontierIsEmpty()){
			
			Node v = frontier.poll();
			IState state = v.getState();
			if(this.isMeta(state, problem)){
				return pathToRoot(v);
			}
			visited.add(v);
			
			for(Object legalAction: problem.getLegalActions(state)){
            	Node w = problem.makeChild(v, legalAction, state);
                if (!visited.contains(w)) { //TODO hashset para els na frontier
                	if(!frontierContains(w)){
                		addToFrontier(w);
                	}
                	else if(getValueFrontier(w) < w.getState().getCostTotal()){
                		
                	}
                }
            }
		}
		return null; //TODO
	}
	
	public void addToFrontier(Node n){
		this.frontier.add(n);
		this.frontierValues.put(n, n.getState().getCostTotal());
	}
	
	public boolean frontierContains(Node n){
		return this.frontierValues.containsKey(n);
	}
	
	public void getFromFrontier(){
		Node n = this.frontier.element();
		this.frontierValues.remove(n);
	}
	
	public float getValueFrontier(Node n){
		return this.frontierValues.get(n);
	}
	
	public boolean frontierIsEmpty(){
		return this.frontier.isEmpty();
	}
	
	/*
	 
	 procedure UniformCostSearch(Graph, root, goal)
	  node := root, cost = 0
	  frontier := priority queue containing node only
	  explored := empty set
	  do
	    if frontier is empty
	      return failure
	    node := frontier.pop()
	    if node is goal
	      return solution
	    explored.add(node)
	    for each of node's neighbors n
	      if n is not in explored
	        if n is not in frontier
	          frontier.add(n)
	        else if n is in frontier with higher cost
	          replace existing node with n
	  
	  
	  
	 
	 */
	
	
	
	
	
	

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

}
