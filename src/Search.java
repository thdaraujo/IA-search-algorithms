import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract class Search implements ISearch {

	private IPuzzleProblem problem;
	private String searchAlgorithmName;
	
	public Search(IPuzzleProblem problem, String searchAlgorithmName){
		this.problem = problem;
		this.searchAlgorithmName = searchAlgorithmName != null? searchAlgorithmName : "";
	}

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
	
	 /**
     * Returns the path from <tt>meta</tt> to <tt>initial state</tt>, or
     * <tt>null</tt> if no such path.
     * @param meta
     * @return the sequence of vertices on a shortest path, as an Iterable
     */
	@Override
    public List<IState> pathToRoot(Node meta) {
        List<IState> path = new Stack<IState>();
        path.add(meta.getState());
        Node x = meta.getParent();
        while(x != null){
        	path.add(x.getState());
        	x = x.getParent();
        }
        //stack should iterate in reverse already...
        Collections.reverse(path);
        return path;
    }
}
