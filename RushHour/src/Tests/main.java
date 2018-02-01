package Tests;

import Heuristic.BlockingCarsHeuristic;
import Heuristic.Heuristic;
import Heuristic.MoveBlockingCarsHeuristic;
import Heuristic.TrivialHeuristic;
import RushHour.Movement;
import RushHour.RushHourGame;
import RushHour.RushHourGenerator;
import RushHour.RushHourSolver;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
/**
 * 
 */

/**
 * @author achille
 *
 */
public class main {

	public static void main(String[] args) throws InterruptedException {
		tests();		
	}

	
	public static void demo() throws InterruptedException {
		RushHourGame game = new RushHourGame("games/hardest.txt");
		RushHourSolver solver = new RushHourSolver(game);		
		
		game.graph.animeMovements(solver.astar(new BlockingCarsHeuristic()),100);
	}
	
	public static void tests()  throws InterruptedException {
		
		long time;				
		
		Heuristic h;
		Heuristic[] test_heurstics = {new TrivialHeuristic(), new BlockingCarsHeuristic(), new MoveBlockingCarsHeuristic()};
		
		int[][][] res = new int[40][test_heurstics.length][3];
		
		for (int i=1; i<=40; i++) {
			RushHourGame game = new RushHourGame(String.format("games/GameP%02d.txt", i), false);
			RushHourSolver solver = new RushHourSolver(game);				
			LinkedList<Movement> solution = null;
			
			
			
			for ( int hi=0; hi<test_heurstics.length; hi++) {
				h = test_heurstics[hi];
				time = System.currentTimeMillis();
				for (int j=0; j<100; j++) 
					solver.astar(h);
				
				res[i-1][hi][0] = (int) ((System.currentTimeMillis() - time));
				res[i-1][hi][1] = solver.number_of_states_explored;
				res[i-1][hi][2] = solver.astar(h).size();
				
		
			}		
		}
		
		System.out.println(Arrays.deepToString(res));	

		
	}
	
	public static void generator() {
		RushHourGenerator generator = new RushHourGenerator(10);		
		RushHourGame game = generator.generate(30, 4, 500);
		
		RushHourSolver solver = new RushHourSolver(game);
		System.out.print("Solving");
		game.graph.animeMovements(solver.astar(new BlockingCarsHeuristic()));
	}
	
	
	

}

class Test {
	int time;
	int n_states;
	
	public Test(int time, int n_states) {
		this.time = time;
		this.n_states = n_states;
	}
}