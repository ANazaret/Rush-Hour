package Heuristic;

import RushHour.RushHourGame;

public class BlockingCarsHeuristic implements Heuristic{

	@Override
	public int evaluate(RushHourGame g) {
		int score = 0;
		int y = g.vList[0].y;
		
		for (int i=g.vList[0].x+g.vList[0].length; i<g.size; i++)
			if (g.board[i][y] != -1)
				score += 1;
		
		return score;
	}

}
