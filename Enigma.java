
import java.io.*;
import java.util.*;
public class Enigma {
	private PrintWriter writer;
	private Scanner f;
	private rotor rotor1;
	private rotor rotor2;
	private rotor rotor3;
	Queue<Integer> shiftlist;
	
	public Enigma() {
		//3 rotors of the enigma machine
		rotor1 = new rotor(new int[] {3,23,1,23,10,0,23,13,21,15,24,19,4,2,12,12,2,21,9,7,4,6,13,23,10,22});
		rotor2 = new rotor(new int[] {11,1,2,24,7,20,5,7,13,25,25,19,19,7,12,4,6,8,5,1,23,24,5,10,23,1,});
		rotor3 = new rotor(new int[] {23,5,22,3,8,24,23,6,25,12,0,12,8,5,14,25,9,10,20,7,15,2,24,12,14,9}); 
		
	}
	
	public void encode(String inname, String outname) throws Exception{
		writer = new PrintWriter(new File(outname));
		f = new Scanner(new File(inname));
		writer.println("Encryption Key: " + rotor1.shift[0] + " " + rotor2.shift[0] + " " + rotor3.shift[0]);
		while(f.hasNext()) {
			String word = f.next();
			for(int i = 0; i < word.length(); i++) {
				int shiftind = (rotor1.shift[rotor1.index] + rotor2.shift[rotor2.index] + rotor3.shift[rotor3.index]);
				bigRotate();
				writer.print(shift(shiftind, word.charAt(i)));
			}
			writer.print(" ");
		}
		f.close();
		writer.close();
		
	}
	
	public String decode(String inname) throws Exception{
		f = new Scanner(new File(inname));
		String print = "";
		String[] keyLine = f.nextLine().trim().split(" ");
		int one = Integer.parseInt(keyLine[1]);
		int two = Integer.parseInt(keyLine[2]);
		int three = Integer.parseInt(keyLine[3]);
		rotor1.setind(one);
		rotor2.setind(two);
		rotor3.setind(three);
		
		
		while(f.hasNext()) {
			String word = f.next();
			for(int i = 0; i < word.length(); i++) {
				int shiftind = (rotor1.shift[rotor1.index] + rotor2.shift[rotor2.index] + rotor3.shift[rotor3.index]);
				bigRotate();
				print += deshift(shiftind, word.charAt(i));
			}
			print += " ";
			
		}
		
		return print.trim();
	}
	
	
	
//	public Character shift(int s, char c) {
//		return (char) ((c + s) % 26 + 65);
//	}
//	public Character deshift(int s, char c) {
//		return (char) ((c - s) % 26 + 65);
//	}
	public char shift(int num, char in) {
		return (char)((in + num)%65%26+65);
	}
	
	public char deshift(int num, char in) {
		return (char)(shift(26-num%26, in));
	}
	
	
	public void bigRotate() {
		rotor1.rotate();
		if(rotor1.index == 0) {
			rotor2.rotate();
			if(rotor2.index == 0) {
				rotor3.rotate();
			}
		}
		

	}

	//represents the "rotor" of the enigma machine
	class rotor {
		int[] shift;
		char[] letters;
		int index;
		
		public rotor(int[] setting) {
			shift = setting;
			letters = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			index = 0;
		}
		private void setind(int n) {
			for(int i = 0; i < shift.length; i++) {
				if(shift[i] == n) index = n;
				break;
			}
		}
		private void rotate() {
			index++;
			index%=26;
		}
		
		
	}
	
}
