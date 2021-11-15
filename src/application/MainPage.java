package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPage extends Application{
	
	public void start(Stage stage) throws Exception{
		
		Parent parent = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
		
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
