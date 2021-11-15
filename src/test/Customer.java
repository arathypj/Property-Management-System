package test;

import java.io.Serializable;

public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private long phoneNumber;
	private String address;
	private String email;
	
	//constructor
	public Customer() {
		name = "";
		phoneNumber = 0L;
		address = "";
		email = "";
	}
	//parametised constructor
	public Customer(String name, long phoneNumber, String address, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}
	
	//getters
	public String getName() {
		return this.name;
	}

	public long getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	//setters
	
	public void setName(String s) {
		this.name = s;
	}
	
	public void setPhoneNumber(long num) {
		this.phoneNumber = num;
	}
	
	public void setAddress(String s) {
		this.address = s;
	}
	
	public void setEmail(String s) {
		this.email = s;
	}
	
	
	//to print the function
	public String toString() {
		String str = "\n\nName : "+ this.name;
		str += "\nPhone Number : "+ this.phoneNumber;
		str += "\nAddress : "+this.address;
		str += "\nEmail : "+this.email;
		return str;
	}
}
