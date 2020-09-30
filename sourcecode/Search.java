import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;





public class Search {
	
	static int nodesExpanded;
	
	public static void main(String[] args) {
		
		//new grid with initial agent position. 
		
		grid startgrid = new grid(3,3);
		System.out.print("the starting grid config: " );
		startgrid.printgrid();
		
		Node startnode = new Node(startgrid,0,0);
		try {
		IDS(startnode);
		}
		catch(Exception e) {
			System.out.println("failure");
		}
	}
	
	
	
	public static int IDS(Node start) throws Exception {
		//iterate between depth 0 and 20. 
		
		int depth = 0;
		nodesExpanded = 0;
		ArrayList<Node> currentnodes;
		Node current;
		
		
		for (int limit = 1; limit < 20; limit++) {
			depth = limit;
			
	
		System.out.println("new limit: " + limit);
		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		
		
		
		while(!stack.isEmpty()) {
			
			current = stack.pop();
			//System.out.println("popped from stack: ");
			//current.config.printgrid();
			
			//goal test 
			if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
				System.out.println("GOAL state has been found");
				current.config.printgrid();
				System.out.println("the solution found: ");
				current.config.printgrid();
				System.out.println("Path Cost: " + current.getPathCost());
				System.out.println("");
				System.out.println("Depth: " + current.getDepth());
				System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
				System.out.println("the action sequence: " + current.getMoves());
				return nodesExpanded;
			}
			
			
			if (current.getDepth() <= limit) {
				System.out.println("the configuration to be expanded at depth: " + current.getDepth());
				current.config.printgrid();
				
				currentnodes = current.expand(current, false);
				Collections.shuffle(currentnodes);
				
				for (Node n : currentnodes)  {
					
					System.out.println("node depth: " + n.depth);
					n.config.printgrid();
					
					stack.push(n);
					nodesExpanded++;
					
				}
				
			}
			//otherwise we continue. 
			
		}
		}
		
