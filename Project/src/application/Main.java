package application;
	
import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * @author Jonathan Mejia
 * @author Zaquariah Holland
 * @author
 * 
 * UTSA CS 3443 - Team Project
 * Fall 2020
 *
 */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException{
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}