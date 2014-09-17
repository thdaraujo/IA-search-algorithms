import java.util.List;


public class DepthFirstLimitedSearch extends Search {

	public DepthFirstLimitedSearch(IPuzzleProblem problem){
		super(problem, "Busca em Profundidade Limitada (BPL)");
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
