package RushHour;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RushHourGame {
	
	public int size;
	public int nbVehicules;
	public Vehicule[] vList;
	public int[][] board;
	
	public RushHourGraphics graph;
	
	public RushHourGame(String filename){
		loadFromFile(filename);
		isValid();
		graph = new RushHourGraphics(this);
	}
	
	public RushHourGame(RushHourGame game) {
		this.board = new int[game.size][];
		
		for (int i=0; i<game.size; i++)
			this.board[i] = game.board[i].clone();
		
		this.vList = new Vehicule[game.nbVehicules];
		
		Vehicule v;
		for (int i=0; i<game.nbVehicules; i++) {
			v = game.vList[i];				
			this.vList[i] = new Vehicule(v.id, v.orientation,v.length, v.x, v.y);
		}
		
		this.size = game.size;
		this.nbVehicules = game.nbVehicules;
	}
	
	
	public boolean loadFromFile(String filename) {
		/*
		 * Initialize the RushHourGame from file
		 */
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
		
			String line;	
			
			//First Line : Size of the grid 
			this.size = Integer.valueOf(br.readLine());
			this.board= new int[size][size];
			
			//Fill in with -1 (no car)
			for (int i=0;i<size;i++)
				for (int j=0;j<size;j++)
					board[i][j]=-1; 
				
			
			//Second line : Nb of vehicules
			nbVehicules = Integer.valueOf(br.readLine());			
			vList = new Vehicule[nbVehicules];
			
			for (int i=0; i<nbVehicules; i++ ) {
				line = br.readLine();
				vList[i] = new Vehicule(line);				
			}
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	
	
	public boolean isValid(){
		/*
		 * Check that the RushHourGame is Valid AND fill in the board
		 * 
		 * - Size > 0
		 * - Each vehicule fits in the grid
		 * - No overlap
		 * 
		 */
		
		if(size==0)	return false;
		
		for(Vehicule v : vList){
			if(v.x<0 || v.y<0 || (v.orientation==0 && v.x+v.length>size) || (v.orientation==1 && v.y+v.length>size))	
				return false;
			
			if(v.orientation==0){
				for (int j=0;j<v.length;j++){
					if(board[v.x+j][v.y] != -1 && board[v.x+j][v.y] != v.id)
						return false;
					else 
						board[v.x+j][v.y]=v.id;
				}
			}
			else {
				for (int j=0;j<v.length;j++){
					if(board[v.x][v.y+j] != -1 && board[v.x][v.y+j] != v.id)
						return false;
					else
						board[v.x][v.y+j]=v.id;
				}
			}
		}
		return true;
	}
	
	
	public String toString() {
		String s;
		s = "Size : " + size + "\nNb vehicules: " + nbVehicules + "\nHashCode: "+this.hashCode() + "\nHashString: " + this.hashString();
		
		return s;
	}
	
	public LinkedList<Movement> available_moves(){
		/*
		 * Compute all possible moves from a game state
		 */
		
		LinkedList<Movement> ls= new LinkedList<Movement>();
		int p,n;
			
		for (Vehicule v : vList){
			if(v.orientation==0){
				p=0;
				while(v.x+v.length+p<size && board[v.x+v.length+p][v.y]==-1){
					p++;
					ls.add(new Movement(v,p));
				}
				n=0;
				while(v.x-1-n>=0 && board[v.x-1-n][v.y]==-1){
					n++;
					ls.add(new Movement(v,-n));
				}			
			}
			else{
				p=0;
				while(v.y+v.length+p<size && board[v.x][v.y+v.length+p]==-1){
					p++;
					ls.add(new Movement(v,p));
				}
				n=0;
				while(v.y-1-n>=0 && board[v.x][v.y-1-n]==-1){
					n++;
					ls.add(new Movement(v,-n));
				}
			}
		}
		return ls;
	}
	
	public void move_vehicule(Movement m){ 
		int id = m.id;
		Vehicule v = this.vList[id];
		
		//Move vehicule on the board
		if(v.orientation==0){ 
			//Horizontal move
			if(m.value>=0){
				//We erase on the left of the vehicule
				for (int i=0;i<Math.min(m.value, v.length);i++)
					this.board[v.x+i][v.y]=-1;
				
				//We write on the right
				for (int i=0;i<Math.min(m.value, v.length);i++)
					this.board[v.x+m.value+v.length-1-i][v.y] = id;
				
			}
			else
			{
				
				//Erase the right
				for (int i=0;i<Math.min(-m.value, v.length);i++)
					this.board[v.x+v.length-1-i][v.y]=-1;
				
				//We write on the left
				for (int i=0;i<Math.min(-m.value, v.length);i++)
					this.board[v.x+m.value+i][v.y] = id;
				
			}
		}
		else{  
			//Vertical move
			if(m.value>=0){
				//We erase on the top of the vehicule
				for (int i=0;i<Math.min(m.value, v.length);i++)
					this.board[v.x][v.y+i]=-1;
				
				//We write on the bottom
				for (int i=0;i<Math.min(m.value, v.length);i++)
					this.board[v.x][v.y+m.value+v.length-1-i] = id;
				
			}
			else
			{
				for (int i=0;i<Math.min(-m.value, v.length);i++)
					this.board[v.x][v.y+v.length-1-i]=-1;
				
				for (int i=0;i<Math.min(-m.value, v.length);i++)
					this.board[v.x][v.y+m.value+i] = id;
				
			}			
		}
		
		//Move vehicule coordinates
		v.move(m.value);
	}
	
	
	@Override
    public int hashCode() {
		/*
		 * Hashcode of the car position (since the car can only move along one axis, the hash is easy)
		 */
        int hash = 0;
        Vehicule v;
        
        for (int i=0; i<nbVehicules; i++) {
        	v = vList[i];
        	
        	hash *= size;
        	
        	if (v.orientation == 0)
        		hash += v.x;
        	else
        		hash += v.y;
        	
        }
        
        return hash; 
	}
	
	
	public String hashString() {
		String hash = "";
		for (Vehicule v: vList) {
			if (v.orientation == 0)
				hash+=String.valueOf(v.x)+"#";
        	else
        		hash+=String.valueOf(v.y)+"#";
		}
		
		return hash;
	}
	
	public void init_from_hash(String hash) {
		//To speed up this function, we do not check if the board is valid
		int i, j, position;
		Vehicule v;
		//Reset the board
		for(i=0; i<size; i++)
			for(j=0; j<size; j++)
				board[i][j] = -1;
		
		String[] positions = hash.split("#");
		
		//Read vehicules positions from the hash
		for(i=0; i<nbVehicules; i++) {
			position = Integer.valueOf(positions[i]);
						
			v = vList[i];
			
			if (v.orientation == 0) {
				v.x = position;
				for (j=0;j<v.length;j++)
					board[v.x+j][v.y]=v.id;
			}				
			else {
				v.y = position;
				for (j=0;j<v.length;j++)
					board[v.x][v.y+j]=v.id;
			}			
		}				
	}
	
	
	public RushHourGame copy() {
		/*
		 * Return a deepcopy of the class
		 */
		RushHourGame cp = new RushHourGame(this);
		return cp;
	}
}
