import java.io.*;
import java.net.URISyntaxException;
//import java.util.Scanner;
public class userIn {
	userIn() throws IOException, URISyntaxException{
		
		System.out.println(promptOne + promptTwo);
		
		menu();
	};
	//private String userInput;
	private String promptOne = "Welcome to McMiahan's happy Hour Tracker!\n";
	private String promptTwo = "This application allows you to check which bars in your area currently have an available happy hour.\n";
	private String promptThree = "Commands: <A>dd a new listing, <R>emove a listing <S>how All bars in the database,<G>et bar info based on the bar name <F>ind current available happy hour options, <Q>uit  ";
	
	public void menu() throws IOException, URISyntaxException{
		DataBase aData = new DataBase();
		Utilities stuff = new Utilities();
		char com = '0';
		while(com != 'Q'){
			com = '0';
			com = stuff.getChar(promptThree);
			com = Character.toUpperCase(com);
			switch(com){
			case 'A':
				aData.addRecord();
				break;
			case 'G':
				String promptOne = "Please provide the name of the bar you are searching for: ";
				String sTermOne = null;
				sTermOne = stuff.getString(promptOne);
				aData.barInfo(sTermOne);
				break;
			case 'O':
				String promptTwo = "Please provide the current time";
				String sTermTwo = null;
				sTermTwo = stuff.getString(promptTwo);
				aData.printHappyHrActive(sTermTwo);
				break;
			case 'R':
				String prompt = "Please provide the name of the bar you would like removed from records.";
				String sTerm = null;
				sTerm = stuff.getString(prompt);
				aData.removeRecord(sTerm);
				break;
				
			case 'S':
				aData.showAll();
				break;
				
			case 'F':
				find(aData);
				break;
			case 'Q':
				try{aData.writeData();}
				catch(IOException e){
					System.out.println(e.getMessage());
				}
				System.out.println("Have a great night out!");
				break;
				
			default:
				System.out.println("Please provide a valid command.\n");
				
			}
		}
	}
	
	private void find(DataBase aData){
		boolean srchState = false;
		Utilities getStuff = new Utilities();
		String promptOne = "Please provide the time you would like to go to happy hour: ";
		String promptTwo = "No bars found that are serving happy hour starting at the given time. ";
		String sTime = getStuff.getString(promptOne);
		srchState = aData.searchTime(sTime);
		if(srchState){
			aData.printFoundBars(sTime);
		}
		else{
			System.out.println(promptTwo);
		}
	}
}
