import java.util.ArrayList;
import java.util.Arrays;

public class grid {

	//Agent : 2 
	
	//BLOCKS: 3, 4 , 5 
	
	//NOTE : the start and goal configurations have to manually be matched up to the data! 
	//start
	private int agentrow = 3;
	private int agentcol = 3;
	
	public int three_row = 3;
	public int three_col = 0;
	                             private  int[][] start = {{0,0,0,0},
			                                               {0,0,0,0},
			                                               {0,0,0,0},
		                                                   {3,4,5,2}};
    
	public int four_row = 3;
	public int four_col =1;
	public int five_row = 3;
	public int five_col = 2;
	
	
	//goal : NOTE IF THIS IS CHANGED, THE MANHATTEN DISTANCE NUMBERS HAVE TO BE ALTERED. 
	public int three_row_goal =1;
	public int three_col_goal = 1;
	public int four_row_goal = 2;                        public static final int[][] goal =  {{0,0,0,0},
			                                                                                  {0,3,0,0},
			                                                                                  {0,4,0,0},
	       	                                                                                  {0,5,0,2}};
    public int four_col_goal = 1;
    public int five_row_goal = 3;
    public int five_col_goal = 1;
	
 
	
	
	
	
	
	int[][] config;
	public int getrow() {
		return this.agentrow;
	}
	
	public int getcol() {
		return this.agentcol;
	}
	
	public int[][] getconfig() {
		return this.config;
	}
	
	
	public grid(int col, int row) {
		
		this.config = start;
		
		this.setAgentPos(col, row);
		
		
	
	}
	
	public boolean goalthree() {
		if (this.three_col == this.three_col_goal && this.three_row == this.three_row_goal) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean goalfour() {
		if (this.four_col == this.four_col_goal && this.four_row == this.four_row_goal) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean goalfive() {
		if (this.five_col == this.five_col_goal && this.five_row == this.five_row_goal) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//when we are creating grid, we need to state exactly the positions of all of the blocks and the agent. 
	public grid(int[][] config, int agentcol, int agentrow, int threecol, int threerow, int fourcol, int fourrow, int fivecol, int fiverow) {
		this.config = config;
		this.agentcol = agentcol;
		this.agentrow= agentrow;
		this.three_col = threecol;
		this.three_row = threerow;
		this.four_col = fourcol;
		this.four_row = fourrow;
		this.five_col = fivecol;
		this.five_row = fiverow;
	}
	
	public grid(int[][] config, int row, int col) {
		this.agentcol = col;
		this.agentrow = row;
		this.setConfig(config);
		this.setAgentPos(col, row);
	}
	
	public void setConfig(int[][] config) {
		this.config = config;
	}
	
	
//in coordinates where we want the agent to be -> Out: An altered configuration and agent coordinates. 
public  void setAgentPos(int col, int row) {
	
	int temp;
	if (!(row == this.agentrow && col == this.agentcol)) {
		//we reset 
		
		//what is currently at the new position
		temp = this.getconfig()[row][col];
		
		if (temp== 3) {
			three_row = getrow();
			three_col = getcol();
		}
		if (temp== 4) {
			four_row = getrow();
			four_col = getcol();
		}
		if (temp== 5) {
			five_row = getrow();
			five_col = getcol();
		}
		
		
		this.getconfig()[getrow()][getcol()] = temp;
		
		this.getconfig()[row][col] = 2;
		
		this.agentcol = col;
		this.agentrow = row;
	}
		
		
		
	}




	
	public  void setAgentPos(int col, int row,grid g) {
		
		if (!(col == g.getcol() && row == g.getrow())) {
		//whats currently there 
		int temp = g.getconfig()[row][col];
		System.out.println("the temp value:" + temp);
		
		if (temp== 3) {
			three_row = getrow();
			three_col = getcol();
		}
		if (temp== 4) {
			four_row = getrow();
			four_col = getcol();
		}
		if (temp== 5) {
			five_row = getrow();
			five_col = getcol();
		}
		
		
		//set position to whats in the new place. 
		g.getconfig()[g.getrow()][g.getcol()] = temp;
		
		g.printgrid();
		//set the new place to 2. 
		g.getconfig()[row][col] = 2;
		g.printgrid();
		//reset the 
		g.agentcol = col;
		g.agentrow = row;
		
		
		}
		
	}
	
	//function returns a complete copy of a grid. 
	public static grid copiedgrid(grid original) {
		
		grid newgrid = new grid(original.agentcol,original.agentrow);
		return newgrid;
	}
	
	
	public static int[][] getnewgrid(int[][] config) {
		int[][] newgrid = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newgrid[i][j] = config[i][j];
			}
		}
		return newgrid;
	}
	
	//function which takes a grid, makes a copy, moves positions and returns the new copy. 
	public int[][] getnewgrid() {
		int[][] newgrid = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newgrid[i][j] = this.getconfig()[i][j];
			}
		}
		
