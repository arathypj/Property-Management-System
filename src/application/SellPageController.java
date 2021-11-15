package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SellPageController {
	
	@FXML
    private ComboBox<String> selectCombo;

    @FXML
    private ComboBox<String> typeCombo;
    
    @FXML
    private TextArea tradePropertyAddress;

    @FXML
    private Button getQuote;

    @FXML
    private TextArea messageArea;

    @FXML
    private Button payNowButton;

    @FXML
    private TextField custName;

    @FXML
    private TextField custEmail;

    @FXML
    private Button backButton;

    @FXML
    private Button sendQuote;
    
    @FXML
    private DatePicker tradeDate;

    @FXML
    private TextField totalAmount;

    @FXML
    private Button goToPayButton;

    
    private String selectedOption="Sell";
	private String selectedPropertyType="";
	
	private Property prop;
	private Customer cust;
    private PropertyList sellList;
	private PropertyList rentList;
	private Trade selling;
	private Trade renting;
	private CustomerList cList;
    private TradeList saleTradeList;
    private TradeList rentTradeList;

    
 // method for initialising the combobox and their listeners 
	public void initialize() throws ClassNotFoundException, IOException {
		
    	selectCombo.getItems().addAll("Sell", "Rent");
    	typeCombo.getItems().addAll("Flat", "House", "Bungalow");
    	getQuote.setDisable(true);
    	payNowButton.setDisable(true);
    	//sendQuote.setDisable(true);
    	goToPayButton.setDisable(true);
    	selectCombo.valueProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		    	selectedOption = newValue;
		    		getQuote.setDisable(false);
		            System.out.println("Selected Type: "+ selectedOption);
		            
		        }    
		    });
    	
    	
    	typeCombo.valueProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		        	    selectedPropertyType = newValue;
		        	    System.out.println("Selected: "+ selected);
		        	    System.out.println("Old Value: "+ oldValue);
		            System.out.println("Selected Property Type: "+ selectedPropertyType);
		            
		        }    
		    });
    }
    
    
	//button listener for getting invoice for property 
    
	public void getQuoteButtonListener() throws IOException, ClassNotFoundException {
    	
    	String tradeAddrs = tradePropertyAddress.getText();
    	if(tradeAddrs.length()==0) {
    		messageArea.setText("Please enter all details");
    	}else {
	    	if(selectedOption == "Sell") {
				sellList = new PropertyList();
		    	sellList = FileHandler.retrieveProperty();
		    	prop = sellList.getSearchPropertyList(tradeAddrs, selectedPropertyType);
				if(prop==null) {
					messageArea.setText("Property in this address doesnot exist");
				}else {
					if(prop.getStatus()) {
						selling = new Trade(prop);
						messageArea.setText(selling.getSalesInvoice());
						String invoiceName = tradeAddrs.concat("-invoice.pdf");
						System.out.println(invoiceName);
						selling.generatePdf(selling.getSalesInvoice(), invoiceName);
						goToPayButton.setDisable(false);
				    	//sendQuote.setDisable(false);
					}else {
						messageArea.setText("Property not available");
					}
				}
			}else {
				rentList = new PropertyList();
		    	rentList = FileHandler.retrieveRentalProperty();
		    	prop = rentList.getSearchPropertyList(tradeAddrs, selectedPropertyType);
				if(prop==null) {
					messageArea.setText("Property in this address doesnot exist");
				}else {
					if(prop.getStatus()) {
						renting = new Trade(prop);
						messageArea.setText(renting.getRentalInvoice());
						goToPayButton.setDisable(false);
				    	//sendQuote.setDisable(false);
					}else {
						messageArea.setText("Property not available");
					}
					
				}
				
			}
	    	
    	}
	}
    
    
    
  //button listener going to payment option after redaing customer details for sale and rent
    public void goToPayButtonListener() throws IOException, ClassNotFoundException {
    	
    	String customerName = custName.getText();
    	String email = custEmail.getText();
    	if(customerName.length()==0 || email.length()==0) {
    		messageArea.setText("You must enter customer name and email id");
    	}else {
	    	cList = new CustomerList();
	    	cList = FileHandler.retrieveCustomer();
	    	
	    	cust = cList.getCustomer(customerName, email);
	    	if(cust==null) {
	    		messageArea.setText("You have entered wrong details");
	    	}else {
		    	if(selectedOption == "Sell") {
		    		selling.setCustomer(cust);;
					
					messageArea.setText("Please make payment now\n\nAmount to be paid: "+selling.getSellPayableAmount());
				}else {
					renting.setCustomer(cust);
					messageArea.setText("Please make payment now\n\nAmount to be paid: "+renting.getRentPayableAmount());
				}
		    	
		    	payNowButton.setDisable(false);
	    	}
    	}
    	
	}
    
    
    
    
  //button listener for making payment for sale and rent
    public void payButtonListener() throws IOException, ClassNotFoundException {
    	String amount = totalAmount.getText();
    	LocalDate date = tradeDate.getValue();
    	
    	
    	if(amount.length()==0 || date == null) {
    		messageArea.setText("Please enter all details ");
    	}else {
			System.out.println(date);
			try {
				double total = Double.parseDouble(amount); 
		    	
		    	
		    	if(selectedOption == "Sell") {
		    		//selling.setPropertyPrice(total);
		    		double price = selling.getSellPayableAmount();
					selling.setPayDate(date);
					if(price==total) {
						//saleTradeList = new TradeList();
						saleTradeList = FileHandler.retrieveSaleTrade();
						System.out.println(saleTradeList.toString());
						//saleTradeList.getTradeProperty(prop);
						saleTradeList.setTrade(selling);
						prop.setStatus(false);
						FileHandler.saveProperty(sellList);;
						messageArea.setText("Payment successful"+selling.toString());
						FileHandler.saveSaleTrade(saleTradeList);
						System.out.println("saved sale transaction");
						totalAmount.clear();
						custEmail.clear();
						custName.clear();
						tradePropertyAddress.clear();
						
					}else {
						messageArea.setText("Sorry, Amount does not match");
					}
				}else {
					renting.setPropertyPrice(total);
					double price = renting.getRentPayableAmount();
					renting.setPayDate(date);
					if(price==total) {
						//rentTradeList = new TradeList();
						rentTradeList = FileHandler.retrieveSaleTrade();
						rentTradeList.setTrade(selling);
						prop.setStatus(false);
						FileHandler.saveRentalProperty(rentList);;
						messageArea.setText("Payment successful ");
						FileHandler.saveRentalTrade(rentTradeList);
						System.out.println("saved rent transaction");
						
						totalAmount.clear();
						custEmail.clear();
						custName.clear();
						tradePropertyAddress.clear();
						tradeDate.setValue(null);
						
					}else {
						messageArea.setText("Sorry, Amount does not match");
					}
				}
			}catch(NumberFormatException e) {
				messageArea.setText("Please enter amount in numbers");
			}
	    	
	    }
    	
	}

  //button listener for going back to previous page
    public void backButtonListener(ActionEvent e) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("firstPage.fxml"));

		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();

	}

}
