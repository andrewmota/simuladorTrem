import java.io.*;

public class KeyBoard {
	private static String s;
	private static InputStreamReader i = new InputStreamReader(System.in);
	private static BufferedReader d = new BufferedReader(i);

	public static int readInt() {
		int a = 0;
		try {
			s = d.readLine();
			a = Integer.parseInt(s);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser inteiro: " + e);
		}
		return (a);
	}

	public static int readInt(String msg) {
		int a = 0;
		System.out.println(msg);
		try {
			s = d.readLine();
			a = Integer.parseInt(s);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser inteiro: " + e);
		}
		return (a);
	}

	public static double readDouble() {
		double a = 0;
		try {
			s = d.readLine();
			a = Double.parseDouble(s);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser número: " + e);
		}
		return (a);
	}

	public static double readDouble(String msg) {
		double a = 0;
		System.out.println(msg);
		try {
			s = d.readLine();
			a = Double.parseDouble(s);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser numero: " + e);
		}
		return (a);
	}

	public static String readString() {
		s = "";
		try {
			s = d.readLine();
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		}
		return (s);
	}

	public static String readString(String msg) {
		s = "";
		System.out.println(msg);
		try {
			s = d.readLine();
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		}
		return (s);
	}

	public static char readChar() {
		char a = ' ';
		try {
			s = d.readLine();
			a = s.charAt(0);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser char: " + e);
		}
		return (a);
	}
	
	public static char readChar(String msg) {
		char a = ' ';
		System.out.print(msg);
		try {
			s = d.readLine();
			a = s.charAt(0);
		} catch (IOException e) {
			System.out.println("Erro de I/O: " + e);
		} catch (NumberFormatException e) {
			System.out.println("o valor digitado deve ser um char: " + e);
		}
		return (a);
	}
}
