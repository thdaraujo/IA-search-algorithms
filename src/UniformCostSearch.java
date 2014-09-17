import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class UniformCostSearch extends Search {

	public UniformCostSearch(IPuzzleProblem problem) {
		super(problem, "Uniform Cost Search");
	}
	
	@Override
	public List<IState> search(IState meta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		// TODO Auto-generated method stub
		return false;
	}

}
