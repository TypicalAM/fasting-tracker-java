package fasting.tracker.controller;

import java.io.IOException;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.derby.tools.sysinfo;

import com.sun.javafx.scene.traversal.WeightedClosestCorner;

import fasting.tracker.database.Database;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {
//	@FXML private ImageView background;
	@FXML
	private BorderPane Main;
	@FXML
	private Button Home;
	@FXML
	private Button Calendar;
	@FXML
	private Button Exercises;
	@FXML
	private Button Meals;
	@FXML
	private URL location;
	@FXML
	private Label Clock;
	@FXML
	private ImageView Background;
	@FXML
	private GridPane Grid;
	@FXML
	private Button[] Buttons = new Button[35];
	@FXML
	private Button number1;
	@FXML
	private Button number2;
	@FXML
	private Button number3;
	@FXML
	private Button number4;
	@FXML
	private Button number5;
	@FXML
	private Button number6;
	@FXML
	private Button number7;
	@FXML
	private Button number8;
	@FXML
	private Button number9;
	@FXML
	private Button number10;
	@FXML
	private Button number11;
	@FXML
	private Button number12;
	@FXML
	private Button number13;
	@FXML
	private Button number14;
	@FXML
	private Button number15;
	@FXML
	private Button number16;
	@FXML
	private Button number17;
	@FXML
	private Button number18;
	@FXML
	private Button number19;
	@FXML
	private Button number20;
	@FXML
	private Button number21;
	@FXML
	private Button number22;
	@FXML
	private Button number23;
	@FXML
	private Button number24;
	@FXML
	private Button number25;
	@FXML
	private Button number26;
	@FXML
	private Button number27;
	@FXML
	private Button number28;
	@FXML
	private Button number29;
	@FXML
	private Button number30;
	@FXML
	private Button number31;
	@FXML
	private Button number32;
	@FXML
	private Button number33;
	@FXML
	private Button number34;
	@FXML
	private Button number35;
	@FXML
	private Button number291;
	@FXML
	private Button number2911;
	@FXML
	private Button number29111;
	@FXML
	private Button number291111;
	@FXML
	private Button number2911111;
	@FXML
	private Button number29111111;
	@FXML
	private Button number291111111;
	@FXML
	private Button SaveChanges;
	@FXML
	private Text Month;
	@FXML
	private Text Fasting;
	@FXML
	private TextField WeightField;
	@FXML
	private TextField CaloriesField;
	@FXML
	private TextField WeightMovedField;
	@FXML
	private TextField MealsField;
	@FXML
	private TextField DateField;
	@FXML

	private TextField NameField;
	@FXML
	private TextField CarbsField;
	@FXML
	private TextField ProteinField;
	@FXML
	private TextField FatsField;
	@FXML
	private TextField Name2Field;
	@FXML
	private TextField CategoryField;
	@FXML
	private TextField SetsField;
	@FXML
	private TextField RepsField;
	@FXML
	private Button SaveChanges2;
	@FXML
	private Button SaveChanges3;
	@FXML
	private ListView<Meals> listViewOfMeals;
	@FXML 
	private ListView<String> listViewOfMeals2;
	@FXML
	private ListView<String> listViewOfExercises;
	@FXML
	private ObservableList<Meals> MealsList;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat MonthFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
	Database base = new Database();
	String startingdate = new String();
	Button temp = null;
	String temp4 = null;
	String date1 = null;
	boolean temp10=false;
	int startdate;
	int month;
	int days;
	int year;

	@FXML

	void initialize() throws ParseException {
		String tempimg = new String();
		String temploc = location.toString();
		try {
			base.connectionToDerby();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// xXXXXXXXXXXXXXXX
//		base.Print("Calendar");
		if (temploc.contains("Home.fxml")) {
			Date date = new Date();
			String today = dateFormat.format(date);
			final Date evening;
			final Date morning;
			final Date tomorrow;
			final Date morning2;
			evening = new Date(dateFormat.parse(today).getTime() + (1000 * 60 * 60 * 20));
			morning = new Date(dateFormat.parse(today).getTime() + (1000 * 60 * 60 * 12));
			morning2 = new Date(dateFormat.parse(today).getTime() + (1000 * 60 * 60 * 36));
			tomorrow = new Date(dateFormat.parse(today).getTime() + ((1000 * 60 * 60 * 24) - 1000));
			// 20:00 the evening fast

			// That is one day ahead in case base.Fast(today).subString(2) produces true

			String temp = base.Fast(today);
			if (!temp.contains("nothin") || (!Boolean.getBoolean(temp.substring(2)))) {

				final String when;
				if (temp.charAt(0) == '3') {
					if (date.getTime() > evening.getTime()) {
						when = "morning1";
					} else if (date.getTime() < morning.getTime()) {
						when = "morning2";
					} else {
						when = "evening";
					}
				} else {
					when = "tomorrow";
				}
				// conn.Query("Calendar","Date")
				Thread timerThread = new Thread(() -> {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
					while (true) {
						try {
							Thread.sleep(1000); // 1 second
						} catch (Exception e) {
//							System.out.println(e.toString());
						}
						Platform.runLater(() -> {
							if (when == "evening") {
								final String time = simpleDateFormat
										.format(new Date(evening.getTime() - new Date().getTime())) + " LEFT TO EAT";
								Clock.setText(time);
							} else {
								if (when == "morning1") {
									final String time = simpleDateFormat.format(
											new Date(morning2.getTime() - new Date().getTime()) + " UNTIL EATING");
									Clock.setText(time);
								} else {
									if (when == "morning2") {
										final String time = simpleDateFormat.format(
												new Date(morning.getTime() - new Date().getTime()) + " UNTIL EATING");
										Clock.setText(time);
									} else {
										final String time = simpleDateFormat.format(
												new Date(tomorrow.getTime() - new Date().getTime())) + " UNTIL EATING";
										Clock.setText(time);
									}
								}
							}

						});
					}
				});
				timerThread.start();
			} else {
				if (Boolean.getBoolean(temp.substring(2))) {
					Clock.setText("No fasting today!");
				} else {
					Clock.setText("No Data for today");
				}
			}
		} else if (temploc.contains("Calendar.fxml")) {
			// CALENDAR

			SwitchMonth(dateFormat.format(new Date()), 0);

		} else if (temploc.contains("Meals.fxml")) {
			if (temp10) {
			int x = 1;
//			System.out.println();
			MealsList = FXCollections.observableArrayList();
			while (!base.Query("Meals", "ID", Integer.toString(x)).equals("No")) {
//				System.out.println("Setting a MealsList element " + base.Query("Meals", "ID", Integer.toString(x))
//						+ ", " + Integer.toString(x));
				MealsList.add(new Meals(base.Query("Meals", "ID", Integer.toString(x)), Integer.toString(x)));
				x++;
			}
			listViewOfMeals = new ListView<>(MealsList);

			listViewOfMeals.setCellFactory(param -> new ListCell<Meals>() {
				@Override
				protected void updateItem(Meals item, boolean empty) {
//					System.out.println("Update item");
					super.updateItem(item, empty);

					if (empty || item == null || item.getMeals() == null) {
						setText("");
					} else {
						setText(item.getMeals());
					}
				}
			});
			} else {
				int x = 1;
				while (!base.Query("Meals", "IDNAME", Integer.toString(x)).equals("No")) {
				listViewOfMeals2.getItems().add("  \t "+" \t "+" \t "+" \t "+" -- "+base.Query("Meals", "IDNAME", Integer.toString(x))+" -- ");
				listViewOfMeals2.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Meals", "IDCARBS", Integer.toString(x)));
				listViewOfMeals2.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Meals", "IDPROTEIN", Integer.toString(x)));
				listViewOfMeals2.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Meals", "IDFATS", Integer.toString(x)));	
				x++;
				}
				listViewOfMeals2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
		} else if (temploc.contains("Exercises.fxml")) {
			
			int x = 1;

			while (!base.Query("Exercises", "IDNAME", Integer.toString(x)).equals("No")) {
			listViewOfExercises.getItems().add("  \t "+" \t "+" \t "+" \t "+" -- "+ base.Query("Exercises", "IDNAME", Integer.toString(x))+" -- ");
			listViewOfExercises.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Exercises", "IDCATEGORY", Integer.toString(x)));
			listViewOfExercises.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Exercises", "IDSETS", Integer.toString(x)));
			listViewOfExercises.getItems().add("\t "+"\t "+"\t "+"\t "+"\t "+base.Query("Exercises", "IDREPS", Integer.toString(x)));	
			x++;
			}
			listViewOfExercises.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		}
	}

	public void SwitchMonth(String dat, int operation) throws ParseException {
		Date endingdata;
		if (operation == 0) {
			// Just setting up the view
			dat = dat.substring(0, 8) + "01";
			if (dat.substring(5, 6) == "0") {
				month = Integer.parseInt(dat.substring(6, 7));
			} else {
				month = Integer.parseInt(dat.substring(5, 7));
			}
			endingdata = dateFormat.parse(dat);
		} else if (operation == 1) {
			boolean addyear = false;
			boolean addzero = false;
			// 1 Month behind
			if (dat.substring(5, 6) == "0") {
				month = Integer.parseInt(dat.substring(6, 7));
				addzero = true;
				if (month == 1) {
					month = 12;
					addyear = true;
					addzero = false;
				} else {
					month--;
				}

			} else {
				month = Integer.parseInt(dat.substring(5, 7));
				month--;
			}
			if (!addyear) {
				if (addzero) {
					endingdata = dateFormat.parse(dat.substring(0, 5) + "0" + month + dat.substring(8));
				} else {
					endingdata = dateFormat.parse(dat.substring(0, 5) + month + dat.substring(8));
				}
			} else {
				if (addzero) {
					endingdata = dateFormat
							.parse("" + (Integer.parseInt(dat.substring(0, 4)) - 1) + "-12" + dat.substring(8));
				} else {
					endingdata = dateFormat
							.parse("" + (Integer.parseInt(dat.substring(0, 4)) - 1) + "-" + month + dat.substring(8));
				}
			}
			addzero = false;
			addyear = false;
		} else {

			boolean addyear = false;
			boolean addzero = false;
			// 1 Month behind
			if (dat.substring(5, 6) == "0") {
				month = Integer.parseInt(dat.substring(6, 7));
				addzero = true;
				if (month == 9) {
					month = 10;
					addzero = false;
				} else {
					month++;
				}
			} else {
				month = Integer.parseInt(dat.substring(5, 7));
				if (month == 12) {
					month = 01;
					addyear = true;
				} else {
					month++;
				}
			}
			if (!addyear) {
				if (addzero) {
					endingdata = dateFormat.parse(dat.substring(0, 5) + "0" + month + dat.substring(8));
				} else {
					endingdata = dateFormat.parse(dat.substring(0, 5) + month + dat.substring(8));
				}
			} else {
				if (addzero) {
					endingdata = dateFormat
							.parse("" + (Integer.parseInt(dat.substring(0, 4)) + 1) + "-01" + dat.substring(8));
				} else {
					endingdata = dateFormat
							.parse("" + (Integer.parseInt(dat.substring(0, 4)) + 1) + "-01" + dat.substring(8));
				}
			}
			addyear = false;
			addzero = false;
		}
//		System.out.println("year " + dat);
		year = Integer.parseInt(dat.substring(0, 4));
		Month.setText(MonthFormat.format(endingdata));

//		System.out.println(endingdata.toString().substring(0, 3));
		if (endingdata.toString().contains("Mon"))
			startdate = 1;
		if (endingdata.toString().contains("Tue"))
			startdate = 2;
		if (endingdata.toString().contains("Wed"))
			startdate = 3;
		if (endingdata.toString().contains("Thu"))
			startdate = 4;
		if (endingdata.toString().contains("Fri"))
			startdate = 5;
		if (endingdata.toString().contains("Sat"))
			startdate = 6;
		if (endingdata.toString().contains("Sun"))
			startdate = 7;

		if (month == 1)
			days = 31;
		else if (month == 2)
			days = 28;
		else if (month == 3)
			days = 31;
		else if (month == 4)
			days = 30;
		else if (month == 5)
			days = 31;
		else if (month == 6)
			days = 30;
		else if (month == 7)
			days = 31;
		else if (month == 8)
			days = 31;
		else if (month == 9)
			days = 30;
		else if (month == 10)
			days = 31;
		else if (month == 11)
			days = 30;
		else if (month == 12)
			days = 31;

//		System.out.println(startdate+" "+days+" "+month+" "+endingdata);

		int data = 1;
		for (int x = 1; x < startdate; x++) {
			((Button) Grid.getChildren().get(x + 6)).setVisible(false);
		}
		for (int x = startdate; x < days + startdate; x++) {
			((Button) Grid.getChildren().get(x + 6)).setText("" + data);
			data++;
		}
		for (int x = days + startdate; x <= 42; x++) {
			((Button) Grid.getChildren().get(x + 6)).setVisible(false);
		}
		data = 1;

		// syso

//		
//		DATE I czy miesiac do przydou czy do tylu
//		DATE MIESIAC DO PRZODU PIERWSZY DZIEN I 

		// }
	}

	public void SaveChanges2() {
		boolean badvalue3 = false;
		Double FatsValue = 0.0;
		Double ProteinValue = 0.0;
		Double CarbsValue = 0.0;
		try {
			FatsValue = Double.parseDouble(FatsField.getText());
			CarbsValue = Double.parseDouble(CarbsField.getText());
			ProteinValue = Double.parseDouble(ProteinField.getText());

		} catch (Exception e3) {
			badvalue3 = true; // The values are not what was intended
		}
		if (!badvalue3) {
			base.InsertMeals(NameField.getText(), CarbsValue, ProteinValue, FatsValue);
			SaveChanges2.setStyle("-fx-background-color: #e086a0");
			SaveChanges2.setText("SAVED");
		} else {
			SaveChanges2.setText("Wrong data");
			SaveChanges2.setStyle("-fx-background-color: #e14548");
		}

	}

	public void SaveChanges3() {
		boolean badvalue3 = false;
		int sets = 0;
		int reps = 0;

		try {
			sets = Integer.parseInt(SetsField.getText());
			reps = Integer.parseInt(RepsField.getText());
		} catch (Exception e4) {
			e4.printStackTrace();
			badvalue3 = true; // The values are not what was intended
		}
		if (!badvalue3) {
			base.InsertExercises(Name2Field.getText(), CategoryField.getText(), sets, reps);
			SaveChanges3.setStyle("-fx-background-color: #e086a0");
			SaveChanges3.setText("SAVED");
		} else {
			SaveChanges3.setText("Wrong data");
			SaveChanges3.setStyle("-fx-background-color: #e14548");
		}

	}

	public void SaveChanges() {
		boolean badvalue = false;
		Double temp7 = 0.0;
		int temp5 = 0;
		int temp8 = 0;
		if (date1 != null && !Meals.getText().equals("No Data") && !WeightField.getText().equals("No Data")
				&& !CaloriesField.getText().equals("No Data") && !WeightMovedField.getText().equals("No Data")
				&& !WeightField.getText().equals("No Data")) {
			try {
				temp7 = Double.parseDouble(WeightField.getText());
				temp5 = Integer.parseInt(CaloriesField.getText());
				temp8 = Integer.parseInt(WeightMovedField.getText());
			} catch (Exception e) {
				if (!e.toString().contains("NumberFormatException")) {
//					System.out.println(e.toString());
				} else {
					badvalue = true; // The values are not what was intended
				}
			}
			if (!badvalue) {
				if ((base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "DATE"))
						.equals(base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "DATE"))) {
					// The date needs to be altered instead of insterted
					base.Insert(Integer.toString(year) + "-" + temp4 + "-" + date1, temp7, temp5, temp8,
							"3" + MealsField.getText());
				} else {
					base.Insert(Integer.toString(year) + "-" + temp4 + "-" + date1, temp7, temp5, temp8,
							MealsField.getText());
				}
				SaveChanges.setStyle("-fx-background-color: #e086a0");
				SaveChanges.setText("SAVED");
				// Values are ready to be injected into the database
			} else {
				SaveChanges.setText("Wrong data");
				SaveChanges.setStyle("-fx-background-color: #e14548");
			}
		} else {
			SaveChanges.setText("Data missing");
			SaveChanges.setStyle("-fx-background-color: #e14548");
		}
		badvalue = false;
	}

	public void onCalendar(ActionEvent e) throws ParseException {
		if (temp != null) {
			temp.setStyle("-fx-background-color: #FFFFFF");
		}
		SaveChanges.setStyle("-fx-background-color: #e68370");
		SaveChanges.setText("SAVE CHANGES");
		Fasting.setText("Fast");
		WeightField.setText("Weight");
		CaloriesField.setText("Calories");
		WeightMovedField.setText("Weight Moved");
		MealsField.setText("Meals");

		if (e.toString().contains("number")) {
			// A date was clicked meaning that days and startingdate was defined
			temp = (Button) e.getSource();
			((Button) e.getSource()).setStyle("-fx-background-color: #e086a0");
			date1 = ((Button) e.getSource()).getText();
			try {
				date1.charAt(1);
			} catch (Exception e2) {
				date1 = "0" + date1;
			}

			try {
				Integer.toString(month).charAt(1);
			} catch (Exception e2) {
				temp4 = "0" + month;
			}
			if (temp4.charAt(0) != '0')
				temp4 = Integer.toString(month);
//			System.out.println("\n" + date1);
//			System.out.println(temp4);
//			System.out.println("Year " + year);
			if (base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Weight") != "null") {
				WeightField
						.setText(base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Weight"));
			} else {
				WeightField.setText("No Data");
			}
			if (base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Calories") != "null") {
				CaloriesField.setText(
						base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Calories"));
			} else {
				CaloriesField.setText("No Data");
			}
//			System.out
//					.println(base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "WeightMoved"));
			if (!base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "WeightMoved")
					.contains("No")) {
				WeightMovedField.setText(
						base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "WeightMoved"));
			} else {
				WeightMovedField.setText("No Data");
			}
			if (base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Meals") != "null") {
				MealsField.setText(base.Query("Calendar", Integer.toString(year) + "-" + temp4 + "-" + date1, "Meals"));
			} else {
				MealsField.setText("No Data");
			}
			String temp5 = (base.Fast(Integer.toString(year) + "-" + temp4 + "-" + date1));
//			System.out.println(Integer.toString(year) + "-" + temp4 + "-" + date1);
//			System.out.println(temp5);
			try {
				if (temp5.charAt(0) == '1') {
					Fasting.setText("5-2");
				} else if (temp5.charAt(0) == '2') {
					Fasting.setText("Alternate day");
				} else if (temp5.charAt(0) == '3') {
					Fasting.setText("16/8");
				} else {
					Fasting.setText("Skip day");
				}
			} catch (Exception e3) {
			}
		}
	}

	public void onaction(ActionEvent e) throws IOException {
		String temp = new String();
		try {
			if (e.toString().contains("Home")) {
				temp = "/FXML/Home.fxml";
			} else if (e.toString().contains("Calendar")) {
				temp = "/FXML/Calendar.fxml";
			} else if (e.toString().contains("Exercises")) {
				temp = "/FXML/Exercises.fxml";
			} else {
				temp = "/FXML/Meals.fxml";
			}
		} catch (Exception e1) {
//			System.out.println(e1.toString());
		}
		Parent root = FXMLLoader.load(this.getClass().getResource(temp));
		Stage window = (Stage) Home.getScene().getWindow();
		window.setScene(new Scene(root));
		window.show();
	}

}
