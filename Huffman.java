import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class Huffman {
	Map<Character, Integer> map;
	Map<Character, StringBuffer> codemap;
	PrintWriter writer;
	HuffmanNode root;
	
	
	public Huffman() {
		root = null;
		map =  new HashMap<>();
		codemap = new HashMap<>();
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

		for(char c : map.keySet()) {
			encode(c, filename);
			
		}
	}
	
	//given a character, traverse the tree and add on 1s and 0s
	public void encode(char c, String filename) throws FileNotFoundException {
		if(root == null) return;
		else {
			File file = new File(filename);
			writer = new PrintWriter(file);
			StringBuffer st = new StringBuffer("");
			encode(root, c, st);
			writer.close();
		}
	}
	
	public void encode(HuffmanNode n, Character c, StringBuffer code) {
		if(n == null) return;
		else if(n.s == c) {
			System.out.println(c + " " + code);
			writer.print(code);
		}
		else {
			code.append('0');
	        encode(n.left, c, code);
	        code.deleteCharAt(code.length()-1);
	        
	        code.append('1');
	        encode(n.right, c, code);
	        code.deleteCharAt(code.length() - 1);
	         
		}
		 
		
	}
	
	
	//given a filename, go to that file and decode the characters
	
	public void decode(String outputfile) throws Exception{
		Scanner output = new Scanner(new File(outputfile));
		
		
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
