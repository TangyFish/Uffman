import java.util.*;
import java.io.*;

public class Huffman {
	Map<Character, Integer> map;
	HuffmanNode root;
	public Huffman() {
		root = null;
		map =  new TreeMap<>();
	}
	
	public void read(String filename) throws Exception{
		Scanner file = new Scanner(new File(filename));
		//maps characters to their frequencies
		while(file.hasNext()) {
			String s = file.nextLine().trim();
			for(int i = 0; i < s.length(); i++) {
				if(!map.containsKey(s.charAt(i))) {
					map.put(s.charAt(i), 0);
				}
				
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}
		}
	
		
	}
	
	public void run() throws Exception{
		//creates a list of all the characters and puts them into nodes - we will construct the tree later;
		ArrayList<HuffmanNode> nodes = new ArrayList<>();
		for(char c : map.keySet()) {
			HuffmanNode n = new HuffmanNode(c, map.get(c));
			nodes.add(n);
		}
		Collections.sort(nodes);
		System.out.println(nodes);
		
		
	}
	
	public static void main(String[] args) throws Exception{
		Huffman a = new Huffman();
		a.read("taxi.txt");
		a.run();
	}
	
	
	
	
	
	
	
	
	//printing the tree
	public void preOrder(HuffmanNode n) {
		preOrder(n.left);
		System.out.println(n.weight + " " + n.s);
		preOrder(n.right);
	}
	
	
	//huffman node with compareTo
	 class HuffmanNode implements Comparable<HuffmanNode>{
		HuffmanNode left;
		HuffmanNode right;
		char s;
		int weight;
		public HuffmanNode(char s, int num, HuffmanNode left, HuffmanNode right) {
			weight = num;
			this.s = s;
			this.left = left;
			this.right = right;
		}
		public HuffmanNode(char s, int num) {
			left = null;
			right = null;
			this.s = s;
			weight = num;
		}
		
		public int compareTo(HuffmanNode n) {
			if(this.weight > n.weight) return 1;
			if(this.weight < n.weight) return -1;
			return 0;
		}
		
		public String toString() {
			return s + " " + weight;
		}
		
		
		
	}
}
