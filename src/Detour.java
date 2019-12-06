public class Detour {
	private Train train;
	private Train divertedTrain;
	private boolean isWaiting;

	public Detour(Train train, Train divertedTrain) {
		this.train = train;
		this.divertedTrain = divertedTrain;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Train getDivertedTrain() {
		return divertedTrain;
	}

	public void setDivertedTrain(Train divertedTrain) {
		this.divertedTrain = divertedTrain;
	}

	public boolean isWaiting() {
		return isWaiting;
	}

	public void setIsWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public String toString() {
		if (train == null && divertedTrain == null) return "¬";
		else if (train != null && divertedTrain == null) return train.getDirection() == 'r' ? ">" : "<";
		else if (train != null && divertedTrain != null) return "=";
		else return "^";
	}
}