import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
public class AStar {

	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;
	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
		//int counter = 0;
		PriorityQueue<Board> Frontier = new PriorityQueue<Board>();
		ArrayList<Board> Explored = new ArrayList<Board>();
		Frontier.add(initialState);
		/* Declare and initialize Frontier and Explored data structures */ 
		/* Put start node in Fringe list Frontier */
		while (!Frontier.isEmpty())
		{	
			Board n = Frontier.remove();
			Explored.add(n);
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			if (n.equals(goalState)){
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				ArrayList<Board> path = new ArrayList<Board>();
				//reversively add the path nodes to an arraylist
				while(n.getParent() != null){
					path.add(0,n);
					n = n.getParent();
				}
				path.add(0,n);
				for(int i = 0; i < path.size(); i++){
					path.get(i).print();
					System.out.println();
				}
				System.out.println();
				System.out.println(path.size() - 1);
				System.out.println(Explored.size());
				return;
			}

			ArrayList<Board> successors = n.getSuccessors();
			for (int i = 0 ;i < successors.size(); i++){
				boolean containSuccessor = false; //indicate whether a structure contains the node
				Board n1 = successors.get(i);
				n1.setParent(n);//set child parent
				n1.setHeu(heuristic.getCost(n1, goalState)); //get and set the child heuristic cost
				n1.setG(n.getG() + 1); //set the child path cost
				
				         
				//if n1 is in frontier
				Board temp = new Board();
				Iterator<Board> it1 = Frontier.iterator();
				while(it1.hasNext()){
					temp = it1.next();
					if(temp.equals(n1)){
						containSuccessor = true;
						// if g(n1) is lower for the newly generated n1
				          //Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n	
						if(n1.getG() < temp.getG()){
							temp.setG(n1.getG()); //update path cost
							temp.setParent(n);// update parent
							break;
						}
					}
				}
				
				// if n1 is in Explored list
				if(!containSuccessor){	
					for(int j = 0; j < Explored.size(); j++){
						if(Explored.get(j).equals(n1)){
							containSuccessor = true;
							temp = Explored.get(j);
							 //Move n1 from Explored to Frontier list
							if(n1.getG() < temp.getG()){
								Explored.remove(j);
								Frontier.add(temp); //move n1 from explored to frontier
								break;
							}
						}
					}
				}
				//if n1 is not already in either Frontier or Explored
			      //Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				if(!containSuccessor){
					Frontier.add(n1);
				}
			}
		}
		System.out.println("No Solution");
	}
}
