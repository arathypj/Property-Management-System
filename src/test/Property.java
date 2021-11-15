package test;

import java.io.Serializable;

public class Property implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String propertyType;
	private String houseType;
	private int noOfBedrooms;
	private int noOfBathrooms;
	private String address;
	private String area;
	private int price;
	private boolean availabilityStatus;
	private static final int maxNoOfbedrooms = 5;
	private static final int minNoOfbedrooms = 1;
	
	//constructor
	public Property() {
		propertyType = "";
		houseType = "";
		noOfBedrooms = 0;
		noOfBathrooms = 0;
		address = "";
		area = "";
		price = 0;
		availabilityStatus = true;
	}
	
	
	//getters
	public String getPropertytype() {
		return propertyType;
	}
	
	public String getHousetype() {
		return houseType;
	}
	
	public int getNoOfBedrooms() {
		return noOfBedrooms;
	}
	
	public int getNoOfBathrooms() {
		return noOfBathrooms;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getArea() {
		return area;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean getStatus() {
		return availabilityStatus;
	}
	
	
	//setters
	public void setPropertytype(String s) {
		this.propertyType = s;
	}
	
	public void setHousetype(String s) {
		this.houseType = s;
	}
	
	public void setNoOfBedrooms(int num) {
		this.noOfBedrooms = num;
	}
	
	public void setNoOfBathrooms(int num) {
		this.noOfBathrooms = num;
	}
	
	public void setAddress(String s) {
		this.address = s;
	}
	
	public void setArea(String s) {
		this.area = s;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStatus(boolean b) {
		this.availabilityStatus = b;
	}
	
	
	public boolean checkLimit(int beds) {
		if (beds<minNoOfbedrooms || beds>maxNoOfbedrooms) {
			return false;
		}
		return true;
	}
	
	//override toSting() methor to print property
	public String toString() {
		String str = "\nProperty type : " + propertyType;
		str += "\nHouse type :" + houseType;
		str += "\nNo of Bedrooms : " + noOfBedrooms;
		str += "\nNo of Bathrooms  : "+ noOfBathrooms;
		str += "\nAddress  : "+ address;
		str += "\nArea  : "+ area;
		str += "\nPrice  : "+ price;
		str += "\nAvalability  : "+ availabilityStatus+"\n";
		return str;
	}
	
	
}
