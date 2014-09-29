import java.util.List;


public class TravessiaPonteProblem implements IPuzzleProblem {

	private int N;
	private Node first;
	
	public TravessiaPonteProblem(List<String> problemDefinition) throws Exception{
		init(problemDefinition);
	}
	
	private void init(List<String> problemDefinition) {
		
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

	
	private List<Object> getLegalActions(IState state) {
		// TODO Auto-generated method stub
		return null;
	}

	private Node makeChild(Node n, Object action, IState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean goalTest(IState state) {
		// TODO Auto-generated method stub
		return false;
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
	public List<Node> getDescendants(Node n) {
		// TODO Auto-generated method stub
		return null;
	}

}
