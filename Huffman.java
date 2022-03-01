import java.util.*;
import java.io.*;

public class Huffman {
	Map<Character, Integer> map;
	PrintWriter writer;
	HuffmanNode root;
	String compressed;
	
	public Huffman() throws FileNotFoundException {
		root = null;
		map =  new HashMap<>();
		compressed = "lorem ipsum";
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
	
	//builds the huffman tree
	public void run(String filename) throws Exception{
		//creates a priority queue of HuffmanNodes
		PriorityQueue<HuffmanNode> q = new PriorityQueue<>();
		writer = new PrintWriter(new File(filename));
		for(char c : map.keySet()) {
			HuffmanNode n = new HuffmanNode(c, map.get(c));
			q.add(n);
		}
		//uses priorityqueue in order to build the huffman tree
		while(q.size() != 1) {
			HuffmanNode left = q.poll();
			HuffmanNode right = q.poll();
			HuffmanNode n = new HuffmanNode('*', left.weight + right.weight, left, right);
			q.add(n);
		}
		
		root = q.poll();
		
		//compress the tree into bits
		compressed = "";
		for(char c : map.keySet()) {
			encode(c, filename);
		}
		
		writer.print(compressed);
		writer.close();
		
		//decode the tree from the output file & print it out
		Scanner temp = new Scanner(new File(filename));
		System.out.print(decode(temp.next().trim()));
		temp.close();
	}
	
	//given a character, traverse the tree and add on 1s and 0s
	public void encode(char c, String filename) throws Exception {
		if(root == null) return;
		else {
			StringBuffer st = new StringBuffer("");
			encode(root, c, st, filename);
		}
	}
	
	public void encode(HuffmanNode n, Character c, StringBuffer code, String filename) throws Exception{
		if(n == null) return;
		else if(n.s == c) {
			compressed += code.toString();
			
		}
		else {
			code.append('0');
	        encode(n.left, c, code, filename);
	        code.deleteCharAt(code.length()-1);
	        
	        code.append('1');
	        encode(n.right, c, code, filename);
	        code.deleteCharAt(code.length() - 1);
	         
		}
		 
		
	}
	
	public String decode(String cyp) {
		
		return decode(cyp, root);
	}
	
	private String decode(String cyp, HuffmanNode n) {
		if (n == null) {
			return "";
		}
		if (n.s!='*' || cyp.length()==0) {
			return n.s + (cyp.length()==0 ? "":decode(cyp, root));
		}
		if (cyp.charAt(0) == '1') {
			return decode(cyp.substring(1), n.right);
		}
		else if (cyp.charAt(0)  == '0') {
			return decode(cyp.substring(1), n.left);
		}
		return "";
		
	}
	
	
	//given a filename, go to that file and decode the characters
	
	
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
	
	
	
	
	
	
	
	
	//huffman node with compareTo for the priorityqueue
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
