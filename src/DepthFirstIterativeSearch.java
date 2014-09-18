import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public class DepthFirstIterativeSearch extends Search {

	public DepthFirstIterativeSearch(IPuzzleProblem problem){
		super(problem, "Busca em Profundidade Iterativa (BPI)");
	}
	
	@Override
	public List<IState> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMeta(IState state, IPuzzleProblem problem) {
		return problem.goalTest(state);
	}

}
