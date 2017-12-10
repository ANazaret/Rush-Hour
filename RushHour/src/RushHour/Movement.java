package RushHour;

public class Movement {
	public int id;
	public int value;
	
	public Movement(Vehicule v, int value){
		this.id = v.id;
		this.value = value;
	}
	
	public Movement(int id, int value){
		this.id = id;
		this.value = value;
	}
	
	public Movement reverse() {
		return new Movement(this.id, -this.value);
	}
	
	
	public String toString() {
		return "Vehicule "+id+" : " + this.value;
	}
}
