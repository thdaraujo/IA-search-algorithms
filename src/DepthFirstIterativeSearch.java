import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class DepthFirstIterativeSearch implements ISearch {

	/* (non-Javadoc)
	 * @see ISearch#getName()
	 */
	@Override
	public String getName() {
		return "Iterative Depth-First Search";
	}

	/* (non-Javadoc)
	 * @see ISearch#search(IState)
	 */
	@Override
	public List<IState> search(IState meta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state) {
		// TODO Auto-generated method stub
		return false;
	}

}
