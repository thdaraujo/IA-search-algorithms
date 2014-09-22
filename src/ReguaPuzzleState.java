
public class ReguaPuzzleState implements IState{

	private String stateDefinition;
	private Object action;
	private float costStep; //step cost until node
	private float costTotal; //cost until node
	
	public ReguaPuzzleState(Object action, String stateDefinition){
		this(action, stateDefinition, 0, 0);
	}
	
	public ReguaPuzzleState(Object action, String stateDefinition, float costStep, float costTotal) {
		this.action = action;
		this.stateDefinition = stateDefinition;
		this.costStep = costStep;
		this.costTotal= costTotal;
	}
	
	@Override
	public float getCostStep() {
		return this.costStep;
	}

	@Override
	public float getCostTotal() {
		return this.costTotal;
	}
	
	@Override
	public Object getAction() {
		return this.action;
	}

	@Override
	public String getStateDefinition() {
		return this.stateDefinition;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return this.stateDefinition.hashCode();
	}

	@Override
	public float getHeuristics() {
		return 0; //TODO
	}
}
