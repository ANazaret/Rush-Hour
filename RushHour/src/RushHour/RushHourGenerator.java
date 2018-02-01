package RushHour;
import java.util.LinkedList;

public class RushHourGenerator {
	public int size;
	
	public RushHourGenerator(int size) {
		this.size = size;
	}
	
	
	public RushHourGame generate(int nCars, int sizeMaxCar, int shuffle) {
		int length, x, y, orientation, id;
		
		RushHourGame game = new RushHourGame(size, nCars);
		
		
		Vehicle redCar = new Vehicle(0, 0, 2, size/2-2, size/2);
		game.addCar(redCar);
				
		int failures = 0;
		int failuresMax = size*size*10;
		
		//Generate cars:
		while (game.nbvehicles < nCars && failures < failuresMax) {
			length = 2 + (int)(Math.random() * (sizeMaxCar - 1));
			orientation = (int)(Math.random() * 2);
			
			if (orientation == 0) {
				x = (int)(Math.random() * (size-length+1));
				y = (int)(Math.random() * size);
			}
				
			else {
				x = (int)(Math.random() * size);
				y = (int)(Math.random() * (size-length+1));
			}
				
			
			//Do not cross the exit line
			if (y <= size/2 && (y + length -1) >= size/2)
				continue;
			
			Vehicle v = new Vehicle(game.nbvehicles, orientation, length, x, y);
			
			if (game.checkCar(v)) {
				failures = 0;
				game.addCar(v);
			}
			else {
				failures += 1;
			}
				
		}
		
		if (failures == failuresMax) {
			System.out.println("Impossible to generate that much cars");
			return null;
		}
			
			
		
		//Shuffle the cars
		LinkedList<Movement> moves;
		Movement m;
		System.out.print("Shuffling");
		for (int i=0; i< shuffle; i++) {
			id = (int)(Math.random() * nCars);
			moves = game.availableMovesOneVehicle(game.vList[id]);
			
			if (moves.size() > 0) {
				m = moves.get((int)(Math.random()*moves.size()));
				game.move_vehicle(m);
				game.graph.repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
		return game;
	}
	
	
}
