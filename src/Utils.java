
public class Utils {
	public static String moveChar(int from, int to, String s){
		char[] arr = s.toCharArray();
		char aux = arr[from];
		arr[from] = arr[to];
		arr[to] = aux;
		
		return String.valueOf(arr);
	}
}
