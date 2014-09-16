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
	
	public static enum Action{
		jumpLeft(-2),  	//2 positions to the left
		moveLeft(-1),  	//1 position to the left
		jumpRight(2), 	//2 positions to the right
		moveRight(1), 	//1 position to the right
		none(0);	   	//do nothing
		
		private final int shift;
        private Action(final int shift) { this.shift = shift; }
        public int getValue() { return shift; }
	}
	
	private static char EMPTY_SPACE = '-';
	private ISearch searchAlgorithm;
	private int N;
	private String initial;
	private Node first;
	
	public ReguaPuzzleProblem(List<String> problemDefinition, ISearch searchAlgorithm) throws Exception{
		init(problemDefinition);
	
		this.searchAlgorithm = searchAlgorithm;
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
		this.first = makeChild(null, Action.none, initialState);
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
	 * @see IPuzzleMaker#searchAlgorithm()
	 */
	@Override
	public String searchAlgorithm() {
		if(this.searchAlgorithm != null){
			return this.searchAlgorithm.getName();
		}
		return "none";
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node makeChild(Node n, Object legalAction, String stateDefinition) {
		String newStateDefinition = stateDefinition;
		
		newStateDefinition = act((Action)legalAction, newStateDefinition);
		IState state = new StateReguaPuzzle(legalAction, newStateDefinition);
		
		int depth = n != null? n.getDepth() + 1 : 0;
		return new Node(n, state, depth);
	}
	
	private int getEmptyPosition(String stateDefinition){
		return stateDefinition.indexOf(EMPTY_SPACE);
	}
	
	/*
	 * returns true when the empty space can be moved (legal action)
	 */
	private boolean isLegal(Action action, String stateDefinition){
		int length = stateDefinition.length();
		int emptyPosition = getEmptyPosition(stateDefinition);
		
		switch (action) {
			case jumpLeft:
				return emptyPosition > 1;
			case moveLeft:
				return emptyPosition > 0;
			case jumpRight:
				return emptyPosition < length - 2;
			case moveRight:
				return emptyPosition < length - 1;
			case none:
				return true;
			default:
				return false;
		}
	}

	private String act(Action legalAction, String stateDefinition) {
		String newStateDefinition = "";
		int emptyPosition = getEmptyPosition(stateDefinition);
		
		int from = emptyPosition, 
			to = emptyPosition + legalAction.getValue(); //calculate shift
		
		newStateDefinition = Utils.moveChar(from, to, stateDefinition);
		
		return newStateDefinition;
	}	
}
