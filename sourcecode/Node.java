import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;



public class Node implements Cloneable{
	 
	grid config;
	Node parent = null;
	int pathCost;
	int totalCost;
	int heuristic = 0;
	
	int depth;
	ArrayList<Node> children;
	String moves = "$";
	String lastMove= "$";
        
	public Node(grid state,int cost, int depth) {
		
		this.config = state;
		this.children = new ArrayList<Node>();
		
		this.pathCost = cost;
		this.depth = depth;
		this.totalCost = pathCost + heuristic;
		
		//add empty stack symbol so search doesn't fail immediately. 

	}
	
	public void addMove(String s) {
		moves = moves.concat("-").concat(s);
		
	}
	
	public Node(grid state,int cost,int depth,Node parent,String move, String moves) {
		
		this.config = state;
		this.children = new ArrayList<Node>();
		
		this.pathCost = cost;
		this.depth = depth;
		this.parent = parent;
		this.setMoves(moves);
		this.addMove(move);
		this.lastMove = move;
		
		
	}
	
public Node(grid state,int cost,int depth,Node parent,String move, String moves,boolean val) {
		
		this.config = state;
		this.children = new ArrayList<Node>();
		
		this.pathCost = cost;
		this.depth = depth;
		this.parent = parent;
		this.setMoves(moves);
		this.addMove(move);
		this.lastMove = move;
		
		//the heuristic. we would also have to call a heuristic function to set the heuristic.
		this.heuristic = this.config.manhattenDist();
		this.totalCost = pathCost + heuristic;
	}
	
	
	public void setMoves(String moves) {
		this.moves = moves;
	}
	
	public void setStartNode() {
		depth = 0;
		pathCost = 0;
		parent = null;
		
	}
	
	public void setParent(Node n) {
		this.parent = n;
		
	}
	public String getMoves() {
		return this.moves;
	}
	
	//we want to take a node n, return a list of new expanded nodes, without interfering with the state n. 
	public ArrayList<Node> expand(Node n, boolean val) {
		ArrayList<Node> nodelist = new ArrayList<Node>();
		grid current = n.config;
		Node newnode;
		
		
		
		try {
			//don't move into the place where we have just come from. 
			
			//A* search expansion. 
			if (val == true) {
				if (n.lastMove !=  "left") {
					newnode = new Node(current.mr(),n.pathCost+1,n.depth +1 ,n,"right",this.getMoves(),val);
					
					nodelist.add(newnode);
					}
			}
			//uninformed search expansion. 
			else {
			if (n.lastMove !=  "left") {
			newnode = new Node(current.mr(),n.pathCost+1,n.depth +1 ,n,"right",this.getMoves());
			
			nodelist.add(newnode);
			}
			}
		}
		
		catch (IndexOutOfBoundsException e) {
			//we are out of bounds. 
		}
		
		try {
			if (val == true) {
				if (n.lastMove != "right") {
					newnode = new Node(current.ml(),n.pathCost+1,n.depth +1 ,n,"left",this.getMoves(),true);
					nodelist.add(newnode);
			}
				}
			
				else {
			if (n.lastMove != "right") {
			newnode = new Node(current.ml(),n.pathCost+1,n.depth +1 ,n,"left",this.getMoves());
			nodelist.add(newnode);
			}}
			}
		
		catch (IndexOutOfBoundsException e) {
			//we are out of bounds. 
		}
		
		
		
		
		try {
			
			if (val == true) {
				if (n.lastMove != "down") {
					newnode = new Node(current.mu(),n.pathCost+1,n.depth +1,n,"up",this.getMoves(),true);
					nodelist.add(newnode);
					}
			}
			else {
			if (n.lastMove != "down") {
			newnode = new Node(current.mu(),n.pathCost+1,n.depth +1,n,"up",this.getMoves());
			nodelist.add(newnode);
			}}
			
			
			
		}
		catch (IndexOutOfBoundsException e) {
			//we are out of bounds. 
		}
		
		
		
		
		
		try {
			
			if (val == true) {
				if (n.lastMove != "up") {
					newnode = new Node(current.md(),n.pathCost+1,n.depth +1 ,n,"down",this.getMoves(),true);
					nodelist.add(newnode);
					}
			}
			
			else {
			if (n.lastMove != "up") {
			newnode = new Node(current.md(),n.pathCost+1,n.depth +1 ,n,"down",this.getMoves());
			nodelist.add(newnode);
			}
			}
		}
		catch (IndexOutOfBoundsException e) {
			//we are out of bounds. 
		}
		
		return nodelist;
	}
	
		
		 
		
	
	public int getPathCost() {
		return this.pathCost;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	
	//this method takes a list and randomly shuffles the contents. This will be used for search methods like depth first where we will need to access the list in random order. 
	
	public ArrayList<Node> nodeshuffle(ArrayList<Node> array) {
		Random rand = new Random();
		int x1,x2;
		
		if (array.size() == 1) {
			return array;
		}
		if (array.size() == 2) {
			try {
				Collections.swap(array, 0, 1);
			}
			catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		//do length + 2 times 
		for (int i = 0; i < array.size() + 2; i++) {
			x1 = rand.nextInt(array.size() -1);
			x2 = rand.nextInt(array.size()-1);
			try {
			Collections.swap(array,x1,x2);
			}
			catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return array;
	}
	
	
	
	
	public static void main(String[] args) {
		//convincing evidence that the node expansion is working well. 
		//the nodes are not allowed to move to their parent node. The parent is not considered a neighbour,
		
		
		
		grid g = new grid(2,2);
		g.printgrid();
		
		/*Node n = new Node(g,0,0);
		for (Node node: n.expand(n,true)) {
			
			System.out.println("cost of node: " + node.totalCost);
			for (Node n1 : node.expand(node,true)) {
				System.out.println("cost of node: " + node.totalCost);
				n1.config.printgrid();
			}
		}
		*/
		
		Node n = new Node(g,0,2);
		
		for (Node node : n.expand(n, false)) {
			System.out.println(node.lastMove);
			
		}
		
		System.out.println("the shuffled array");
		for (Node node : n.nodeshuffle(n.expand(n, false))) {
			System.out.println(node.lastMove);
			
		}
		
		
}}
