public class ManhattanHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState){
		int rows = 5;
		int col = 3;
		int cost = 0; //sum of all tile difference
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				if(state.tiles[i][j] != -1){
					goalState.getPosition(state.tiles[i][j]);
					cost = Math.abs(goalState.posR - i) + Math.abs(goalState.posC - j) + cost;
				}
			}
		}
		return cost;
	}
}
