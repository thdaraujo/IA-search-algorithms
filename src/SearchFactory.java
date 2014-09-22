
public final class SearchFactory {
	
	public static ISearch getSearch(String param, IPuzzleProblem problem, int limit) throws Exception{
		ISearch searchAlgorithm;
		
		switch (param.toUpperCase()) {
		case "BL":
			searchAlgorithm = new BreadthFirstSearch(problem);
			break;
		case "BP":
			searchAlgorithm = new DepthFirstSearch(problem);
			break;
		case "BPL":
			searchAlgorithm = new DepthFirstLimitedSearch(problem, limit);
			break;
		case "BPI":
			searchAlgorithm = new DepthFirstIterativeSearch(problem);
			break;
		case "BCU":
			searchAlgorithm = new UniformCostSearch(problem);
			break;
		case "A*":
			searchAlgorithm = new AStarSearch(problem);
			break;
		case "IDA*":
			searchAlgorithm = new IDAStarSearch(problem);
			break;
		case "RBFS":
			searchAlgorithm = new RecursiveBestFirstSearch(problem);
			break;
		default:
			throw new Exception("Search algorithm not found: [" + param + "]");
		}
		return searchAlgorithm;
	}
}
