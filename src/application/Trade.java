package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


public class Trade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Property property;
	private double propertyPrice;
	private LocalDate date;
	
	///constructors
	public Trade(Property property) {
		this.property = property;
	}
	
	public Trade(Customer customer) {
		this.customer = customer;
	}
	
	public Trade(Property property, Customer customer) {
		this.customer = customer;
		this.property = property;
	}
	
	
	//getters
	public Property getProperty() {
		return(property);
	}
	
	public Customer getCustomer() {
		return(customer);
	}
	
	
	//setters
	public void setProperty(Property property) {
		this.property = property;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setPropertyPrice(double price) {
		this.propertyPrice = price;
	}
	
	public void setPayDate(LocalDate d) {
		this.date = d;
	}
	
	//method to calculate the amount for payment for sale
	public double getSellPayableAmount() {
		double actualPrice = property.getPrice();
		double agentFee = 0.015*actualPrice;
		propertyPrice = actualPrice + agentFee;
		return (propertyPrice);
	}
	
	
	////method to calculate the amount fr payment for rent
	public double getRentPayableAmount() {
		double rentAmount = property.getPrice();
		double agentFee = 300;
		double deposit = 3*rentAmount;
		propertyPrice = rentAmount+deposit+agentFee;
		return (propertyPrice);
	}
	
	
	//method to return the invoice for sale
	public String getSalesInvoice() {
		propertyPrice = property.getPrice();
		String str = "         INVOICE";
		str += "\nProperty Type : " + property.getPropertytype();
		str += "\nAddress : " + property.getAddress();
		str += "\nPrice : " + propertyPrice;
		str += "\nAgent fee : " + propertyPrice*0.015;
		str += "\nTotal price : " + (propertyPrice+propertyPrice*0.015);
		return str;
	}
	
	//method to return the invoice for rent
	public String getRentalInvoice() {
		propertyPrice = property.getPrice();
		String str = "         INVOICE";
		str += "\nProperty Type : " + property.getPropertytype();
		str += "\nAddress : " + property.getAddress();
		str += "\nRent : " + propertyPrice;
		str += "\nDeposit : " + 3*propertyPrice;
		str += "\nAgent fee : 300";
		str += "\nTotal price : " + (300+propertyPrice*4);
		return str;
	}
	
	public static void generatePdf(String content, String fileName) {
		
		 Document document = new Document();

	        try {

	            PdfWriter.getInstance(document, new FileOutputStream(new File(fileName)));

	            //open
	            document.open();

	            Paragraph p = new Paragraph();
	            p.add(content);
	            p.setAlignment(Element.ALIGN_CENTER);

	            document.add(p);

	            Font f = new Font();
	            f.setStyle(Font.BOLD);
	            f.setSize(8);

	            document.add(new Paragraph("This is my paragraph 3", f));

	            //close
	            document.close();

	            System.out.println("Done");
	         
	        } catch (FileNotFoundException | DocumentException e) {
	            e.printStackTrace();
	        } 
	}
	
	// to print the trade object
	public String toString() {
		String str = "\nProperty  :  ";
		str += property.toString();
		str += "Customer  :   ";
		str += customer.toString();
		str += "\n Amount Paid  :"+ propertyPrice;
		return str;
	}
}
