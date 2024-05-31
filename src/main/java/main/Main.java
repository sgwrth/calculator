package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	public final String STAGE_TITLE = "CALCUTRON 9001";
	public final int STAGE_WIDTH = 400;
	public final int STAGE_HEIGHT = 600;
	public final int STAGE_POS_X = 150;
	public final int STAGE_POS_Y = 150;
	public final boolean STAGE_IS_RESIZABLE = false;
	public final String STAGE_ICON = "/icon.png";
	public final String CALC_NAME_FONT = "Verdana";
	public final String CALC_NAME = "Calcutron 9001";
	public final int CALC_NAME_FONT_SIZE = 24;
	public final int CALC_NAME_POS_X = 105;
	public final int CALC_NAME_POS_Y = 40;
	public final int CALC_NAME_LINE_WIDTH = 190;
	public final int CALC_NAME_LINE_OFFSET = 8;
	public final int CALC_NAME_LINE_THICKNESS = 2;
	public final double CALC_NAME_LINE_OPACITY = 0.5;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			Group group = new Group();

			Text title = new Text();
			title.setText(CALC_NAME);
			title.setX(CALC_NAME_POS_X);
			title.setY(CALC_NAME_POS_Y);
			title.setFont(Font.font(CALC_NAME_FONT, CALC_NAME_FONT_SIZE));
			title.setFill(Color.WHITE);

			Line line = new Line();
			line.setStartX(title.getX());
			line.setStrokeWidth(CALC_NAME_LINE_THICKNESS);
			line.setStroke(Color.WHITE);
			line.setEndX(title.getX() + CALC_NAME_LINE_WIDTH);
			line.setStartY(title.getY() + CALC_NAME_LINE_OFFSET);
			line.setEndY(title.getY() + CALC_NAME_LINE_OFFSET);
			line.setOpacity(CALC_NAME_LINE_OPACITY);

			Rectangle rectLeft = new Rectangle();
			rectLeft.setX(20);
			rectLeft.setY(20);
			rectLeft.setWidth(48);
			rectLeft.setHeight(48);
			rectLeft.setFill(Color.GREY);
			rectLeft.setStrokeWidth(12);
			rectLeft.setStroke(Color.DARKGREY);

			Rectangle rectRight = new Rectangle();
			rectRight.setX(STAGE_WIDTH - 68);
			rectRight.setY(20);
			rectRight.setWidth(48);
			rectRight.setHeight(48);
			rectRight.setFill(Color.GREY);
			rectRight.setStrokeWidth(12);
			rectRight.setStroke(Color.DARKGREY);

			group.getChildren().add(title);
			group.getChildren().add(line);
			group.getChildren().add(rectLeft);
			group.getChildren().add(rectRight);

			Scene scene = new Scene(group, Color.DARKBLUE);

			// FXML
			Parent parent = FXMLLoader
					.load(getClass().getResource("/calc.fxml"));
			Scene fxmlScene = new Scene(parent, Color.DARKBLUE);

			stage.setTitle(STAGE_TITLE);
			Image icon = new Image(getClass()
					.getResource(STAGE_ICON)
					.toURI()
					.toString());
			stage.getIcons().add(icon);
			// stage.setWidth(STAGE_WIDTH);
			// stage.setHeight(STAGE_HEIGHT);
			stage.setResizable(STAGE_IS_RESIZABLE);
			stage.setX(STAGE_POS_X);
			stage.setY(STAGE_POS_Y);

			stage.setScene(fxmlScene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
