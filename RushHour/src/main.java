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
		//RushHourGame game2 = new RushHourGame(game);
		
		game.move_vehicule(new Movement(game.vList[1], 2));
		
		//game2.graph = new RushHourGraphics(game2);
		
		
		
		
		game.graph.repaint();
		//game2.graph.repaint();
		
		//Thread.sleep(1000);
		
		//game.vList[0].moveNoCheck(2);
		//graph.repaint();
		
		RushHourGameSolver solver = new RushHourGameSolver(game);
		

		
		/*LinkedList<Movement> li = solver.backtrack();
		System.out.print(li.size());
		
		Movement m;
		while (!li.isEmpty()) {
			m = li.removeLast();
			m.reverse();
			game.move_vehicule(m);
			game.graph.repaint();
			Thread.sleep(10);
		}
		*/
		
		LinkedList<Movement> li = solver.bruteforce();
		
		for (Movement m : li) {
			game.move_vehicule(m);
			game.graph.repaint();
			Thread.sleep(500);
			
		}
	}

	

}
