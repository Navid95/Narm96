package Trip;

import java.util.ArrayList;

public class Location {
	
	private ArrayList<String> arraylist ;
	int a;
	
	
	public Location(String Origin,String Destination ) {
		
		arraylist = new ArrayList<String>();
		arraylist.add(Origin);
		arraylist.add(Destination);
		
	}
	
	
	
	public void AddNode (String city) {
		
		arraylist.add(arraylist.size()-1, city);
	}
	
	public void DeletNode(int arg) {
		arraylist.remove(arg);
	}
	public void DeletNode(String city) {
		arraylist.forEach(node -> {
			
			System.out.println(node);
			
			if(node.equals(city)) {			
				a = arraylist.indexOf(node);
			}
				
		});
		
		DeletNode(a);
	}
	
	public void EditOrigin (String Origin) {
		
		arraylist.remove(0);
		arraylist.add(0, Origin);
		
	}
	
	public void EditDestination (String Destination) {
		
		arraylist.add(arraylist.size(),Destination);
		arraylist.remove(arraylist.size()-2);
	}
	
	public ArrayList<String> getArrayList (){
		return this.arraylist;
	}
 
}
