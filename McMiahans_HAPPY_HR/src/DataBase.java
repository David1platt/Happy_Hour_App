import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class DataBase {
	private BufferedReader txtIn = null;
	static int ARR_SIZE = 7;
//	private Set<Bar> barTree = new TreeSet<Bar>(new MyComparator()); 
	private List<Bar> [] barrArr;
	private List<Bar> [] endArr;
	DataBase() throws IOException, URISyntaxException{
		barrArr = new ArrayList[ARR_SIZE];
		endArr = new ArrayList[ARR_SIZE];
		for(int i = 0; i < ARR_SIZE; i++){
			barrArr[i] = new ArrayList<Bar>();
			endArr[i] = new ArrayList<Bar>();
		}
		readData();
		connectArr(barrArr);
	}
	
	
	public void readData() throws IOException, URISyntaxException{
		String line = null, unparsed = null, name = null, adress = null, startHour = null, endHour = null;
		int key = 0;
		InputStream filePlace = getClass().getResourceAsStream("./Bars.dat");
		InputStreamReader fileStuff = new InputStreamReader(filePlace); 
		//File file = new File(filePlace.toURI());
		txtIn = new BufferedReader(fileStuff);
		StringBuilder aString = new StringBuilder();
			while((line = txtIn.readLine()) !=null){
				aString.append(line);
			}	
			unparsed = aString.toString();
			StringTokenizer token = new StringTokenizer(unparsed, ";");
			while(token.hasMoreElements()){
				Bar aBar = new Bar();
				
				name = token.nextToken().toString();
				adress = token.nextToken().toString();
			//	token = new StringTokenizer(unparsed, ";");
				startHour = token.nextToken().toString();
		//		token = new StringTokenizer(unparsed, "\n");
				endHour = token.nextToken().toString();

				aBar.setName(name);

				aBar.setAdress(adress);

				aBar.setStartHour(startHour);

				aBar.setEndHour(endHour);

				key = hashF(startHour);
				barrArr[key].add(aBar);
		}
			txtIn.close();
	}
	
	
	public void writeData() throws IOException{
		String fileName = "./Bars.dat", name, adress, startHr, endHr;
		FileWriter file = new FileWriter(fileName);
		BufferedWriter buffer = new BufferedWriter(file);
		for(int i = 0; i < barrArr.length; i++){
			for(Bar b : barrArr[i]){
				name = b.getName();
				buffer.write(name + ';');
				adress = b.getAdress();
				buffer.write(adress + ';');
				startHr = b.getStartHour();
				buffer.write(startHr + ';');
				endHr = b.getEndHour();
				buffer.write(endHr + ';' + '\n');
			}
		}
		buffer.close();
	}

	public void addRecord(){
		Utilities stuff = new Utilities();
		String name, adress, startTime, endTime;
		int key = 0;
		String promptOne = "Please provid the name of the bar: ";
		name = stuff.getString(promptOne);
		String promptTwo = "Please provid the adress of the bar: ";
		adress = stuff.getString(promptTwo);
		String promptThree = "Please provid the start time for happy hour in x:xx format: ";
		startTime = stuff.getString(promptThree);
		String promptFour = "Please provid the end time for happy hour in x:xx format: ";
		endTime = stuff.getString(promptFour);
		Bar aBar = new Bar(name, adress, startTime, endTime);
		key = hashF(startTime);
		this.barrArr[key].add(aBar);
		
	}
	
	public boolean removeRecord(String sTerm){
		for(int i = 0; i < ARR_SIZE; i++){
			for(Bar b : barrArr[i]){
				if(sTerm.equalsIgnoreCase(b.getName())){
					System.out.println("The lising for: " + b.getName() + " is being deleted.");
					barrArr[i].remove(b);
					return true;
				}
			}
		}
		System.out.println("The bar name searched for is not in the database.");
		return false;
	}
	
	public boolean barInfo(String sTerm){
		for(int i = 0; i < ARR_SIZE; i++){
			for(Bar b : barrArr[i]){
				sTerm = sTerm.replaceAll(" ", "").toLowerCase();
				String srch = b.getName().replaceAll(" ", "").toLowerCase();
				if(srch.contains(sTerm)){
					String info = b.toString();
					System.out.println("The lisings that match are: " + info);
					return true;
				}
			}
		}
		System.out.println("The bar name searched for is not in the database.");
		return false;
	}		
	
	public boolean searchTime(String sTime){
		int key = hashF(sTime);
		if(barrArr[key].isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void printFoundBars(String sTime){
		int key = hashF(sTime);
		System.out.println("The following bars are serving happy hour soon:\n");
		for(Bar b : barrArr[key]){
			String words = b.toString();
			System.out.println(words);
		}
	}
	
	public void printHappyHrActive(String sTime){
		int key = hashE(sTime);
		System.out.println("The listed bars are still serving happy hour: ");
		for(Bar b :  endArr[key]){
			String words = b.toString();
			System.out.println(words);
		}
	}
	
	public void showAll(){
			for(int i = 0; i < barrArr.length; i++){
				for(Bar b : barrArr[i]){
					String info = b.toString();
					System.out.println(info);
				}
		}
	}
	
	private int hashF(String key)throws NumberFormatException{
		key = key.trim();
		if(key.length() < 4 || key.length() > 5 || !key.contains(":"))
		{
			System.out.println("Format Error! Please provide time in the following format: 11:11 or 1:11 ");
			return 0;
		}
		String[] aKey = key.split(":");
		String hrs = aKey[0];
		String min = aKey[1];
		
		int hours = Integer.parseInt(hrs);
		int minut = Integer.parseInt(min);
		if(minut <= 30){//rounds off minutes so that user input will more closely estimate and match happy hour times
			minut = 0;  //using the hashing function
		}
		if(minut >= 30)
		{
			hours = hours + 1;
			minut = 0;
		}
	
		int hKey = 0;
		
		hKey = (hKey + hours + minut);

		hKey = hKey % 7;
		return hKey;
	}
	
	private int hashE(String key)throws NumberFormatException{
		key = key.trim();
		if(key.length() < 4 || key.length() > 5 || !key.contains(":"))
		{
			System.out.println("Format Error! Please provide time in the following format: 11:11 or 1:11 ");
			return 0;
		}
		String[] aKey = key.split(":");
		String hrs = aKey[0];
		String min = aKey[1];
		
		int hours = Integer.parseInt(hrs);
		int minut = Integer.parseInt(min);
		if(minut <= 30){//rounds off minutes so that user input will more closely estimate and match happy hour times
			//hours = hours + 1;
			minut = 0;  //using the hashing function
		}
		if(minut >= 30)
		{
			//hours = hours + 2;
			minut = 0;
		}
	
		int hKey = 0;
		
		hKey = (hKey + hours);

		hKey = hKey % 7;
		return hKey;
	}
	
	private void connectArr(List<Bar>[] list){
		String endHr = null;
		int hash = 0;
		for(int i = 0; i < list.length; i++)
		{
			for(Bar b : list[i]){
				endHr = b.getEndHour();
				hash = hashE(endHr);
				endArr[hash].add(b);
			}
			
		}
	}
}

