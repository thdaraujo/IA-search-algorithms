import java.util.List;


public interface ISolution {
	public void printSolution();
	public void printPath();
	public void setPath(List<Node> nodes);
	public void setAlgorithmName(String searchAlgorithmName);
	public boolean solved();
}
