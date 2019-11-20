public class Detour {
	private Train train;
	private Train divertedTrain;

	public Detour(Train train, Train divertedTrain) {
		this.train = train;
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

	public String toString() {
		if (train == null && divertedTrain == null) return "¬";
		else if (train != null && divertedTrain == null) return "_";
		else return "=";
	}
}