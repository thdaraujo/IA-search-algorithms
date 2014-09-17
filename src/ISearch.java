import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public interface ISearch {
	List<IState> search(IState meta);
	IPuzzleProblem getProblem();
	String getSearchAlgorithm();
	boolean isMeta(IState state, IPuzzleProblem problem);
}
