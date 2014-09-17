import java.util.List;
import java.util.Stack;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public interface ISearch {
	List<IState> search();
	IPuzzleProblem getProblem();
	String getSearchAlgorithm();
	boolean isMeta(IState state, IPuzzleProblem problem);
	public List<IState> pathToRoot(Node meta);
}
