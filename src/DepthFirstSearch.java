import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 * Depth-First search on a graph
 *
 */
public class DepthFirstSearch extends Search {

	public DepthFirstSearch(IPuzzleProblem problem) {
		super(problem, "Busca em Profundidade (BP)");
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
