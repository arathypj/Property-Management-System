package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PropertyList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Property> propertyList;
	
	
	//constructor
	public PropertyList() {
		
		propertyList = new ArrayList<Property>();
		
	}
	
	
	//add property to the list
	public void addProperty(Property p) {
		propertyList.add(p);
		System.out.println("hi reached here too many times");
		
	}
	
	//return property list
	public List<Property> getPropertyList(){
		Property p = propertyList.get(0);
		System.out.println(p);
		return propertyList;
	}
	
	
	//method to print property list
	public String toString() {
		String str = "";
		if(propertyList.isEmpty()) {
			str = "No property available";
		}else {
			for(int i= 0; i<propertyList.size() ;i++) {
				str += propertyList.get(i);
			}
		}
		return str;
	}
	
	//method to return properties that is available for sale or rent 
	public List<Property> getAvailablePropertyList(){
		System.out.println(propertyList.size());
		for(int i= 0; i<propertyList.size(); i++) {
			if(!propertyList.get(i).getStatus()) {
				System.out.print("removed");
				propertyList.remove(i);  
				i--;
			}
		}
		
		return propertyList;
	}
	
	
	//return the property  by address if already exiss
	public Property getPropertyByAddress(String address) {
		for(int i= 0; i<propertyList.size(); i++) {
			if(address.trim().equalsIgnoreCase(propertyList.get(i).getAddress().trim())) {
				Property prop =  propertyList.get(i);
				return prop;
			}
		}
		
		return null;
		
    }
	
	
	//method to return properties according to the search option
	public List<Property> getFilterPropertyList(String type, String houseType, String address, String area, String beds, String baths, String price) {
		 
		if(!type.isEmpty() || type!="") {
			System.out.println(propertyList.size());
			for(int i= 0; i<propertyList.size();i++) {
				System.out.println(i+"  "+propertyList.get(i).getPropertytype());
				if(!type.equals(propertyList.get(i).getPropertytype())) {
					System.out.println("removed by type");
					propertyList.remove(i);  
					i--;
				}
			}
		}
		if(!houseType.isEmpty() || houseType!="") {
			for(int i= 0; i<propertyList.size() ;i++) {
				System.out.println(propertyList.get(i).getHousetype());
				if(!houseType.equals(propertyList.get(i).getHousetype())) {
					propertyList.remove(i);   
					System.out.println("removed by house");
					System.out.println(houseType);
					System.out.println(propertyList.get(i).getHousetype());
					i--;
				}
			}
		}
		if(!area.isEmpty()) {
			for(int i= 0; i<propertyList.size() ;i++) {
				System.out.println(propertyList.get(i).getArea());
				if(!area.equalsIgnoreCase(propertyList.get(i).getArea())) {
					System.out.println("removed by area");
					
					propertyList.remove(i); 
					i--;
				}
			}
		}
		
		if(!address.isEmpty()) {
			for(int i= 0; i<propertyList.size() ;i++) {
				if(!address.equalsIgnoreCase(propertyList.get(i).getAddress())) {
					propertyList.remove(i);  
					i--;
				}
			}
		}
		
		if(!beds.isEmpty()) {
			for(int i= 0; i<propertyList.size() ;i++) {
				if(propertyList.get(i).getNoOfBedrooms()!=Integer.parseInt(beds)) {
					propertyList.remove(i);   
					i--;
				}
			}
		}
		
		if(!baths.isEmpty()) {
			for(int i= 0; i<propertyList.size() ;i++) {
				if(propertyList.get(i).getNoOfBathrooms()!=Integer.parseInt(baths)) {
					propertyList.remove(i);  
					i--;
				}
			}
		}
		
		if(!price.isEmpty()) {
			for(int i= 0; i<propertyList.size() ;i++) {
				if(propertyList.get(i).getPrice()!=Integer.parseInt(price)) {
					propertyList.remove(i);   
					i--;
				}
			}
		}
		
		return propertyList;
	}

	//return property by address and type for getting quotation
	public Property getSearchPropertyList(String address, String type){
		
		for(int i= 0; i<propertyList.size() ;i++) {
			System.out.print(propertyList.get(i).getPropertytype());
			System.out.println(propertyList.get(i).getAddress());
			if(address.equalsIgnoreCase(propertyList.get(i).getAddress()) && type.equals(propertyList.get(i).getPropertytype())) {
				Property prop = propertyList.get(i);
				System.out.println(prop);
				return prop;
			}
		}
		return null;
	}
	
	
//	public void serachItem(String s) {
//		for(int i= 0; i<propertyList.size() ;i++) {
//			
//		}
//	}
}
