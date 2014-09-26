
public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		return state.getDifferent(goalState);
	}
}

