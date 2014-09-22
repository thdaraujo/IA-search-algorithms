import java.util.Date;

import com.sun.jmx.snmp.Timestamp;


public class Node {
	private IState state;
	private Node parent;
	private int depth;
	private long timestamp;
	
	public Node(Node parent, IState state, int depth){
		this.parent = parent;
		this.state = state;
		this.depth = depth;
		this.timestamp = System.nanoTime();
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
	
	public float getCostStep(){
		return this.state.getCostStep();
	}
	
	public float getCostTotal(){
		return this.state.getCostTotal();
	}
	
	public long getTimeStamp(){
		return this.timestamp;
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
