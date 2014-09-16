import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 * Depth-Frist search on a graph
 *
 */
public class DepthFirstSearch implements ISearch {

	/* (non-Javadoc)
	 * @see ISearch#getName()
	 */
	@Override
	public String getName() {
		return "Depth-First Search (DFS)";
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
