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
		
		RushHourGame game = new RushHourGame("src/RushHour1.txt");
		System.out.println(game.isValid());
		
		System.out.println(game);
		
		
		
		//Thread.sleep(1000);
		
		//game.vList[0].moveNoCheck(2);
		//graph.repaint();
		
		RushHourGameSolver solver = new RushHourGameSolver(game);
		

		
		LinkedList<Movement> li = solver.backtrack();
		System.out.print(solver.moves.size());
		
		Movement m;
		while (!li.isEmpty()) {
			m = li.removeLast();
			
			game.move_vehicule(new Movement(m.vehicule, -m.value));
			game.graph.repaint();
			Thread.sleep(10);
		}
	}

	

}
