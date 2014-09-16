import java.util.List;

/**
 * 
 */

/**
 * @author thiagoaraujo
 *
 */
public interface ISearch {
	String getName();
	List<IState> search(IState meta);
	boolean isMeta(IState state);
}
