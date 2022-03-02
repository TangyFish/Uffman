import java.io.File;
import java.util.Scanner;

public class Huffman_Runner {
	public static void main(String[] args) throws Exception{
		Huffman a = new Huffman();
		a.read("hamlet.txt");
		a.addNodes();
		a.preOrder();
		a.readPreOrder();
		File ham = new File("hamlet.txt");
		a.encode(ham);
		Scanner scan = new Scanner(new File("encode.txt"));
		int i=0;
		do {
			String print = scan.nextLine();
			System.out.println(a.decode(print));
			i++;
		}
		while(scan.hasNext());
	}

}
