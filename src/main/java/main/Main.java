package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/calc.fxml"));
			Scene fxmlScene = new Scene(parent);
			stage.setScene(fxmlScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
