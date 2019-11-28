import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class Simulator {
	private SinglyLinkedList<Object> list;
	private int numStations;
	private Calendar dateTime;
	int kmStations = 6;
	int cont = 0;

	public Simulator() {
		this.list = new SinglyLinkedList<Object>();
		//this.numStations = random(10, 30);
		this.numStations = 4;
		this.dateTime = Calendar.getInstance();
		
		dateTime.set(Calendar.HOUR_OF_DAY, 8);
		dateTime.set(Calendar.MINUTE, 0);
		dateTime.set(Calendar.SECOND, 0);
		dateTime.set(Calendar.MILLISECOND, 0);
	}

	public SinglyLinkedList<Object> getList() {
		return list;
	}

	public void setList(SinglyLinkedList	<Object> list) {
		this.list = list;
	}

	public int getNumStations() {
		return numStations;
	}

	public void setNumStations(int numStations) {
		this.numStations = numStations;
	}

	public Calendar getDate() {
		return dateTime;
	}

	public void setDate(Calendar dateTime) {
		this.dateTime = dateTime;
	}
	
	public int random(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
	
	public void addTime(int minute) {
		dateTime.add(Calendar.MINUTE, minute);
	}

	public void fillRails() {
		list.insertFirst(new Point("A"));		
		list.insertLast(new Rail());
		for (int i = 1; i <= numStations; i++) {
			for (int j = 1; j <= kmStations - 2; j++) list.insertLast(new Rail());
		
			list.insertLast(new Detour(null, null));
			list.insertLast(new Station(i, null));
			list.insertLast(new Detour(null, null));
			
			if (i == numStations)
				for (int j = 1; j <= kmStations - 2; j++) list.insertLast(new Rail());
		}
		list.insertLast(new Rail());
		list.insertLast(new Point("B"));
	}

	public void moveTrains() {		
		HashMap<Integer, Object> toAdd = new HashMap<Integer, Object>();
		
		// Percorre trilho de trás para frente, movimentando trens com direção L
		for (int pos = list.numElements() - 1; pos >= 0; pos--) {
			Object obj = list.get(pos);
			
			if (obj.getClass().getName().equals("Station"))
				moveStations((Station) obj, pos, toAdd, 'l');
			else if (obj.getClass().getName().equals("Train"))
				moveTrain((Train) obj, pos, toAdd, 'l');
			else if (obj.getClass().getName().equals("Detour"))
				moveDetour((Detour) obj, pos, toAdd, 'l');
		}
		addToList(toAdd);
		cont = 0;
		
		// Percorre trilho de trás para frente, movimentando trens com direção R
		for (int pos = 0; pos < list.numElements(); pos++) {
			Object obj = list.get(pos);
			
			if (obj.getClass().getName().equals("Station"))
				moveStations((Station) obj, pos, toAdd, 'r');
			else if (obj.getClass().getName().equals("Train"))
				moveTrain((Train) obj, pos, toAdd, 'r');
			else if (obj.getClass().getName().equals("Detour"))
				moveDetour((Detour) obj, pos, toAdd, 'r');
		}
		addToList(toAdd);
		
		if (dateTime.get(Calendar.MINUTE) == 30 || dateTime.get(Calendar.MINUTE) == 00)
			initTrains();
	}
	
	public void moveDetour(Detour detour, int pos, HashMap<Integer, Object> toAdd, char direction) {
		Train toUse = new Train();
		boolean nextStop = false;
		
		if (detour.getTrain() != null && detour.getDivertedTrain() == null && detour.getTrain().getDirection() == direction) {
			toUse = detour.getTrain();
			nextStop = true;
		} else if (cont == 0 && (detour.getTrain() != null || detour.getDivertedTrain() != null)) {
			cont += 1;
		} else if (cont > 0 && ((detour.getTrain() != null && detour.getTrain().getDirection() == direction) || (detour.getDivertedTrain() != null && detour.getDivertedTrain().getDirection() == direction))) {
			toUse = detour.getTrain() != null ? detour.getTrain() : detour.getDivertedTrain();
			nextStop = true;
		}
		
		if (nextStop && toUse.getDirection() == direction) {
			int nextPosition = (toUse.getDirection() == 'r' ? pos + 1 : pos - 1);
			int lastPosition = (toUse.getDirection() == 'r' ? pos - 1 : pos + 1);
			if (nextPosition > 0 && nextPosition < list.numElements())
				nextStop(toUse, nextPosition, lastPosition, pos, toAdd);
		}
	}
	
	public void moveTrain(Train train, int pos, HashMap<Integer, Object> toAdd, char direction) {
		if (train.getDirection() == direction) {
			int nextPosition = (train.getDirection() == 'r' ? pos + 1 : pos - 1);
			int lastPosition = (train.getDirection() == 'r' ? pos - 1 : pos + 1);
			if (nextPosition > 0 && nextPosition < list.numElements())
				nextStop(train, nextPosition, lastPosition, pos, toAdd);
		}
	}
	
	public void moveStations(Station station, int pos, HashMap<Integer, Object> toAdd, char direction) {
		if (station.getTrain() != null && station.getTrain().getDirection() == direction) {
			if (station.getNumGoingDown() > 0 || station.getNumGoingUp() > 0) {
				System.out.println("\nQuantidade de pessoas no Trem: " + station.getTrain().getNumPeople());
				System.out.println("Quantidade de pessoas para descer: " + station.getNumGoingDown());
				System.out.println("Quantidade de pessoas para subir: " + station.getNumGoingUp());
			}
			
			if (station.getNumGoingDown() > 0) {
				for (int j = 0; j <= (station.getNumGoingDown() < 2 ? station.getNumGoingDown() : 2); j++) {
					station.setNumGoingDown(station.getNumGoingDown() - 1);
					station.getTrain().setNumPeople(station.getTrain().getNumPeople() - 1);
				}
			} else if (station.getNumGoingUp() > 0) {
				for (int j = 0; j <= (station.getNumGoingUp() < 2 ? station.getNumGoingUp() : 2); j++) {
					station.setNumGoingUp(station.getNumGoingUp() - 1);
					station.getTrain().setNumPeople(station.getTrain().getNumPeople() + 1);
				}
			} else if (station.isWaiting()) {
				station.setIsWaiting(false);
			} else if (station.getNumGoingDown() == 0 && station.getNumGoingUp() == 0) {
				int nextPosition = (station.getTrain().getDirection() == 'r' ? pos + 1 : pos - 1);
				int lastPosition = (station.getTrain().getDirection() == 'r' ? pos - 1 : pos + 1);
				nextStop(station.getTrain(), nextPosition, lastPosition, nextPosition, toAdd);
				station.setTrain(null);
			}
		}
	}
	
	public void nextStop(Train train, int nextPosition, int lastPosition, int pos, HashMap<Integer, Object> toAdd) {
		Object nextObj = list.get(nextPosition);
		Object lastObj = list.get(lastPosition);

		switch (nextObj.getClass().getName()) {
			case "Station":
				Station s = (Station) nextObj;
				Detour det = (Detour) list.get(pos);
				/* TODO
				 * 		Número par de pessoas que descem e sobem
				 * 		Se ngm for descer ou subir, esperar um tempo
				 */
				
				//int goingDown = random(0, train.getNumPeople() > 10 ? 10 : train.getNumPeople());
				//int goingUp = random(0, ((50 - train.getNumPeople()) < 10 ? 50 - train.getNumPeople() : 10));
				int goingDown = random(0, 2);
				int goingUp = random(0, 2);
				s.setNumGoingDown(goingDown);
				s.setNumGoingUp(goingUp);
				s.setIsWaiting(goingDown == 0 && goingUp == 0);
				s.setTrain(train);
				
				System.out.println("Chegando na estação " + s.getId() + ", " + s.getNumGoingUp() + " pessoas subindo e " + s.getNumGoingDown() + " pessoas descendo");
				
				det.setTrain(null);
				list.remove(pos);
				list.insert(det, pos);
				toAdd.put(nextPosition, s);
				break;
				
			case "Rail":
				list.remove(pos);
				list.insert(lastObj.getClass().getName().equals("Station") ? new Detour(null, null) : new Rail(), pos);
				toAdd.put(nextPosition, train);
				break;
				
			case "Detour":
				Detour dt = (Detour) nextObj;
				list.remove(pos);
				list.insert(new Rail(), pos);
				
				if (dt.getDivertedTrain() != null) {
					System.out.println("pos: " + nextPosition + " tem um trem desviado");
					cont = 0;
					dt.setTrain(train);
					toAdd.put(nextPosition, dt);
				} else {
					Object afterDetour = list.get(train.getDirection() == 'r' ? nextPosition + 1 : nextPosition - 1);
					if (afterDetour.getClass().getName().equals("Station")) {
						Station station = (Station) afterDetour;
						if (station.getTrain() != null && station.getTrain().getDirection() != train.getDirection()) {
							toAdd.put(nextPosition, new Detour(null, train));
							/*list.remove(nextPosition);
							list.insert(new Detour(null, train), nextPosition);*/
						} else
							toAdd.put(nextPosition, new Detour(train, null));
					} else {
						boolean hasTrain = false;
						
						for (int j = 1; j < list.numElements(); j++) {
							if (nextPosition + j > list.numElements()-1 || nextPosition - j < 0)
								break;
							Object current = list.get(train.getDirection() == 'r' ? nextPosition + j : nextPosition - j);
							
							if (current.getClass().getName().equals("Detour")) {
								Detour detour = (Detour) current;
								if (detour.getTrain() != null && detour.getTrain().getDirection() != train.getDirection())
									hasTrain = true;
							} else if (current.getClass().getName().equals("Train") && ((Train) current).getDirection() != train.getDirection())
								hasTrain = true;
							else if (current.getClass().getName().equals("Station") || current.getClass().getName().equals("point"))
								break;
						}
						
						// Fazer verificação se em X min não vai sair outro trem
						if (!hasTrain)
							hasTrain = false;
		
						toAdd.put(nextPosition, (hasTrain ? new Detour(null, train) : new Detour(train, null)));
					}
				}
				break;
			
			case "Point":
				/* TODO
				 * 		quando for point, virar direção do trem ou melhor, começar um novo
				 */
				list.remove(pos);
				list.insert(new Rail(), pos);
				break;
				
			case "Train":
				/* TODO
				 * 		quando for point, virar direção do trem ou melhor, começar um novo
				 */
				list.remove(pos);
				list.insert(new Rail(), pos);
				break;
			
			default:
				break;
		}
	}
	
	public void addToList(HashMap<Integer, Object> toAdd) {
		for (Integer i : toAdd.keySet()) {
			list.remove(i);
			list.insert(toAdd.get(i), i);
	    }
		
		toAdd = new HashMap<Integer, Object>();
	}

	public void initTrains() {
		int peopleA = random(10, 50);
		int peopleB = random(10, 50);
		
		list.remove(1);
		list.insert(new Train(peopleA, 'r'), 1);
		list.remove(list.numElements() - 2);
		list.insert(new Train(peopleB, 'l'), list.numElements()-1);
		
		System.out.println("Trem do Ponto A iniciando com " + peopleA + " pessoas abordo.");
		System.out.println("Trem do Ponto B iniciando com " + peopleB + " pessoas abordo.");		
	}

	public void movePeople() {
		
	}
}
