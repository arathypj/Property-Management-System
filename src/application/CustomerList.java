package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;
	
	//constructor
	public CustomerList() {
		
		customerList = new ArrayList<Customer>();
		
	}
	
	//add customer to the list
	public void addCustomer(Customer c) {
		customerList.add(c);
		
	}
	
	//return customer list
	public List<Customer> getCustomerList(){
		Customer c = customerList.get(0);
		System.out.println(c);
		return customerList;
	}
	
	
	//return a particuler customer with name and email - searching
	public Customer getCustomer(String name, String email) {
		for(int i=0; i<customerList.size();i++) {
			if (name.equalsIgnoreCase(customerList.get(i).getName()) && email.equalsIgnoreCase(customerList.get(i).getEmail())) {
				Customer cust = customerList.get(i);
				System.out.println("yes custmer");
				return cust;
			}
		}
		System.out.print("no custmer");
		return null;
	}
	
	public Customer getCustomerWithEmail(String email) {
		for(int i=0; i<customerList.size();i++) {
			if (email.equalsIgnoreCase(customerList.get(i).getEmail())) {
				Customer cust = customerList.get(i);
				System.out.println("yes custmer");
				return cust;
			}
		}
		System.out.print("no custmer");
		return null;
	}
	
	//to pring the customer list
	public String toString() {
		String str = "";
		for(int i= 0; i<customerList.size() ;i++) {
			str += customerList.get(i);
		}
		return str;
	}

}