		return newgrid;
	}
	
	
	
	
	
	
	public void printgrid() {
		for (int[] x : this.config)
		{
		   for (int y : x)
		   {
		        System.out.print(y + " ");
		   }
		   System.out.println();
		}
		
		System.out.println(""
				+ "");
	}
	
	
	public static boolean goaltest(grid s) {
		while (true) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//we dont care where agent is, 
				if (s.config[i][j] != 2) {
				if (s.config[i][j] != grid.goal[i][j]) {
					return false;
				}
				}
			}
		}
		return true;
		}
	}
	
	//return a new grid in which agent has moved to the right. 
	public grid mr() throws IndexOutOfBoundsException{
		//make copy, calling the constructor which specifies blocks as well as agent position. 
		grid g = new grid(this.getnewgrid(this.config), this.agentcol,this.agentrow,this.three_col,this.three_row,this.four_col,this.four_row,this.five_col,this.five_row);
	
	
		if (!(g.agentcol +1 > 3)) {
			g.setAgentPos(agentcol +1 , agentrow );
			System.out.println("the updated grid: ");
			
			return g;
		}
		else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	//return a new grid in which agent has moved to the left 
		public grid ml() throws IndexOutOfBoundsException{
			//make copy
			grid g = new grid(this.getnewgrid(this.config), this.agentcol,this.agentrow,this.three_col,this.three_row,this.four_col,this.four_row,this.five_col,this.five_row);
			System.out.println("the copied grid: ");
			System.out.println("Col: " +g.getcol());
			System.out.println("ROW: " +g.getrow());
			
			
			
			if (!(g.agentcol -1 < 0)) {
				g.setAgentPos(agentcol -1, agentrow );
				System.out.println("new col : " + g.getcol());
				System.out.println("new row : " + g.getrow());
				
			
				return g;
			}
			else {
				throw new IndexOutOfBoundsException();
			}
			
		}
		
		
		//return a new grid in which agent has moved upwards.
		public grid mu() throws IndexOutOfBoundsException{
			//make copy
			grid g = new grid(this.getnewgrid(this.config), this.agentcol,this.agentrow,this.three_col,this.three_row,this.four_col,this.four_row,this.five_col,this.five_row);
			//System.out.println("the copied grid: ");
			//g.printgrid();
			
			if (!(g.agentrow -1 < 0)) {
				g.setAgentPos(agentcol, agentrow -1 );
				//System.out.println("the updated grid: ");
				//g.printgrid();
				return g;
			}
			else {
				throw new IndexOutOfBoundsException();
			}
			
		}
	
		
		//return a new grid in which agent has moved to the right. 
		public grid md() throws IndexOutOfBoundsException{
			//make copy
			grid g = new grid(this.getnewgrid(this.config), this.agentcol,this.agentrow,this.three_col,this.three_row,this.four_col,this.four_row,this.five_col,this.five_row);
			//System.out.println("the copied grid: ");
			//g.printgrid();
			
			//some code to remove the temporary problem of duplicate twos. 
			
			if (!(g.agentrow +1  > 3)) {
				g.setAgentPos(agentcol, agentrow +1 );
				//System.out.println("the updated grid: ");
				//g.printgrid();
				return g;
			}
			else {
				throw new IndexOutOfBoundsException();
			}
			
		}
		
	
		
		//returns an integer which is the cost of the state in terms of a heuristic function. 
		public int manhattenDist() {
			//set the heuristic value, which will be used in the total cost. Min cost is obviously best. 
			//this heuristic calculates how far all of the blocks are from their goal position (in moves), same for agent. 
		    
		    //NOTE: IF THE GOAL IS CHANGED, THEN THESE NUMBERS HAVE TO CHANGE AS WELL. 
			
		    int three_diff = Math.abs(three_row - three_row_goal) + Math.abs(three_col - this.three_col_goal);
		    int four_diff = Math.abs(four_row - this.four_row_goal) + Math.abs(four_col - this.four_col_goal);
		    int five_diff = Math.abs(five_row - this.five_row_goal) + Math.abs(five_col - this.five_col_goal);
			
		  return three_diff + four_diff + five_diff;
			
		}
		
		
	public static void main(String[] args) {
		
		//showing that the grid is working with 3 4 and 5 also. 
		
		
		
	}
	
	 
	
}
