
public class Bar {
	private String name;
	private String adress;
	private String startHour; //start happy hour
	private String endHour;
	private boolean srch;
	
	public Bar(){
		this.srch = false;
		
	}
	
	public Bar(String name, String adress, String stHour, String eHour){
		this.name = name;
		this.adress = adress;
		this.startHour = stHour;
		this.endHour = eHour;
	}
	
	public Bar(Bar aBar){
		this.name = aBar.name;
		this.adress = aBar.adress;
		this.startHour = aBar.startHour;
		this.endHour = aBar.endHour;
		
	}
	
	public String getName(){
		return name;
		
	}
	
	public String getAdress(){
		return adress;
		
	}
	
	public String getStartHour(){
		return startHour;
	}
	
	public String getEndHour(){
		return endHour;
	}
	
	public boolean getSrch(){
		return srch;
	}
	
	public void setName(String aName){
		this.name = aName;
	}
	
	public void setAdress(String aAdress){
		this.adress = aAdress;
	}
	
	public void setStartHour(String aStart){
		this.startHour = aStart;
	}
	
	public void setEndHour(String aEnd){
		this.endHour = aEnd;
	}
	
	public void setSrch(boolean srch){
		this.srch = srch;
	}
	
	@Override
	public String toString(){
		String barW = "name: " + this.name + " adress:" + this.adress + "\n start time:" + this.startHour + " end time: " + this.endHour + "\n"; 
		return barW;
	}
	
	
}
