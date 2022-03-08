import java.util.Scanner;

public class Enigma_Runner{
	public static void main(String[] args) throws Exception{
		Enigma_Runner a = new Enigma_Runner();
		a.run();
	}

	public void run() throws Exception{
		Scanner scan = new Scanner(System.in);
		Enigma en = new Enigma();
		en.encode("tiny.txt", "example.txt");
		System.out.print(en.decode("example.txt"));
		
		
		
	}
} 
