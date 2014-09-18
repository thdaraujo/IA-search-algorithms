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
	
	public static enum Action{
		moveLeft(-1),  	//1 position to the left
		jumpLeft(-2),  	//2 positions to the left
		moveRight(1), 	//1 position to the right
		jumpRight(2), 	//2 positions to the right
		none(0);	   	//do nothing
		
		private final int shift;
        private Action(final int shift) { this.shift = shift; }
        public int getShift() { return shift; }
	}
	
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
		IState initial = new ReguaPuzzleState(Action.none, initialState);
		this.first = makeChild(null, Action.none, initial);
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
		
		for(Action action : Action.values()){
			boolean isLegal = isLegal(action, state.getStateDefinition());
			if(isLegal) actionList.add(action);
		}
		
		return actionList;
	}

	@Override
	public boolean goalTest(IState state) {
		//TODO mudar!!
		return state.getStateDefinition().replace("-", "").matches(this.validStateRegex);
		
	}

	@Override
	public Node makeChild(Node n, Object legalAction, IState state) {
		String newStateDefinition = state.getStateDefinition();
		
		newStateDefinition = act((Action)legalAction, newStateDefinition);
		IState newState = new ReguaPuzzleState(legalAction, newStateDefinition);
		
		int depth = n != null? n.getDepth() + 1 : 0;
		return new Node(n, newState, depth);
	}
	
	private int getEmptyPosition(String stateDefinition){
		return stateDefinition.indexOf(EMPTY_SPACE);
	}
	
	/*
	 * returns true when the empty space can be moved (legal action)
	 */
	private boolean isLegal(Action action, String stateDefinition){
		if(action == Action.none) return false; //ignore none
		
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
}
