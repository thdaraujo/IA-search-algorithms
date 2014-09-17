import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class BestFirstSearch extends Search {

	public BestFirstSearch(IPuzzleProblem problem){
		super(problem, "Best-First Search");
	}
	@Override
	public List<IState> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		// TODO Auto-generated method stub
		return false;
	}

}
