public class Main {
	public static void main(String[] args) {
		DoublyLinkedList<Trilho> simulador = new DoublyLinkedList<Trilho>();
		
		int quilometrosEntreEstacoes = 20;
		//int estacoes = 10 + (int)(Math.random() * 20);
		int estacoes = 5;
		//simulador.insertLast(new Ponto("B"));
		
		simulador.insertFirst(new Ponto("A"));
		for (int i = 1; i <= estacoes; i++) {
			for (int j = 0; j < quilometrosEntreEstacoes; j++) {
				/*if (j == 19)
					simulador.insert(new Trilho(), pos);*/
			}
		}
	}
}