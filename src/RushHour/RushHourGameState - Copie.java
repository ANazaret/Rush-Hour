package RushHour;

public class RushHourGameState {
	public Vehicule[] vList;
	public int[][] board;
	
	
	
	
	@Override
    public int hashCode() {
        int hash = 0;
        
        for (Vehicule v: vList) {
        	hash *= board.length;
        	
        	if (v.orientation == 0)
        		hash += v.x;
        	else
        		hash += v.y;
        	
        }
        return hash; 
	}
	
	
	public RushHourGameState copy() {
		RushHourGameState cp = new RushHourGameState();
		
		cp.board = new int[board.length][];
		
		for (int i=0; i<board.length; i++)
			cp.board[i] = this.board[i].clone();
		
		cp.vList = new Vehicule[this.vList.length];
		
		Vehicule v;
		for (int i=0; i<vList.length; i++) {
			v = vList[i];				
			cp.vList[i] = new Vehicule(v.id, v.orientation,v.length, v.x, v.y);
		}
		
		return cp;
	}
}
