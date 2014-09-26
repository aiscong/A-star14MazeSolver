
public class OwnHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState){
		int rows = 5;
		int col = 3;
		int cost = 0; //sum of tiles out of rows and tiles out of columns

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < col; j++){
				goalState.getPosition(state.tiles[i][j]);
				if(state.tiles[i][j] != -1){
					if(goalState.posR != i){
						cost++;
					}
					if(goalState.posC != j){
						cost++; //count as out of columns
					}
				}
			}
		}
		return cost;
	}
}