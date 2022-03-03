import java.io.File;
import java.util.Scanner;

public class Huffman_Runner {
	public static void main(String[] args) throws Exception{
		Huffman a = new Huffman();
//		a.read("hamlet.txt");
//		a.preOrder(); 
//		a.readPreOrder();
//		a.encode(new File ("hamlet.txt"));
//		Scanner scan = new Scanner(new File("encode.txt"));
//		
//		do {
//			String print = scan.nextLine();
//			String d = a.decode(print);
//			System.out.println(d);
//		}
//		while(scan.hasNext());
		a.read("taxi.txt");
		String bruh = a.encode("ab ab cab");
		System.out.println(bruh);
		System.out.println(a.decode(bruh));
		
	}

}
