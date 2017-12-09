import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RushHourGame {
	
	public int size;
	public int nbVehicules;
	public Vehicule[] vList;
	public int[][] board;
	
	public LinkedList<Movement> moves; 
	
	public RushHourGame(String filename){
		loadFromFile(filename);
		isValid();
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
					if(board[v.x+j][v.y] != -1)
						return false;
					else 
						board[v.x+j][v.y]=v.id;
				}
			}
			else {
				for (int j=0;j<v.length;j++){
					if(board[v.x][v.y+j] != -1)
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
		s = "Size : " + size + "\nNb vehicules: " + nbVehicules;
		
		return s;
	}
	
	/*
	public void update(Movement m){ //met à joue à la fois vList et board
		int id= m.vehicule.id;
		if(this.vList[id].orientation==0){ //mouv horizontal
			if(m.value>=0){
				for (int i=0;i<m.value;i++){ //on doit séparer deux cas car la boucle for ne comprend pas de 0 à x neg
					this.board[this.vList[id].x+i][this.vList[id].y]=-1;
					this.board[this.vList[id].x+this.vList[id].length+i][this.vList[id].y]=id;
				}
			}
			else{
				for (int i=1;i<-m.value;i++){
					this.board[this.vList[id].x-i][this.vList[id].y]=id;
					this.board[this.vList[id].x+this.vList[id].length-i][this.vList[id].y]=-1;
				}
			}
		}
		else{  //vertical
			if(m.value>=0){
				for (int i=0;i<m.value;i++){
					this.board[this.vList[id].x][this.vList[id].y+i]=-1;
					this.board[this.vList[id].x][this.vList[id].y+this.vList[id].length+i]=id;
				}
			}
			else{
				for (int i=1;i<-m.value;i++){
					this.board[this.vList[id].x][this.vList[id].y-i]=id;
					this.board[this.vList[id].x][this.vList[id].y+this.vList[id].length-i]=-1;
				}
			}	
		}
		this.vList[id].y+=m.value;
	}
	
	public LinkedList<Movement> available_moves(){
		LinkedList<Movement> ls= new LinkedList<Movement>();
		int p,n;
		for (int i=0;i<this.nbVehicules;i++){
			if(this.vList[i].orientation==0){
				p=0;
				while(this.board[this.vList[i].x+this.vList[i].length+p][this.vList[i].y]==-1 && this.vList[i].x+this.vList[i].length+p<this.size){
					p++;
				}
				n=0;
				while(this.board[this.vList[i].x-1-n][this.vList[i].y]==-1 && this.vList[i].x-1-n>0){
					n++;
				}
				for(int j=1;j<=p;j++){
					ls.add(new Movement(this.vList[i],j));
				}
				for(int j=1;j<=n;j++){
					ls.add(new Movement(this.vList[i],-j));
				}
			}
			else{
				p=0;
				while(this.board[this.vList[i].x][this.vList[i].y+this.vList[i].length+p]==-1 && this.vList[i].y+this.vList[i].length+p<this.size){
					p++;
				}
				n=0;
				while(this.board[this.vList[i].x][this.vList[i].y-1-n]==-1 && this.vList[i].y-1-n>0){
					n++;
				}
				for(int j=1;j<=p;j++){
					ls.add(new Movement(this.vList[i],j));
				}
				for(int j=1;j<=n;j++){
					ls.add(new Movement(this.vList[i],-j));
				}
			}
		}
		return ls;
	}
		*/
}
