import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.fillRails();

		char option = KeyBoard.readChar("Você deseja simular a cada tecla pressionada (S ou N)? ");
		if (option == 'S' || option == 's') {
			while (KeyBoard.readChar("Pressione uma tecla e após a tecla Enter: ") != 0) {
				System.out.println();
				/*if (simulator.getDate().get(Calendar.HOUR_OF_DAY) == 8 && simulator.getDate().get(Calendar.MINUTE) == 10) break;
				else {*/
					simulator.moveTrains();
					simulator.movePeople();
					System.out.println(simulator.getDate().getTime());
					System.out.println(simulator.getList().toString());
					simulator.addTime(1);
				//}
			}
		} else {
			while (!(simulator.getDate().get(Calendar.HOUR_OF_DAY) == 10 && simulator.getDate().get(Calendar.MINUTE) == 30)) {
				simulator.moveTrains();
				simulator.movePeople();
				System.out.println(simulator.getDate().getTime());
				System.out.println(simulator.getList().toString());
				simulator.addTime(1);
			}
		}
	}
}