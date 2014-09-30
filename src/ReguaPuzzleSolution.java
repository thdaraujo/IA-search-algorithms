import java.util.LinkedList;
import java.util.List;


public class ReguaPuzzleSolution implements ISolution {

	private int explored;
	private int generated;
	private int metaDepth;
	private float solutionCost;
	private String searchAlgorithm;
	private List<Node> path;
	
	public ReguaPuzzleSolution(){
		this("");
	}
	
	public ReguaPuzzleSolution(String searchAlgorithm){
		this.path = new LinkedList<Node>();
		this.searchAlgorithm = searchAlgorithm;
	}
	
	@Override
	public void printSolution() {
		System.out.println(this.searchAlgorithm);
		System.out.println("caminho da solução:");
		this.printPath();
		System.out.printf("%-30s%-12s\n", "número de nós explorados:", this.explored);
		System.out.printf("%-30s%-12s\n", "número de nós gerados:", this.generated);
		System.out.printf("%-30s%-12s\n", "profundidade da meta:", this.metaDepth);
		System.out.printf("%-30s%.3f\n", "custo da solução:", this.solutionCost);
		System.out.printf("%-30s%.3f\n", "fator de ramificação médio:", this.getBranchingFactor());
	}

	private float getBranchingFactor() {
		int expl = this.explored != 0? this.explored : 1;
		return this.generated / expl;
	}

	@Override
	public void printPath() {
		for(Node n : this.path){
			IState state = n.getState();
			System.out.println(state.getStateDefinition());
		}
	}

	@Override
	public void setPath(List<Node> nodes) {
		this.path = nodes;
	}
	
	public void addToExplored(int addition){
		this.generated += addition;
	}
	
	public void addToGenerated(int addition){
		this.generated += addition;
	}
	
	public void saveMetaCosts(Node meta){
		if(meta == null){
			System.out.println("meta is null!");
			return;
		}
		this.metaDepth = meta.getDepth();
		this.solutionCost = meta.getCostTotal();
		
		System.out.println("meta found: " + meta.getState().getStateDefinition());
	}

	@Override
	public void setAlgorithmName(String searchAlgorithmName) {
		this.searchAlgorithm = searchAlgorithmName;
	}

	@Override
	public boolean solved() {
		return this.path != null && this.path.size() > 0;
	}
}
