import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Huffman {
	
	private TreeMap<Character, Integer> map;
	private Node root;
	private ArrayList<Node> list;
	private PriorityQueue<Node> Q;
	private PrintWriter writer; // writer writes to the uff.txt file.
	private PrintWriter codeWriter; // writes to the encode.txt file, which stores a file in terms of huffmancode
	private HashMap<Character, String> cypMap; // map of all Huffman Nodes to their huffmancode ID
	
	
	// constructor
	public Huffman() {
		list = new ArrayList<>();
		root = null;
		map = new TreeMap<>();
		Q = new PriorityQueue<>();
		try {
			writer = new PrintWriter(new File("uff.txt"));
			codeWriter = new PrintWriter(new File("encode.txt"));
		}
		catch(Exception e) {
			
		}
		cypMap =  new HashMap<>();
	}
	
	// reads a file and builds the huffmantree + map
	public void read(String filename) throws Exception{
		// makes a Map of all inputs, sorts them, and adds them into a priority Q
		Scanner scan = new Scanner(new File(filename));
		String read ="";
		while(scan.hasNext()) {
			read += scan.nextLine() + "\n";
		}
		for (int i=0;i<read.length();i++) {
			char cur = read.charAt(i);
			if (map.get(cur)==null) {
				map.put(cur, 1);
			}
			else {
				map.put(cur, map.get(cur)+1);
			}
		}
		for (Character bob : map.keySet()) {
			list.add(new Node( map.get(bob), bob));
		}
		Collections.sort(list);
		for(Node n: list) {
			Q.add(n);
		}
		// Calls addNodes to add create Hufftree
		addNodes();
		// Calls addCyp to add all HuffmanCode to the cypMap
		addCyp(root.left);
		addCyp(root.right);
	}
	
	
	// prints out tree in preorder
	public void preOrder() {
		preOrder(root);
	}
	private void preOrder(Node n) {
		if (n!= null) {
			System.out.println(n);
			preOrder(n.left);
			preOrder(n.right);
		}
		return;
	}
	
	// writes the tree in preorder into the uff.txt file
	public void readPreOrder() {
		readPreOrder(root);
		writer.close();
	}
	private void readPreOrder(Node n) {
		if (n!= null) {
			writer.println(n);
			readPreOrder(n.left);
			readPreOrder(n.right);
		}
		return;
	}
 	
	
	// helper method in making Huffman tree. Adds nodes to the tree
	private void addNodes() {
		//uses the priority queue to make a Huffman Tree
		while(!Q.isEmpty()) {
			Node cur = Q.poll();
			if (Q.isEmpty()) {
				root = cur;
				return;
			}
			addNodes(cur, Q.poll());
		}
	}
	
	private void addNodes(Node add, Node add2) {
		
		Node small = add.chooseSmall(add2);
		addCode(small, "0");
		Node big = add.chooseBig(add2);
		addCode(big, "1");
		
		Node par = new Node( small, big, '*', add.sum+add2.sum);
		Q.add(par);
	}
	// helper method to adddNodes

	private void addCode(Node n, String cyp) {
		if (n != null) {
			n.code=cyp + n.code;
			addCode(n.right, cyp);
			addCode(n.left, cyp);
		}
	}
	
	// helper adds all huffmancode IDs of all Nodes into a map
		private void addCyp(Node n) {
			if (n!=null) {
				cypMap.put(n.data, n.code);
				addCyp(n.left);
				addCyp(n.right);
			}
		}
	
		// encodes a string into huffmancode
	public String encode(String cyp) {
		if (cyp.length()>0) {
			if (cypMap.get(cyp.charAt(0))!=null ) {
				return cypMap.get(cyp.charAt(0)) + encode(cyp.substring(1));
			}
		}
		return "";
	}
	
	// encodes an entire file into huffmancode and writes it into the encode.txt file
	public void encode(File filename) throws FileNotFoundException {
		Scanner scan = new Scanner(filename);
		String read ="";
		String code = "";
		while(scan.hasNext()) {
			read = scan.nextLine();
			code = encode(read);
			codeWriter.println(code);
		}
		codeWriter.close();		
	}
	
	// decodes a string of huffmanCode into a regular string
	public String decode(String cyp) {
		return decode(cyp, root);
	}
	
	private String decode(String cyp, Node n) {
		if (n == null) {
			return "";
		}
		if (n.data!='*' || cyp.length()==0) {
			if (n.equals(root)) {
				return "";
			}
			return n.data + (cyp.length()==0 ? "":decode(cyp, root));
		}
		if (cyp.charAt(0) == '1') {
			return decode(cyp.substring(1), n.right);
		}
		else if (cyp.charAt(0)  == '0') {
			return decode(cyp.substring(1), n.left);
		}
		return "";
		
	}
	
	// huff node. Implements comparable to compare Nodes and sort
	public class Node implements Comparable<Node>{
	
		Node left;
		Node right;
		char data;
		Integer sum;
		String code;
		
		public Node(Node l, Node r, char d, int s) {
			left = l;
			right = r;
			data = d;
			sum =s;
			code = "";
		}
		public Node (Integer s, char d) {
			left = null;
			right = null;
			sum = s;
			data =d;
			code = "";
			
		}
		@Override
		public int compareTo(Huffman.Node o) {
			// TODO Auto-generated method stub
			return this.sum-o.sum;
		}
		
		public Node chooseSmall(Node o) {
			if (o.sum>this.sum) {
				return this;
			}
			return o;
		}
		
		public Node chooseBig(Node o) {
			if (o.sum>this.sum) {
				return o;
			}
			return this;
		}
		
		public String toString() {
			return data + ": " + sum+" " + code;
		}
	}

}
