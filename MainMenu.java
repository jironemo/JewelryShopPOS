
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainMenu.class.getResource("Dashboard.fxml"));
		Scene scene = new Scene(loader.load());
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene); 
		primaryStage.setTitle("Main Menu");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
