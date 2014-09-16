import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class UniformCostSearch implements ISearch {

	/* (non-Javadoc)
	 * @see ISearch#getName()
	 */
	@Override
	public String getName() {
		return "Uniform Cost Search";
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
