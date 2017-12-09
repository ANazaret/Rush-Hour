import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class RushHourGameSolver {
	
	
	public RushHourGame game; 
	
	
	public RushHourGameSolver(RushHourGame game) {
		this.game = game;
		
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
		LinkedList<Movement> liste = game.available_moves();
		
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
		 
		HashMap<Integer, Movement> visitedStates;		
		
		
		visitedStates = new HashMap<Integer, Movement>();		
		visitedStates.put(game.hashCode(), new Movement(game.vList[0], 0));		
		
		
		LinkedList<RushHourGame> queue = new LinkedList<RushHourGame>();
		queue.add(game.copy());
		
		
		LinkedList<Movement> moves;
		RushHourGame state;
		RushHourGame tmp = null;
		boolean found = false;
		int i=0;
		
		while( !queue.isEmpty() && !found) {
			state = queue.pop();
			
			moves = state.available_moves();
			for (Movement m: moves) {
				tmp = state.copy();
				tmp.move_vehicule(m);

				if (!visitedStates.containsKey(tmp.hashCode())) {
					//Unvisited state
					visitedStates.put(tmp.hashCode(), m);
					if (tmp.vList[0].x+tmp.vList[0].length == tmp.size) {
						found = true;
						break;
					}
					queue.add(tmp);
						
				}
			}		
		}
		
		//tmp contains the final state
		
		//Reconstruct solution
		LinkedList<Movement> solution = new LinkedList<Movement>();
		
		Movement m;
		do {
			m = visitedStates.get(tmp.hashCode());
			m.reverse();
			tmp.move_vehicule(m);
			m.reverse();
			solution.addFirst(m);
		} while(m.value != 0);
		
		solution.removeFirst();
			
		return solution;				
		
	}
}
