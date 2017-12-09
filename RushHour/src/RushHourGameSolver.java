import java.util.HashSet;
import java.util.LinkedList;

public class RushHourGameSolver {
	
	
	public RushHourGame game; 
	
	
	public RushHourGameSolver(RushHourGame game) {
		this.game = game;
		
	}
	

	
	public LinkedList<Movement> available_moves(){
		/*
		 * Compute all possible moves from a game state
		 */
		
		LinkedList<Movement> ls= new LinkedList<Movement>();
		int p,n;
			
		for (Vehicule v : game.vList){
			if(v.orientation==0){
				p=0;
				while(v.x+v.length+p<game.size && game.board[v.x+v.length+p][v.y]==-1){
					p++;
					ls.add(new Movement(v,p));
				}
				n=0;
				while(v.x-1-n>=0 && game.board[v.x-1-n][v.y]==-1){
					n++;
					ls.add(new Movement(v,-n));
				}			
			}
			else{
				p=0;
				while(v.y+v.length+p<game.size && game.board[v.x][v.y+v.length+p]==-1){
					p++;
					ls.add(new Movement(v,p));
				}
				n=0;
				while(v.y-1-n>=0 && game.board[v.x][v.y-1-n]==-1){
					n++;
					ls.add(new Movement(v,-n));
				}
			}
		}
		return ls;
	}
	
	
	
	public LinkedList<Movement> backtrack() {		
		LinkedList<Movement> moves; 
		HashSet<RushHourGame> visitedStates;		
		
		moves = new LinkedList<Movement>();
		visitedStates = new HashSet<RushHourGame>();		
		visitedStates.add(game);				
		
		backtrack_rec(moves, visitedStates);
		
		return moves;				
		
	}
	
	public boolean backtrack_rec(LinkedList<Movement> moves, HashSet<RushHourGame> visitedStates){
		LinkedList<Movement> liste = available_moves();
		
		if (game.vList[0].x + game.vList[0].length == game.size)
			return true;
		
		for (Movement m : liste) {
			game.move_vehicule(m);
			if (visitedStates.contains(game)) {
				m.value *= -1;
				game.move_vehicule(m);
			}
			else
			{
				visitedStates.add(game);
				moves.add(m);
				game.graph.repaint();
				
				if (backtrack_rec(moves, visitedStates))
					return true;
				
				m.value *= -1;
				game.move_vehicule(m);
				moves.removeLast();
				game.graph.repaint();
				
			}
			
			
			
		}
		
		return false;
		
	}
	
	
	
	public LinkedList<Movement> bruteforce() {		
		LinkedList<Movement> moves; 
		HashSet<RushHourGame> visitedStates;		
		
		
		visitedStates = new HashSet<RushHourGame>();		
		visitedStates.add(game);				
		
		LinkedList<RushHourGame> queue = new LinkedList<RushHourGame>();
		queue.add(game);
		
		RushHourGame state;
		while( !queue.isEmpty()) {
			state = queue.pop();
			
		}
		
		
		return null;				
		
	}
}
