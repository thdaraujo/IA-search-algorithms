
public interface IState {
	public String toString();
	public float G();
	public float H();
	public float F();
	public Object getAction();
	public String getStateDefinition();
}
