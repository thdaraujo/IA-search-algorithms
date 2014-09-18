
public class Node {
	private IState state;
	private Node parent;
	private int depth;
	
	public Node(Node parent, IState state, int depth){
		this.parent = parent;
		this.state = state;
		this.depth = depth;
	}
	
	public IState getState(){
		return this.state;
	}
	
	public Node getParent(){
		return this.parent;
	}

	public int getDepth() {
		return depth;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return this.state.hashCode();
	}
}
