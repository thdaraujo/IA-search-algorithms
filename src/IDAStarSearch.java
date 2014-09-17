import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class IDAStarSearch extends Search {

	public IDAStarSearch(IPuzzleProblem problem) {
		super(problem, "IDA*");
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
