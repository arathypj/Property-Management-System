package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaleDetailsController {
	
	@FXML
    private Button addPropertyButton;

	@FXML
    private ComboBox<String> sellOrRent;

    @FXML
    private ComboBox<String> propertyTypeCombobox;

    @FXML
    private ComboBox<String> houseTypeCombo;
    
    @FXML
    private TextArea propertyAddress;

    @FXML
    private TextField propertyArea;

    @FXML
    private TextField noOfBedrooms;

    @FXML
    private TextField noOfBathrooms;

    @FXML
    private TextField propertyPrice;

    @FXML
    private Button displayPropertiesButton;
    
    @FXML
    private Button availablePropertyButton;

    @FXML
    private TextArea displayArea;

    @FXML
    private Button searchButton;

    @FXML
    private Button goBackButton;
    
    private Property property;
    private PropertyList rentList;
    private PropertyList sellList;
    private List<Property> searchSalePropertyList;
    private List<Property> searchRentPropertyList;
    private String selectedOption="Sell";
	private String selectedPropertyType="";
	private String selectedHouseType="";
	private String houseTypeForSearch;
	
	
	// method for initialising the combobox and their listeners 
	public void initialize() throws ClassNotFoundException, IOException {
		
		sellOrRent.getItems().addAll("Sell", "Rent");
    	propertyTypeCombobox.getItems().addAll("Flat", "House", "Bungalow");
    	houseTypeCombo.getItems().addAll("Detached", "Semidetached", "Terraced");
    	houseTypeCombo.setDisable(true);
    	addPropertyButton.setDisable(true);
    	sellOrRent.valueProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		    	selectedOption = newValue;
		    		addPropertyButton.setDisable(false);
		            System.out.println("Selected Type: "+ selectedOption);
		            
		        }    
		    });
    	propertyTypeCombobox.valueProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		        	    selectedPropertyType = newValue;
		        	    System.out.println("Selected: "+ selected);
		        	    System.out.println("Old Value: "+ oldValue);
		            System.out.println("Selected Property Type: "+ selectedPropertyType);
		            if(selectedPropertyType=="House") {
		            	houseTypeCombo.setDisable(false);
		            	houseTypeCombo.valueProperty().addListener(new ChangeListener<String>() {
		        		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
		        		    	selectedHouseType = newValue;
		        		        	    System.out.println("Selected: "+ selected);
		        		        	    System.out.println("Old Value: "+ oldValue);
		        		            System.out.println("Selected Property Type: "+ selectedHouseType);
		        		            
		        		        }    
		        		    });
		            }
		            else {
		            	houseTypeCombo.setDisable(true);;
		            }
		        }    
		    });
    }
    
    
	
	//button listener for adding property for sale and rent
    public void addPropertyButtonListener(ActionEvent ev) throws IOException, ClassNotFoundException {
    	
    	String address = propertyAddress.getText();
    	String rooms = noOfBedrooms.getText();
    	String baths = noOfBathrooms.getText();
    	String area = propertyArea.getText();
    	String price = propertyPrice.getText();
    	if(address.length()==0 || rooms.length()==0 || baths.length()==0 || area.length()==0 || price.length()==0) {
    		displayArea.setText("All inputs must be entered");
    	}else {
    		property = new Property();
			property.setPropertytype(selectedPropertyType);
			if(selectedPropertyType=="House") {
				property.setHousetype(selectedHouseType);
			}
			property.setAddress(address);
			property.setArea(area);
			try{
				Integer noRooms = Integer.parseInt(rooms);
				Integer noBaths = Integer.parseInt(baths);
				Integer priceAmount = Integer.parseInt(price);
				if(property.checkLimit(noRooms)) {
					property.setNoOfBedrooms(noRooms);
					property.setNoOfBathrooms(noBaths);
					property.setPrice(priceAmount);
				
		    	
					property.setStatus(true);
					
					
					if(selectedOption=="Sell") {
						//sellList = new PropertyList();
						sellList = FileHandler.retrieveProperty();
						Property prop1 = sellList.getPropertyByAddress(address);
						if(prop1==null) {
							sellList.addProperty(property);
							FileHandler.saveProperty(sellList);
							displayArea.setText("Property saved\n"+sellList);
							System.out.println("hi written list is ");
							System.out.println(sellList);
						}else {
							displayArea.setText("Property in this address already exist\n");
						}
					}else {
						//rentList = new PropertyList();
						rentList = FileHandler.retrieveRentalProperty();
						Property prop2 = sellList.getPropertyByAddress(address);
						if(prop2==null) {
							System.out.println("Rent file");
							rentList.addProperty(property);
							FileHandler.saveRentalProperty(rentList);
							displayArea.setText("Property saved\n"+rentList);
							System.out.println("hi written list is ");
							System.out.println(rentList);
						}else {
							displayArea.setText("Property in this address already exist\n");
						}
					}
			    	propertyAddress.clear();
					propertyArea.clear();
					noOfBedrooms.clear();
					noOfBathrooms.clear();
					propertyPrice.clear();
				}else {
					displayArea.setText("Number of bedrooms should be 1 to 5");
				}
				
			}catch(NumberFormatException e) {
				displayArea.setText("Number of bedrooms, bathrooms and price should be numbers");
			}
    	}
	}
    
    
    
    
    public void ComboBoxListeners() {		
//		propertyTypeCombobox.valueProperty().addListener(new ChangeListener<String>() {
//		    @Override public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
//		        	    selectedPropertyType = newValue;
//		        	    System.out.println("Selected: "+ selected);
//		        	    System.out.println("Old Value: "+ oldValue);
//		            System.out.println("Selected Property Type: "+ selectedPropertyType);
//		        }    
//		    });
		 
	}
    
    
  //button listener for searching property for sale and rent
    public void searchPropertyButtonListener(ActionEvent e) throws IOException, ClassNotFoundException {

    	String addr = propertyAddress.getText();
		String beds = noOfBedrooms.getText();
		String baths= noOfBathrooms.getText();
		String price = propertyPrice.getText();
		String area = propertyArea.getText();
		
		if(selectedPropertyType == "House") {
			houseTypeForSearch = String.valueOf(selectedHouseType);
		}else {
			houseTypeForSearch = String.valueOf("");
		}
		
		if(selectedOption=="Sell") {
			//sellList = new PropertyList();
			sellList = FileHandler.retrieveProperty();
			searchSalePropertyList = new ArrayList<Property>();
			System.out.println(sellList.getPropertyList());
			
			searchSalePropertyList =sellList.getFilterPropertyList(selectedPropertyType, houseTypeForSearch, addr, area, beds, baths, price);
			
			if(searchSalePropertyList.isEmpty()) {
				displayArea.setText("No property available with the search criteria");
				
			}else {
				displayArea.setText(searchSalePropertyList.toString());
			}
		}else {
			//rentList = new PropertyList();
			rentList = FileHandler.retrieveRentalProperty();
			searchRentPropertyList = new ArrayList<Property>();
			System.out.println("Search Rent result");
			System.out.println(selectedPropertyType);
			searchRentPropertyList =rentList.getFilterPropertyList(selectedPropertyType, houseTypeForSearch, addr, area, beds, baths, price);
			
			System.out.println("Search result");
			System.out.println(searchRentPropertyList);
			
			if(searchRentPropertyList.isEmpty()) {
				displayArea.setText("No property available with the search criteria");
				
			}else {
				displayArea.setText(searchRentPropertyList.toString());
			}
			
		}
		
	}
    
    
  //button listener for showing property for sale and rent
    public void showPropertiesButtonListener(ActionEvent e) throws IOException, ClassNotFoundException {

    	sellList = FileHandler.retrieveProperty();
    	rentList = FileHandler.retrieveRentalProperty();
    	
		displayArea.setText("Properties for sale\n"+sellList+" \nProperties for rent\n"+rentList);
		System.out.println("Retrieved list ");
	}
    
  //button listener for showing available property for sale and rent
    public void showAvailablePropListener(ActionEvent e) throws IOException, ClassNotFoundException {

    	sellList = FileHandler.retrieveProperty();
    	rentList = FileHandler.retrieveRentalProperty();
    	
    	sellList.getAvailablePropertyList();
    	rentList.getAvailablePropertyList();
		displayArea.setText("Properties for sale\n"+sellList+" \nProperties for rent\n"+rentList);
		System.out.println("Retrieved list ");
	}
    
   
  //button listener for going back to previous page
    public void goBackButtonListener(ActionEvent e) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("firstPage.fxml"));

		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();

	}

}
