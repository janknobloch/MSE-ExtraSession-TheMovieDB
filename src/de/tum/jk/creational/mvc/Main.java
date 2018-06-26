package de.tum.jk.creational.mvc;
import java.io.IOException;
import java.net.URISyntaxException;

import com.omertron.themoviedbapi.MovieDbException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//LOAD OUR XML VIEW SPECIFICATION
		AnchorPane root = FXMLLoader.load(Main.class.getResource("root.fxml"));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) throws MovieDbException, URISyntaxException, IOException {
		launch(args);
	}

}
