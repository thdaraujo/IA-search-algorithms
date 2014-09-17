import java.util.List;


public abstract class Search implements ISearch {

	private IPuzzleProblem problem;
	private String searchAlgorithmName;
	
	public Search(IPuzzleProblem problem, String searchAlgorithmName){
		this.problem = problem;
		this.searchAlgorithmName = searchAlgorithmName != null? searchAlgorithmName : "";
	}

	@Override
	public abstract List<IState> search(IState meta);

	@Override
	public IPuzzleProblem getProblem() {
		return this.problem;
	}

	@Override
	public String getSearchAlgorithm() {
		return this.searchAlgorithmName;
	}

	@Override
	public abstract boolean isMeta(IState state, IPuzzleProblem problem);
}
