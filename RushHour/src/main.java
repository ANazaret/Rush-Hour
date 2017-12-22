import java.util.LinkedList;

import Heuristic.BlockingCarsHeuristic;
import Heuristic.MoveBlockingCarsHeuristic;
import Heuristic.TrivialHeuristic;
import RushHour.Movement;
import RushHour.RushHourGame;
import RushHour.RushHourGameSolver;

/**
 * 
 */

/**
 * @author achille
 *
 */
public class main {

	public static void main(String[] args) throws InterruptedException {
		
		RushHourGame game = new RushHourGame("src/RushHour2.txt");
		
		System.out.println(game);		
		
		game.init_from_hash(game.hashString());
		
		
		
		RushHourGameSolver solver = new RushHourGameSolver(game);		

		LinkedList<Movement> li = null;
	
		long time;
		
		time = System.currentTimeMillis();			
		for (int i=0; i<100; i++)
			li = solver.bruteforce();
		
		System.out.println("DFS time :" +(System.currentTimeMillis() - time) +" States visited : " +solver.number_of_states_explored);
		
		time = System.currentTimeMillis();		
		for (int i=0; i<100; i++)
			li = solver.astar(new TrivialHeuristic());		
		System.out.println("Astar Trivial time :" +(System.currentTimeMillis() - time) +" States visited : " +solver.number_of_states_explored);
		
		
		time = System.currentTimeMillis();		
		for (int i=0; i<100; i++)
			li = solver.astar(new BlockingCarsHeuristic());		
		System.out.println("Astar Blocking time :" +(System.currentTimeMillis() - time) +" States visited : " +solver.number_of_states_explored);
		
		
		time = System.currentTimeMillis();		
		for (int i=0; i<100; i++)
			li = solver.astar(new MoveBlockingCarsHeuristic());		
		System.out.println("Astar Blocking Advanced : " +(System.currentTimeMillis() - time) +" States visited : " +solver.number_of_states_explored);
		
		System.out.print(li.size());
		
		time = System.currentTimeMillis();			
		for (int i=0; i<100; i++)
			li = solver.bruteforce();
		
		System.out.println("DFS time :" +(System.currentTimeMillis() - time) +" States visited : " +solver.number_of_states_explored);
		
		
		for (Movement m : li) {
			Thread.sleep(300);
			game.move_vehicule(m);
			game.graph.repaint();		
			
		}
		
		
		
	}

	

}
