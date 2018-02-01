package Heuristic;

import RushHour.RushHourGame;
import RushHour.Vehicle;

public class MoveBlockingCarsHeuristic implements Heuristic {

	@Override
	public int evaluate(RushHourGame g) {
		int score = 0;
		int y = g.vList[0].y;
		
		for (int i=g.vList[0].x+g.vList[0].length; i<g.size; i++)
			if (g.board[i][y] != -1)
				score += 1+moving_away(g.vList[g.board[i][y]], g, i, y);
		
		return score;
	}
	
	public int moving_away(Vehicle v, RushHourGame g, int x, int y) {
		boolean before = true, after=true;
		if (v.orientation == 0) {
			if (v.length <= y)
				for (int i=0; i<v.length; i++) {
					if (g.board[x][y-i-1] != -1 && g.board[x][y-i-1] != v.id) {
						before = false;
						break;
					}
						
				}
			else 
				before = false;
			
			if (v.length + y < g.size) {
				for (int i=0; i<v.length; i++) {
					if (g.board[x][y+i+1] != -1 && g.board[x][y+i+1] != v.id) {
						after = false;
						break;
					}
						
				}
			}
			else
				after = false;
		}
		else {
			if (v.length <= x)
				for (int i=0; i<v.length; i++) {
					if (g.board[x-i-1][y] != -1 && g.board[x-i-1][y] != v.id) {
						before = false;
						break;
					}
						
				}
			else 
				before = false;
			
			if (v.length + x < g.size) {
				for (int i=0; i<v.length; i++) {
					if (g.board[x+i+1][y] != -1 && g.board[x+i+1][y] != v.id) {
						after = false;
						break;
					}
						
				}
			}
			else
				after = false;
		}
		
		if (after || before)
			return 0;
		else
			return 1;
	}
}
