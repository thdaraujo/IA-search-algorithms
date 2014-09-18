
public class ReguaPuzzleState implements IState{

	private String stateDefinition;
	private Object action;
	private float g; //cost until node
	private float h; //heuristics - cost until goal.
	
	public ReguaPuzzleState(Object action, String stateDefinition){
		this(action, stateDefinition, 0, 0);
	}
	
	public ReguaPuzzleState(Object action, String stateDefinition, float g, float h) {
		this.action = action;
		this.stateDefinition = stateDefinition;
		this.g = g;
		this.h = h;
	}

	@Override
	public float G() {
		return this.g;
	}

	@Override
	public float H() {
		return this.h;
	}
	
	@Override
	public float F()
	{
		return this.G() + this.H();
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
		return this.stateDefinition.hashCode(); //TODO verificar
	}
}
