import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class heuristic implements Comparator<Node>{

	
	//minimum cost has highest priority. 
	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return ( (o1.totalCost - o2.totalCost));
	}

	
	
	

}
