import java.util.Scanner;

public class Huffman_Runner {
	public static void main(String[] args) throws Exception{
		Huffman a = new Huffman();
		a.read("tiny.txt");
		a.addNodes();
		a.preOrder();
		System.out.println(a.decode("10100"));
	}

}
