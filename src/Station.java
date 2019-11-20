public class Station {
	private int id;
	private Train train;
	private int numGoingDown;
	private int numGoingUp;
	private boolean isWaiting;
	
	public Station(int id, Train train) {
		this.id = id;
		this.train = train;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
	
	public int getNumGoingDown() {
		return numGoingDown;
	}

	public void setNumGoingDown(int numGoingDown) {
		this.numGoingDown = numGoingDown;
	}

	public int getNumGoingUp() {
		return numGoingUp;
	}

	public void setNumGoingUp(int numGoingUp) {
		this.numGoingUp = numGoingUp;
	}

	public boolean isWaiting() {
		return isWaiting;
	}

	public void setIsWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public String toString() {
		return (train != null ? "|^" : "|") + id + (train != null ? "^|" : "|");
	}
}