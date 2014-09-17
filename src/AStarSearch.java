import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class AStarSearch extends Search {

	public AStarSearch(IPuzzleProblem problem){
		super(problem, "A* Search");
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
