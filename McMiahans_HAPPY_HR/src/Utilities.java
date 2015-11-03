import java.util.Scanner;
public class Utilities {
	
	public String getString(String prompt){
		Scanner input = new Scanner(System.in);
		String words = null;
		System.out.println(prompt);
		words = input.nextLine();
		//input.close();
		return words;
	}
	
	public char getChar(String prompt){
		Scanner input = new Scanner(System.in);
		char letter = ' ';
		System.out.println(prompt);
		letter = input.next().charAt(0);
		//input.close();
		return letter;
	}
	
	public int getInt(String prompt){
		Scanner input = new Scanner(System.in);
		int number = 0;
		System.out.println(prompt);
		number = Integer.valueOf(input.next());
		
		return number;
		
		//input.close();
	}
}
