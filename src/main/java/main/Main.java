package main;

import java.io.File;
import java.net.URI;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.*;
import model.Calculator;
import view.CalculatorView;

public class Main extends Application {

	public final String STAGE_TITLE = "Calculator";

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			// BorderPane root = new BorderPane();
			// Scene scene = new Scene(root,400,400);
			Group root = new Group();
			Scene scene = new Scene(root,Color.BLUE);
			stage.setTitle(STAGE_TITLE);
			Image icon = new Image(getClass().getResource("/icon.png").toURI().toString());
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

/*
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new CalculatorController(
                        new CalculatorView(),
                        new Calculator()
                )
        );
    }

}
*/
