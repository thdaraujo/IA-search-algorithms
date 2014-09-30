import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BridgeCrossingProblem implements IPuzzleProblem {

	private int N;
	private int maxMinutes;
	private Node first;
	
	private final String WEST_TO_EAST = ">>";
	private final String EAST_TO_WEST = "<<";
	
	public BridgeCrossingProblem(List<String> problemDefinition) throws Exception{
		init(problemDefinition);
	}
	
	private void init(List<String> problemDefinition) throws Exception {
		//validation
		if(problemDefinition == null || problemDefinition.size() == 0){
			throw new Exception("problem definition is invalid!");
		}
		
		Pattern intPattern = Pattern.compile("\\d+");
		Matcher matcher = intPattern.matcher(problemDefinition.get(0));
		
		if(matcher.find()){
			String sN = matcher.group();
			this.N = Integer.parseInt(sN);
		}
		if(matcher.find()){
			String sMaxMinutes = matcher.group();
			this.maxMinutes = Integer.parseInt(sMaxMinutes);
		}
		if(this.N <= 0 || this.maxMinutes <= 0){
			throw new Exception("N or max_minutes is invalid!");
		}
		
		HashSet<Integer> peopleAtWest = new HashSet<Integer>();
		for(int pos = 2; pos < this.N + 2 && pos < problemDefinition.size(); pos++){
			String person = problemDefinition.get(pos);
			Integer p = Integer.parseInt(person);
			if(!peopleAtWest.add(p)){
				System.out.println("people times should be unique!");
			}
		}
		
		//end validation
		
		IState initial = new BridgeCrossingState(new Action(), peopleAtWest, new HashSet<Integer>()); 
		this.first = makeChild(null, new Action(), initial);
	}

	@Override
	public Node getFirst() {
		return this.first;
	}

	@Override
	public PuzzleType getPuzzleType() {
		return PuzzleType.TravessiaPonte;
	}

	@Override
	public int N() {
		return this.N;
	}

	private Node makeChild(Node parent, Object legalAction, IState state) {
		Action action = (Action)legalAction;
		IState newState = (IState)act(action, (BridgeCrossingState) state);
		
		//float childStepCost = Math.abs(action.getShift());
		//float parentCostTotal = state.getCostTotal();
		//float totalCost = parentCostTotal + childStepCost;
		
		//float heuristics = getHeuristics1(state); //TODO somente para A-star e IDA-star
		
		int depth = parent != null? parent.getDepth() + 1 : 1;
		
		return new Node(parent, newState, depth);
	}
	
	private BridgeCrossingState act(Action legalAction, BridgeCrossingState parentState) {
		Set<Integer> peopleAtWest = new HashSet<Integer>(parentState.getPeopleAtWest());
		Set<Integer> peopleAtEast = new HashSet<Integer>(parentState.getPeopleAtEast());
		
		Set<Integer> movingPeople = legalAction.getPeople();
		
		if(legalAction.getDirection().equals(WEST_TO_EAST)){
			peopleAtWest.removeAll(movingPeople);
			peopleAtEast.addAll(movingPeople);
		}
		else if(legalAction.getDirection().equals(EAST_TO_WEST)){
			peopleAtWest.addAll(movingPeople);
			peopleAtEast.removeAll(movingPeople);
		}
		return new BridgeCrossingState(legalAction, peopleAtWest, peopleAtEast);
	}

	@Override
	public boolean goalTest(IState state) {
		if(state == null) return false;
		BridgeCrossingState bcs = (BridgeCrossingState) state; 
		return bcs != null &&
				bcs.getPeopleAtEast().size() == this.N && 
				bcs.getPeopleAtWest().size() == 0 &&
				bcs.getCostTotal() <= this.maxMinutes;
		
		//TODO ver o max minutos tambem
	}

	@Override
	public float getHeuristics1(String stateDefinition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeuristics2(String stateDefinition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Node> getDescendants(Node parent) {
		//TODO decidir se deve ir para esquerda ou direita
		
		List<Node> descendants = new ArrayList<Node>();
		BridgeCrossingState parentState = (BridgeCrossingState)parent.getState();
		
		String direction;
		if(parentState.getActionDirection().equals(EAST_TO_WEST) || 
				parentState.getActionDirection().equals("")){ 
			//invert direction on children or
			//go west on initial
			direction = WEST_TO_EAST;
		}
		else{
			direction = EAST_TO_WEST;
		}
		
		System.out.println("pai: " + parentState.getActionDirection());
		System.out.println("filho: " + direction);
		
		for(Integer person : parentState.getPeopleAtWest()){
			Set<Integer> people = new HashSet<>();
			people.add(person);
			
			Action legalAction = new Action(0, people, direction);
			Node child = this.makeChild(parent, legalAction, parentState);
			descendants.add(child);
		}
		
		//TODO fazer o 2 x 2 tambem...
		
		return descendants;
	}

}
