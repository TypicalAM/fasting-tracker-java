package fasting.tracker;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fasting.tracker.controller.HomeController;
import fasting.tracker.database.Database;
import fasting.tracker.database.DatabaseCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
	static boolean create = false;
	Parent root;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		DatabaseCreation solution = new DatabaseCreation();
		if (solution.connectionToDerby()==1) create=true;
		launch(args);	
		System.exit(0);
	}

	@Override
	public void start(Stage args) throws Exception {
		if (create) {
			root = FXMLLoader.load(this.getClass().getResource("/FXML/Login.fxml"));
			args.setTitle("Setup");
		} else {
			root = FXMLLoader.load(this.getClass().getResource("/FXML/Home.fxml"));
			args.setTitle("Home");
		}
		args.setScene(new Scene(root));
		args.show();

	}
}
