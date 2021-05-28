package fasting.tracker.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fasting.tracker.database.Database;
import fasting.tracker.database.DatabaseCreation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button Calculate;
	@FXML
	private Button FiveTwo;
	@FXML
	private Button Alt;
	@FXML
	private Button SixteenEight;
	@FXML
	private TextField Weight;
	@FXML
	private TextField Height;
	@FXML
	private TextField Age;
	@FXML
	private TextField Calories;
	@FXML
	private RadioButton F;
	@FXML
	private RadioButton M;
	@FXML
	private RadioButton Button1;
	@FXML
	private RadioButton Button2;
	@FXML
	private RadioButton Button3;
	@FXML
	private RadioButton Button4;
	@FXML
	private RadioButton Button5;
	@FXML
	private RadioButton Button6;
	@FXML
	private RadioButton Button7;
	@FXML
	private Button SaveChanges;
	private int fastingtype = 10;
	private double weightvalue;
	private double heightvalue;
	private int agevalue;
	private int calorievalue;
	boolean badvalue = false;
	int tempt=0;
	int temp1=0;
	int temp2=0;
	
	public LoginController() {
//		System.out.println("A constructor was used");
	}

	@FXML
	void initialize() {
//		System.out.println("Initialized");
	}

	@FXML
	public void Calculate() {
		// A method which calculates the Optimal calorie intake for a person
		// A value which signifies that the text is not what it should be
		weightvalue = 0;
		heightvalue = 0;
		agevalue = 0;
		badvalue = false;

		try {
//			System.out.println("Calculate");
			weightvalue = Double.parseDouble(Weight.getText());
			heightvalue = Double.parseDouble(Height.getText());
			agevalue = Integer.parseInt(Age.getText());
		} catch (Exception e) {
			if (!e.toString().contains("NumberFormatException")) {
//				System.out.println(e.toString());
			} else {
				badvalue = true; // The values are not what was intended
			}
		}
		if (!(F.isSelected()) && !(M.isSelected()) || badvalue || weightvalue > 250 || weightvalue < 30 || agevalue > 99
				|| agevalue < 10 || heightvalue > 200 || heightvalue < 130) {
			Calculate.setText("WRONG VALUES, TRY AGAIN");

			Calculate.setStyle("-fx-background-color: #e14548");
		} else {
			badvalue = false;
			Calculate.setStyle("-fx-background-color: #e086a0");
			if (F.isSelected()) {
				Calculate.setText("RECOMMENDED INTAKE: "
						+ (1155 + (9.6 * weightvalue) + (1.8 * heightvalue) - (4.7 * agevalue)) + " CAL");
			} else if (M.isSelected()) {
				Calculate.setText("RECOMMENDED INTAKE: "
						+ (566 + (13.7 * weightvalue) + (5 * heightvalue) - (6.8 * agevalue)) + " CAL");
			}
		}
	}

	public void OnRadio() {
		if (F.isSelected()) {
			M.setSelected(false);
		}
		if (M.isSelected()) {
			F.setSelected(false);
		}
	}

	public void Choice(ActionEvent e) {
		try {
			if (e.toString().contains("FiveTwo")) {
				fastingtype = 1;
				FiveTwo.setStyle("-fx-background-color: #e086a0");
				Alt.setStyle("-fx-background-color: #e68370");
				SixteenEight.setStyle("-fx-background-color: #e68370");
			} else if (e.toString().contains("Alt")) {
				fastingtype = 21;
				FiveTwo.setStyle("-fx-background-color: #e68370");
				Alt.setStyle("-fx-background-color: #e086a0");
				SixteenEight.setStyle("-fx-background-color: #e68370");
			} else {
				fastingtype = 3;
				FiveTwo.setStyle("-fx-background-color: #e68370");
				Alt.setStyle("-fx-background-color: #e68370");
				SixteenEight.setStyle("-fx-background-color: #e086a0");
			}
			if (fastingtype==1) {
				FiveTwo.setVisible(false);
				Alt.setVisible(false);
				SixteenEight.setVisible(false);
				Button1.setVisible(true);
				Button2.setVisible(true);
				Button3.setVisible(true);
				Button4.setVisible(true);
				Button5.setVisible(true);
				Button6.setVisible(true);
				Button7.setVisible(true);
			}
			
		} catch (Exception e1) {
//			System.out.println(e1.toString());
		}
	}

	public void Days() {
		tempt=0;
		if (Button1.isSelected()) {temp1=1; tempt++;}
		if (Button2.isSelected()) {temp1=2; tempt++;}
		if (Button3.isSelected()) {temp1=3; tempt++;}
		if (Button4.isSelected()) {temp1=4; tempt++;}
		if (Button5.isSelected()) {temp1=5; tempt++;}
		if (Button6.isSelected()) {temp1=6; tempt++;}
		if (Button7.isSelected()) {temp1=7; tempt++;}
		if (tempt==2) {
				if (temp1!=1&&Button1.isSelected()) temp2=1; 
				if (temp1!=2&&Button2.isSelected()) temp2=2; 
				if (temp1!=3&&Button3.isSelected()) temp2=3; 
				if (temp1!=4&&Button4.isSelected()) temp2=4; 
				if (temp1!=5&&Button5.isSelected()) temp2=5; 
				if (temp1!=6&&Button6.isSelected()) temp2=6; 
				if (temp1!=7&&Button7.isSelected()) temp2=7; 
		}
	}
	public void SaveChanges() throws ClassNotFoundException, SQLException {
		badvalue = false;
		Calculate();
		if (!badvalue) {
			try {
				calorievalue = Integer.parseInt(Calories.getText());
			} catch (Exception e) {
				if (!(e.toString().contains("NumberFormatException"))&&(calorievalue>6000||calorievalue<500)) {
//					System.out.println(e.toString());
				} else {
					badvalue = true; // The values are not what was intended
					SaveChanges.setText("WRONG VALUES, TRY AGAIN");
					SaveChanges.setStyle("-fx-background-color: #e14548");
				}
			}
		}
		if (fastingtype==1) {
			if (tempt!=2) {
				SaveChanges.setText("WRONG VALUES, TRY AGAIN");
				SaveChanges.setStyle("-fx-background-color: #e14548");
				badvalue=true;
			} else {
				fastingtype = Integer.parseInt(("1"+Integer.toString(temp1)+Integer.toString(temp2)));
//				System.out.println(fastingtype);
						//PARSE TEH NUMBER AND CHANGE TE RADIO TO A STRING VALUE
			}
			
		}
		if (!badvalue&&fastingtype!=10) {

			Calculate.setStyle("-fx-background-color: #e086a0");
			SaveChanges.setStyle("-fx-background-color: #e086a0");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				// Inputting starting values into the database
				DatabaseCreation startingvalues = new DatabaseCreation();
				startingvalues.connectionToDerby();
				if (startingvalues.Query((String) dateFormat.format(date)).contains("true")) {
					SaveChanges.setText("THE DATE ALREADY EXISTS");
					SaveChanges.setStyle("-fx-background-color: #e14548");
				} else {
					Date temp=date;
					for (int x=1; x<15;x++) {
//						System.out.println((String) dateFormat.format(temp)+", "+fastingtype+", "+weightvalue+", "+ calorievalue);
						startingvalues.Insert((String) dateFormat.format(temp), fastingtype, weightvalue, calorievalue);
						temp=new Date(temp.getTime()+1000*24*60*60);
					}
			}
					Parent root = FXMLLoader.load(this.getClass().getResource("/FXML/Home.fxml"));
					Stage window = (Stage) SaveChanges.getScene().getWindow();
					window.setTitle("Home");
					window.setScene(new Scene(root));
					window.show();
//				}
			} catch (Exception e) {
//				System.out.println(e.toString());
			}
			
			
			// Writing the values to the database
		}
	}
}
