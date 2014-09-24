import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class ReguaPuzzleProblem implements IPuzzleProblem {
	
	private String validStateRegex;
	private static char EMPTY_SPACE = '-';
	private int N;
	private String initial;
	private Node first;
	
	public ReguaPuzzleProblem(List<String> problemDefinition) throws Exception{
		init(problemDefinition);
	}
	
	private void init(List<String> problemDefinition) throws Exception{
		
		//validation
		if(problemDefinition == null || problemDefinition.size() != 2){
			throw new Exception("problem definition is invalid!");
		}
		else if(problemDefinition.get(1).isEmpty()){
	    	throw new Exception("initial state is invalid!");
	    }
		
		String nSize = problemDefinition.get(0);
		int n = Integer.parseInt(nSize); 
		
		String initialState = problemDefinition.get(1);
		
		if(initialState.length() != n * 2 + 1){
			throw new Exception("initial state length is invalid!");
		}
		else if(initialState.indexOf(EMPTY_SPACE) == -1){
			throw new Exception("initial state should contain one empty space character: '-'");
		}
		//end validation
		
		this.initial = initialState;
		this.N = n;
		this.validStateRegex = "^B{#}A{#}$".replaceAll("#", Integer.toString(N)); 
		IState initial = new ReguaPuzzleState(new Action(), initialState);
		this.first = makeChild(null, new Action(), initial);
	}

	@Override
	public Node getFirst() {
		return this.first;
	}

	/* (non-Javadoc)
	 * @see IPuzzleMaker#getPuzzleType()
	 */
	@Override
	public PuzzleType getPuzzleType() {
		return PuzzleType.ReguaPuzzle;
	}

	/* (non-Javadoc)
	 * @see IPuzzleMaker#getSolution()
	 */
	@Override
	public List<IState> getSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see IPuzzleMaker#printSolution()
	 */
	@Override
	public String printSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see IPuzzleMaker#N()
	 */
	@Override
	public int N() {
		return this.N;
	}

	@Override
	public List<Object> getLegalActions(IState state) {
		List<Object> actionList = new ArrayList<Object>();
		String stateDefinition = state.getStateDefinition();
		
		int length = stateDefinition.length();
		int emptyPosition = getEmptyPosition(stateDefinition);
		
		//left shift
		for(int pos = emptyPosition - 1, i = 0; pos >= 0 && i < this.N; i++, pos--){
			Action leftAction = new Action(pos - emptyPosition);
			actionList.add(leftAction);
		}
		
		//right shift
		for(int pos = emptyPosition + 1, i = 0; pos < length && i < this.N; i++, pos++){
			Action rightAction = new Action(pos - emptyPosition);
			actionList.add(rightAction);
		}
		
		return actionList;
	}

	@Override
	public boolean goalTest(IState state) {
		return state.getStateDefinition().replace("-", "").matches(this.validStateRegex);
	}

	@Override
	public Node makeChild(Node n, Object legalAction, IState state) {
		Action action = (Action)legalAction;
		String newStateDefinition = state.getStateDefinition();
		newStateDefinition = act(action, newStateDefinition);
		
		float childStepCost = Math.abs(action.getShift());
		float parentCostTotal = state.getCostTotal();
		float totalCost = parentCostTotal + childStepCost;
		
		float heuristics = getHeuristicsWrongPosition(newStateDefinition); //TODO somente para A-star e IDA-star
		//deve-se somar o total com a heuristica?
		
		IState newState = new ReguaPuzzleState(legalAction, newStateDefinition, childStepCost, totalCost, heuristics);
		
		int depth = n != null? n.getDepth() + 1 : 1;
		
		return new Node(n, newState, depth);
	}
	
	private int getEmptyPosition(String stateDefinition){
		return stateDefinition.indexOf(EMPTY_SPACE);
	}
	
	/*
	 * returns true when the empty space can be moved (legal action)
	 */
	private boolean isLegal(Action action, String stateDefinition){
		if(action.getShift() == 0) return false; //ignore none
		
		int length = stateDefinition.length();
		int emptyPosition = getEmptyPosition(stateDefinition);
		
		int finalPosition = emptyPosition + action.getShift();
		return finalPosition >= 0 && finalPosition < length;
	}

	private String act(Action legalAction, String stateDefinition) {
		String newStateDefinition = "";
		int emptyPosition = getEmptyPosition(stateDefinition);
		
		int from = emptyPosition, 
			to = emptyPosition + legalAction.getShift(); //calculate shift
		
		newStateDefinition = Utils.moveChar(from, to, stateDefinition);
		
		return newStateDefinition;
	}	
	
	/*
	 * Returns the number of wrong positioned elements.
	 */
	public final float getHeuristicsWrongPosition(String stateDefinition){
		stateDefinition = stateDefinition.replace("-", ""); //ignore white
		
		int wrongPositionedCount = 0;
		for(int i = 0; i < this.N; i++){
			if(stateDefinition.charAt(i) == 'A') wrongPositionedCount++;
		}
		wrongPositionedCount = 2 * wrongPositionedCount;
		return wrongPositionedCount;
	}
	
	public float getHeuristicsDistance(String stateDefinition){
		return 0;
	}
	
}
