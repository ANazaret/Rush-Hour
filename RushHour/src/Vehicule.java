
public class Vehicule {
	public int id;
	public int orientation; //0 is horizontal, 1 is vertical
	public int length;
	public int x;
	public int y;
	
	public Vehicule(int id, int orientation, int length, int x, int y) {
		this.id = id;
		this.orientation = orientation;
		this.length = length;
		this.x = x;
		this.y = y;
		
	}
	
	public Vehicule(String s) {
		String[] split = s.split(" ");
		
		this.id = Integer.valueOf(split[0]) -1;
		this.orientation = (split[1].charAt(0) == 'h') ? 0: 1;
		this.length = Integer.valueOf(split[2]);
		this.x = Integer.valueOf(split[3])-1;
		this.y = Integer.valueOf(split[4])-1;
		
	}
	
	public void move(int nb_cells) {
		//Move the car
		
		if (orientation == 0)
			x += nb_cells;
		else
			y += nb_cells;
		
	}
	
	
}
