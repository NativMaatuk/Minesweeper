package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();//i need to fix
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Game.fxml"));
			//this is the fix 
			VBox  root = loader.load();
			Scene scene = new Scene(root);
			root.setMinSize(500, 0);
			
			//start creat copy of controller
			Controller controller = loader.getController();
			//set focus on out | out == label== enter values:
			controller.getStart().requestFocus();
			controller.setStage(primaryStage);
			
			//try to edit stage :(
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setTitle("Minesweeper");
			//end
			
			// set padding to all the program
			//root.setPadding(new Insets(0));
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//add img icon to the program
			Image img = new Image(getClass().getClassLoader().getResource("Logo.png").toString());
			primaryStage.getIcons().add(img);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
