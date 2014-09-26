import java.util.ArrayList;

public class Board implements Comparable<Board>{
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	private int h; //heuristic cost
	private int g = 0; //from initial state cost
	
	public int posR = 0; //row position of a specific value in the board
	public int posC = 0; //col position of a specific value in the board
	
	public Board(int[][] cells)                 //Populate states
	{
		tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
	}
	//constructor that creates an empty board
	public Board(){
		tiles = new int[rows][columns];
	}
	//used for hamming
	//return the total number of different tiles from its goal state
	public int getDifferent(Board n){
		int num = 0;
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				if(this.tiles[i][j] != n.tiles[i][j]){
					num++;
				}
			}
		return num;
	}
	
	public boolean equals(Object x)         //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	
	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		ArrayList <Board> successors = new ArrayList<Board>();
		int temp[][] = new int [rows][columns]; 
		int openR = 0;
		int openC = 0;
		//copy the original state
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				temp[i][j] = tiles[i][j];
				if(tiles[i][j] == 0){
					openR = i;
					openC = j;
				}
			}
		}
		//move to the right
		if((openC+1) <= 2 && temp[openR][openC+1] != -1){
			temp[openR][openC] = temp[openR][openC+1];
			temp[openR][openC+1] = 0;
			successors.add(new Board(temp));
			temp[openR][openC] = 0;
			temp[openR][openC+1] = tiles[openR][openC+1];
		}
		//move down
		if((openR+1) <= 4 && temp[openR+1][openC] != -1){
			temp[openR][openC] = temp[openR+1][openC];
			temp[openR+1][openC] = 0;
			successors.add(new Board(temp));
			temp[openR][openC] = 0;
			temp[openR+1][openC] = tiles[openR+1][openC];
		}
		//move left
		if((openC-1) >= 0 && temp[openR][openC-1] != -1){
			temp[openR][openC] = temp[openR][openC-1];
			temp[openR][openC-1] = 0;
			successors.add(new Board(temp));
			temp[openR][openC] = 0;
			temp[openR][openC-1] = tiles[openR][openC-1];
		}
		//move up
		if((openR-1) >= 0 && temp[openR-1][openC] != -1){
			temp[openR][openC] = temp[openR-1][openC];
			temp[openR-1][openC] = 0;
			successors.add(new Board(temp));
			temp[openR][openC] = 0;
			temp[openR-1][openC] = tiles[openR-1][openC];
		}
		return successors;
	}
	
	//print the state
	public void print()
	{
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}
	//set the parent node
	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}
	//get the parent node
	public Board getParent()
	{
		return parent;
	}
	//set the heurstic function cost
	public void setHeu(int i){
		h = i;
	}
	//get the heurstic function cost
	public int getHeu(){
		return h;
	}
	//get the total cost
	public int getCost(){
		return (h + g);
	}
	//set the total cost
	public void setG(int i){
		g = i;
	}
	//get the total cost
	public int getG(){ 
		return g;
	}
	//override the compareTo so that when every board gets added
	//to the PriorityQueue, it is sequenced according to its total cost
	@Override
	public int compareTo(Board arg0) {
		// TODO Auto-generated method stub
		if(this.getCost() < arg0.getCost()){
			return -1;
		}
		if(this.getCost() > arg0.getCost()){
			return 1;
		}
		return 0;
	}
	//find the given value position on a board
	
	public void getPosition(int n){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(tiles[i][j] == n){
					posR = i;
					posC = j;
				}
			}
		}
	}
}
