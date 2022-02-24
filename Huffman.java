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
		//creates a priority queue of HuffmanNodes and then does stuff
		PriorityQueue<HuffmanNode> q = new PriorityQueue<>();
		for(char c : map.keySet()) {
			HuffmanNode n = new HuffmanNode(c, map.get(c));
			q.add(n);
		}
		
		while(q.size() != 1) {
			HuffmanNode left = q.poll();
			HuffmanNode right = q.poll();
			HuffmanNode n = new HuffmanNode('*', left.weight + right.weight, left, right);
			q.add(n);
		}
		
		root = q.peek();

		System.out.println(q);
//		System.out.println(root.left);
//		System.out.println(root.right);
		
		
	}
	
	//printing the tree
	public void preOrder() {
		preOrder(root);
	}
	public void preOrder(HuffmanNode n) {
		if(n != null) {
			System.out.println(n);
			preOrder(n.left);
			preOrder(n.right);
		}
		return;
		
	}
	
	public static void main(String[] args) throws Exception{
		Huffman a = new Huffman();
		a.read("taxi.txt");
		a.run();
		a.preOrder();
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
			return this.s - n.s;
		}
		
		public String toString() {
			return s + " " + weight;
		}
		
		
		
	}
}
