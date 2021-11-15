package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationPageController {
	
	 @FXML
	    private TextField nameField;

	    @FXML
	    private TextField numberField;

	    @FXML
	    private TextField addressField;
	    
	    @FXML
	    private TextField emailField;

	    @FXML
	    private TextArea custDispArea;

	    @FXML
	    private Button addDataButton;

	    @FXML
	    private Button backtoHomeButton;

	     @FXML
	    private Button showCustomersButton;
	    
	    private Customer customer;
	    private CustomerList lists;
	    
	    
	    //button listener function for goig back to first page
	    public void backtoHomeButtonListener(ActionEvent e) throws IOException {
	    	Parent parent = FXMLLoader.load(getClass().getResource("firstPage.fxml"));

			Scene scene = new Scene(parent);

			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

			stage.setScene(scene);
			stage.setTitle("Main Page");
			stage.show();
		}
	    
	    
	    //button listener for registering customers
	    public void registerCustomerButtonListener(ActionEvent ev) throws IOException, ClassNotFoundException{
	    	
    		String name = nameField.getText();
    		String num = numberField.getText();
    		String address = addressField.getText();
	    	String email = emailField.getText();
	    	
    		if(name.length()==0 || num.length()==0 || address.length()==0 || email.length()==0) {
    			custDispArea.setText("All inputs must be entered");
    		}else {
    			try {
    				Long number = Long.parseLong(num);
    				
    				customer = new Customer(name, number, address, email);
    		    	//lists = new CustomerList();
    		    	lists = FileHandler.retrieveCustomer();
    		    	Customer cust1 = lists.getCustomerWithEmail(email);
    		    	if(cust1==null) {
	    				lists.addCustomer(customer);
	    				FileHandler.saveCustomer(lists);
	    				custDispArea.setText("Customer added to the file"+lists.toString());
	    				System.out.println("hi written list is ");
	    				System.out.println(lists);
	    				nameField.clear();
	    				numberField.clear();
	    				addressField.clear();
	    				emailField.clear();
    		    	}else {
    		    		custDispArea.setText("Email already exist");
    		    	}
    				
    			}catch(NumberFormatException e) {
    				custDispArea.setText("Phonenumber not in format");
    			}
		    	
		    	
    		}
		    	
			
		}
	    
	    
	  //button listener for showing all customers
	    public void showCustomerButtonListener(ActionEvent e) throws IOException, ClassNotFoundException {
	    	lists = FileHandler.retrieveCustomer();
	    	System.out.println(lists);
	    	custDispArea.setText(lists.toString());
			
		}

}
