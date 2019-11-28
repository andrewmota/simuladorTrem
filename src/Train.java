public class Train {
	private int numPeople;
	private char direction;

	public Train() {
	}
	
	public Train(int numPeople, char direction) {
		this.numPeople = numPeople;
		this.direction = direction;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public String toString() {
		return "_";
	}
}