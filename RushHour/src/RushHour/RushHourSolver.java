package RushHour;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import Heuristic.Heuristic;

public class RushHourSolver {
	
	
	public RushHourGame game; 
	public int number_of_states_explored;
	
	public RushHourSolver(RushHourGame game) {
		this.game = game;
		
	}
		
	public LinkedList<Movement> bruteforce() {		
		/*
		 * 
		 */
		 
		
		/*
		 * Mysteriously, Hashmap of RushHourGame does not use RushHourGame.hashCode
		 * Thus we use directly the hashCode in the Hashmap
		 * 
		 * HashMap maps a state to the movement we made to reach it
		 * Doing so we can reconstruct the series of movements from starting state to any given state
		 */
		
		HashMap<Integer, Movement> visitedStates;					
		visitedStates = new HashMap<Integer, Movement>();		
		visitedStates.put(game.hashCode(), new Movement(game.vList[0], 0));		
		
		//Here is the queue storing the states we've just visited
		LinkedList<RushHourGame> queue = new LinkedList<RushHourGame>();
		queue.add(game.copy());
		
		
		LinkedList<Movement> next_moves;
		RushHourGame state;
		RushHourGame next_state = null;
		boolean found = false;
		int i=0;
		
		while( !queue.isEmpty() && !found) {
			state = queue.pop();
			
			next_moves = state.available_moves();
			for (Movement m: next_moves) {
				next_state = state.copy();
				next_state.move_vehicle(m);
				
				//Check if next_state has already been visited
				if (!visitedStates.containsKey(next_state.hashCode())) {
					visitedStates.put(next_state.hashCode(), m);
					//Check if red car is free
					if (next_state.vList[0].x+next_state.vList[0].length == next_state.size) {
						found = true;
						break;
					}
					queue.add(next_state);						
				}
			}		
		}
		
		
		//next_state contains the final state
		RushHourGame final_state = next_state;
		
		//Reconstruct solution
		LinkedList<Movement> solution = new LinkedList<Movement>();
		
		Movement m;
		do {
			m = visitedStates.get(final_state.hashCode());
			final_state.move_vehicle(m.reverse());
			solution.addFirst(m);
		} while(m.value != 0);
		
		//Remove the trivial movement 0:0
		solution.removeFirst();
			
		number_of_states_explored = visitedStates.size();
		return solution;					
	}
	
	
	
	
	public LinkedList<Movement> astar(Heuristic h){
		
		HashMap<Integer, Movement> visitedStates;					
		visitedStates = new HashMap<Integer, Movement>();		
		visitedStates.put(game.hashCode(), new Movement(game.vList[0], 0));		
		
		
		PriorityQueue<StateAstar> queue = new PriorityQueue<StateAstar>();
		//LinkedList<StateAstar> queue = new LinkedList<StateAstar>(); 
		queue.add(new StateAstar(game, 0, h));
		
		StateAstar state_infos;
		LinkedList<Movement> next_moves;
		RushHourGame state = game.copy();
		
		boolean found = false;
		int i=0;
		
		while( !queue.isEmpty() && !found) {
			//Pop the state_infos
			state_infos = queue.poll();
			//Reconstruct the game_board
			state.initFromHash(state_infos.hash);
			
			next_moves = state.available_moves();
			for (Movement m: next_moves) {
				state.move_vehicle(m);
				
				//Check if next_state has already been visited
				if (!visitedStates.containsKey(state.hashCode())) {
					visitedStates.put(state.hashCode(), m);
					if (state.vList[0].x+state.vList[0].length == state.size) {
						found = true;
						break;
					}
					
					queue.add(new StateAstar(state, state_infos.distance+1, h));
				}
				
				//Undo the move
				state.move_vehicle(m.reverse());
			}		
		}
		
		
		//state contains the final state
		RushHourGame final_state = state;
		
		//Reconstruct solution
		LinkedList<Movement> solution = new LinkedList<Movement>();
		
		Movement m;
		do {
			m = visitedStates.get(final_state.hashCode());
			final_state.move_vehicle(m.reverse());
			solution.addFirst(m);
		} while(m.value != 0);
		
		//Remove the trivial movement 0:0
		solution.removeFirst();
			
		number_of_states_explored = visitedStates.size();
		
		return solution;	
		
	}
	
}
	
	
	

class StateAstar implements Comparable<StateAstar>{
	
	public String hash;
	public int priority;
	public int distance;
	
	public StateAstar(RushHourGame game, int distance, Heuristic h) {
		hash = game.hashString();
		this.distance = distance;
		priority = distance + h.evaluate(game);
	}
	
	public int compareTo(StateAstar a) {
		return Integer.compare(priority, a.priority);
	}
}