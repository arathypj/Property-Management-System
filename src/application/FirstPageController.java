package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FirstPageController {
	
	@FXML
    private Button propertySaleButton;

    @FXML
    private Button propertyRentButton;

    @FXML
    private Button registerCustomerButton;

    
    //listener function for the new scene of dealing customers
	public void registerButtonListener(ActionEvent e) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("registration.fxml"));

		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.setTitle("Customer Details");
		stage.show();
	}
	
	//listener function for the new scene which deals with the sale/rent properties
	public void propertySaleButtonListener(ActionEvent e) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("saleDetailsPage.fxml"));

		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.setTitle("Property details");
		stage.show();
	}
	

	
	//listener function for the new scene which deals with payment and invoice
	public void propertyRentButtonListener(ActionEvent e) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("sellPage.fxml"));

		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.setTitle("New Sale/Rent");
		stage.show();
	}
	
		
}
