import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public interface ISearch {
	List<Node> search();
	IPuzzleProblem getProblem();
	ISolution getSolution();
	String getSearchAlgorithm();
	boolean isMeta(IState state, IPuzzleProblem problem);
	public List<Node> pathToRoot(Node meta);
}
