package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {
	
	public FileHandler() {
		
	}
	
	// save any object to a file
	public static void saveObject(Object obj, String outputFile) throws IOException {
        FileOutputStream outStream = new FileOutputStream(outputFile);
        ObjectOutputStream objTowrite = new ObjectOutputStream(outStream);
        objTowrite.writeObject(obj);
        outStream.close();
    }
	
	//save customer to customers.dat file by calling saveObject function
	public static void saveCustomer(CustomerList list) throws IOException {
		saveObject(list, "Customers.dat");
	    System.out.println("The serialized objects were written to ");	
	}
	
	//save property for sate in saleproperties.dat file by calling saveObject function
	public static void saveProperty(PropertyList list) throws IOException {
		saveObject(list, "SaleProperties.dat");
	    System.out.println("The serialized objects were written to ");	
	}
	
	//save property for rent in rentalproperties.dat file by calling saveObject function
	public static void saveRentalProperty(PropertyList list) throws IOException {
		saveObject(list, "RentalProperties.dat");
	    System.out.println("The serialized objects were written to ");	
	}
	
	//save trade object for sale in a file by calling saveObject function
	public static void saveSaleTrade(TradeList list) throws IOException {
		saveObject(list, "SaleTrade.dat");
	    System.out.println("The serialized objects were written to ");	
	}

	//save trade object for rent in a file by calling saveObject function
	public static void saveRentalTrade(TradeList list) throws IOException {
		saveObject(list, "RentalTrade.dat");
	    System.out.println("The serialized objects were written to ");	
	}
	
	//retrieve customer from customers.dat file 
	public static CustomerList retrieveCustomer() throws IOException, ClassNotFoundException {
		
		FileInputStream inStream = new FileInputStream("Customers.dat");
		ObjectInputStream inFile = new ObjectInputStream(inStream);
		
		CustomerList list = (CustomerList)inFile.readObject();
		System.out.println("hi retrieved new ");
		inFile.close();
		return list;
		
	}
	
	//retrieve property for sale from saleproperties.dat file 
	public static PropertyList retrieveProperty() throws IOException, ClassNotFoundException {
		
		FileInputStream inStream = new FileInputStream("SaleProperties.dat");
		ObjectInputStream inFile = new ObjectInputStream(inStream);
		System.out.println("hi trying to retrieve ");
		
		//PropertyList list = (PropertyList)inFile.readObject();
		Object obj= inFile.readObject();
		System.out.println("hi retrieved new ");
		PropertyList list = (PropertyList)obj;
		System.out.println("hi type casted ");
		inFile.close();
		return list;
		
	}
	
	//retrieve property for rent from rentalproperties.dat file 
	public static PropertyList retrieveRentalProperty() throws IOException, ClassNotFoundException {
		
		FileInputStream inStream = new FileInputStream("RentalProperties.dat");
		ObjectInputStream inFile = new ObjectInputStream(inStream);
		System.out.println("hi trying to retrieve ");
		
		//PropertyList list = (PropertyList)inFile.readObject();
		Object obj= inFile.readObject();
		System.out.println("hi retrieved new ");
		PropertyList list = (PropertyList)obj;
		System.out.println("hi type casted ");
		inFile.close();
		return list;
		
	}
	
	//retrieve trade object of sale properties
	public static TradeList retrieveSaleTrade() throws IOException, ClassNotFoundException {
		
		FileInputStream inStream = new FileInputStream("SaleTrade.dat");
		ObjectInputStream inFile = new ObjectInputStream(inStream);
		System.out.println("hi trying to retrieve ");
		
		//PropertyList list = (PropertyList)inFile.readObject();
		Object obj= inFile.readObject();
		System.out.println("hi retrieved new ");
		TradeList list = (TradeList)obj;
		System.out.println("hi type casted ");
		inFile.close();
		return list;
		
	}
	
	
	//retrieve trade object of rental properties
	public static TradeList retrieveRentalTrade() throws IOException, ClassNotFoundException {
		
		FileInputStream inStream = new FileInputStream("RentalTrade.dat");
		ObjectInputStream inFile = new ObjectInputStream(inStream);
		System.out.println("hi trying to retrieve ");
		
		//PropertyList list = (PropertyList)inFile.readObject();
		Object obj= inFile.readObject();
		System.out.println("hi retrieved new ");
		TradeList list = (TradeList)obj;
		System.out.println("hi type casted ");
		inFile.close();
		return list;
		
	}
}

