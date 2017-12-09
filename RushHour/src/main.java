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
		RushHourInterface graph = new RushHourInterface(game);
		
		Thread.sleep(1000);
		
		game.vList[0].moveNoCheck(2);
		graph.repaint();
	}

	

}
