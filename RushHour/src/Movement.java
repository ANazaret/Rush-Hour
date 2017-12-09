
public class Movement {
	public int id;
	public int value;
	
	public Movement(Vehicule v, int value){
		this.id = v.id;
		this.value = value;
	}
	
	public void reverse() {
		this.value*= -1;
	}
	
	
	public String toString() {
		return "Vehicule "+id+" : " + this.value;
	}
}