		System.out.println("we could not find a solution for limit : " + depth);
		throw new Exception();
		
	}
	
	
	public static int DLS(Node start, int limit) throws Exception {
		
		//initialise
		Node current;
		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		nodesExpanded = 0;
		ArrayList<Node> expandnodes;
		
		
		while(!stack.isEmpty()) {
			
			current = stack.pop();
			//System.out.println("popped from stack: ");
			//current.config.printgrid();
			
			//goal test 
			if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
				System.out.println("GOAL state has been found");
				System.out.println("the solution found: ");
				current.config.printgrid();
				System.out.println("Path Cost: " + current.getPathCost());
				System.out.println("");
				System.out.println("Depth: " + current.getDepth());
				System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
				System.out.println("MOVES: " + current.moves);
				return nodesExpanded;
			}
			
			
			
				//expanding if we are not beyond limit. 
			try {
			if (current.getDepth() <= limit) {
				System.out.println("the node to be expanded: ");
				current.config.printgrid();
				
				expandnodes  = current.expand(current, false);
				Collections.shuffle(expandnodes);
				for (Node n : expandnodes) {
					System.out.println("expand nodes: ");
					n.config.printgrid();
					
					stack.push(n);
					nodesExpanded++;
				}
				
			}
			
			else {
				System.out.println("we are beyond the limit for this node");
			}
			}
			
			catch (OutOfMemoryError e) {
				System.out.println("the stack is full, now testing contents");
				//now just pop everything in the stack and test for goal
				while (!stack.isEmpty()) {
					current = stack.pop();
					if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
						System.out.println("goal state has been found");
						current.config.printgrid();
						System.out.println("Path Cost: " + current.getPathCost());
						System.out.println("");
						System.out.println("Depth: " + current.getDepth());
						System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
						return nodesExpanded;
					}
					
				}
				
		
				
			}
			}
			
		throw new Exception("Could not find solution for limit: " + limit);
		
		
	}
	
	
	
	
	
	
	public  static int DFS(Node start) throws Exception {
		//initialise 
		nodesExpanded= 0;
		Node current;
		
		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		//a structure to store the expanded nodes.
		ArrayList<Node> expandnodes;
	    
		while(!stack.isEmpty()) {
			current = stack.pop();
			
			System.out.println("                            \n)"
					+ "Stack size: " + stack.size() + "   \n " +
					"                                      ");
			
			//goal test 
			if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
				System.out.println("goal state has been found");
				current.config.printgrid();
				System.out.println("Path Cost: " + current.getPathCost());
				System.out.println("");
				System.out.println("Depth: " + current.getDepth());
				System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
				System.out.println("the moves: " + current.moves);
				return nodesExpanded;
			}
			
			//catch memory exception then just empty the stack. 
			try {
			System.out.println("the node to be expanded: ");
			current.config.printgrid();
			
			expandnodes  = current.expand(current, false);
			//ensure that the nodes are in random order -> call a function which essentially swaps entries randomly. 
			Collections.shuffle(expandnodes);
			for (Node n : expandnodes) {
				System.out.println("expand nodes: ");
				n.config.printgrid();
				
				stack.push(n);
				nodesExpanded++;
			}
			
			}
			catch (OutOfMemoryError e) {
				System.out.println("the stack is full, now testing contents");
				//now just pop everything in the stack and test for goal
				while (!stack.isEmpty()) {
					current = stack.pop();
					if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
						System.out.println("goal state has been found");
						current.config.printgrid();
						System.out.println("Path Cost: " + current.getPathCost());
						System.out.println("");
						System.out.println("Depth: " + current.getDepth());
						System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
						return nodesExpanded;
					}
					
				}
				System.out.println("No Solution Found");
				throw new Exception();
			}
			//expand the node, add to the queue. 
			
		}
		System.out.println("Search Failed");
		throw new Exception();
	}
	
	
	
	
	
	
	
	public  static Node BFS(Node start) throws Exception {
		//initialise 
		Node current;
		nodesExpanded = 0;
		
		ArrayList<Node> queue = new ArrayList<Node>();
		
		queue.add(start);
		
		while(!queue.isEmpty()) {
			
			current = queue.remove(0);
			System.out.println("                            \n)"
					+ "Queue size: " + queue.size() + "   \n " +
					"                                      ");
			
			
			//goal test 
			if (current.config.goalthree() && current.config.goalfour() && current.config.goalfive()) {
				System.out.println("goal state has been found");
				current.config.printgrid();
				System.out.println("Path Cost: " + current.getPathCost());
				System.out.println("");
				System.out.println("Depth: " + current.getDepth());
				System.out.println("TOTAL NODES EXPANDED: " + nodesExpanded);
				System.out.println("the moves: " + current.moves);
				return current;
			}
			System.out.println("the state to be expanded: "); 
			current.config.printgrid();
			for (Node n : current.expand(current,false)) {
				if (nodesExpanded < 100000) {
					
				System.out.println("expanded: ");
				n.config.printgrid();
				}
				System.out.println("DEPTH: " + current.getDepth());
				
				
				queue.add(n);
				
				nodesExpanded++;
				
			}
			//expand the node, add to the queue. 
			
		}
		System.out.println("Search Failed");
		throw new Exception();
	}
	
	
	
	
	
	public static int astar(Node start) throws Exception {
		//make a priority queue with a comparator. 
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>(11,new heuristic());
		nodeQueue.add(start);
		Node currentbest;
		nodesExpanded = 0;
		
		while(!nodeQueue.isEmpty()) {
			//remove the top of queue. 
			currentbest = nodeQueue.remove();
			System.out.println("the state to be expanded; ");
			currentbest.config.printgrid();
			
			//do a goal test
			if (currentbest.heuristic == 0 && currentbest.depth!= 0) {
				System.out.println("the found configuration: ");
				currentbest.config.printgrid();
				System.out.println("Goal state found at depth: " + currentbest.getDepth());
				System.out.println("NODES EXPANDED: " + nodesExpanded);
				
				System.out.println("the moves: " + currentbest.moves);
				return nodesExpanded;
				
			}
			
			//expand and add to queue. 
			for (Node n : currentbest.expand(currentbest,true)) {
				nodesExpanded++;
				nodeQueue.add(n);
				
				System.out.println("adding config to queue: ");
				n.config.printgrid();
				
				System.out.println("current highest priority config with totalcost  " + nodeQueue.peek().totalCost);
				nodeQueue.peek().config.printgrid();
			}
			
		}
		throw new Exception("failure");
	}
	
	
	
	
}
