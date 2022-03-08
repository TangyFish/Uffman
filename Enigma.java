
import java.io.PrintWriter;
import java.util.*;
public class Enigma {
	private PrintWriter writer;
	private rotor rotor1;
	private rotor rotor2;
	private rotor rotor3;
	
	// represents the 3 gears of the enigma machine. Once one rotates
	public Enigma() {
		rotor1 = new rotor(new int[] {3,23,1,23,10,0,23,13,21,15,24,19,4,2,12,12,2,21,9,7,4,6,13,23,10,22});
		rotor2 = new rotor(new int[] {11,1,2,24,7,20,5,7,13,25,25,19,19,7,12,4,6,8,5,1,23,24,5,10,23,1,});
		rotor3 = new rotor(new int[] {23,5,22,3,8,24,23,6,25,12,0,12,8,5,14,25,9,10,20,7,15,2,24,12,14,9}); 
	}
	
	public void encode() {
		
	}
	
	public void decode() {
		
	}
	
	public void bigRotate() {
		rotor1.rotate();
		if (rotor1.index == 0) {
			rotor2.rotate();
			if (rotor2.index == 0) {
				rotor3.rotate();
			}
		}
	}
	
	private void bigRotate(rotor one, rotor two, rotor three) {
		
	}
	
	public char shift(int num, char in) {
		return (char)((in + num)%65%26+65);
	}
	
	public char deshift(int num, char in) {
		return (char)(shift(26-num%26, in));
	}

	// object rotor which represents the rotors on the enigma Machine. Each have 26 different wiring shifts and 26 letters corresponding to them
	class rotor {
		int[] shift;
		char[] letters;
		int index;
		
		public rotor(int[] setting) {
			shift = setting;
			letters = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			index = 0;
		}
		
		private void rotate() {
			index++;
			index%=26;
		}
		
	}
	public static void main(String[] args) throws Exception{
		Enigma a = new Enigma();
		a.run();
	}

	public void run() throws Exception{
		Enigma en = new Enigma();
		for (int i=32;i<300;i++) {
			System.out.print(shift(i,'A'));
		}
		
		
	}
}
