package fasting.tracker.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseCreation extends Database {
	Connection conn;
	String JDBC_URL;

	public int connectionToDerby() throws ClassNotFoundException {
//		System.out.println("connectionToDerby");
		final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		JDBC_URL = "jdbc:derby:Internal";
		Class.forName(DRIVER);
		try {
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (Exception e) {
			if (e.toString().contains("not found")) {
				try {
					if (Creation()) {
						// Created tables
						return 1;
					}
				} catch (Exception f) {
					if (f.toString().contains("AppClassLoader")) {
						// The database was already started
						JOptionPane.showMessageDialog(null, "Close any instances of the application");
						System.exit(0);
					} else {
					}
					
					return 0;
				}
			}
			if (e.toString().contains("AppClassLoader")) {
				// The database was already started
				JOptionPane.showMessageDialog(null, "Please kill the database using the task manager");
			}
//			System.out.println(e.toString());
			return 0;
		}
//		System.out.println(conn);
		return 2;
	}

	// CONNECTING TO THE DATABASE
	public boolean Creation() throws ClassNotFoundException, SQLException {
		JDBC_URL = "jdbc:derby:Internal";
		conn = DriverManager.getConnection(JDBC_URL + ";create=true");
		if (conn != null) {
			conn.createStatement()
					.execute("CREATE TABLE Calendar (" + "ID int generated always as identity, " + "DATE Varchar(10), " + "FastingType INT, "
							+ "Weight FLOAT, "+ "Calories INT, " + "WeightMoved INT, " + "Meals VARCHAR(50))");
			conn.createStatement()
			.execute("CREATE TABLE Meals (" + "ID int generated always as identity, " + "Name Varchar(50), " + "Carbs DOUBLE, "
					+ "Protein DOUBLE, "+ "Fats DOUBLE)");
			conn.createStatement()
			.execute("CREATE TABLE Exercises (" + "ID int generated always as identity, " + "Name Varchar(50), " + "Category Varchar(50), "
					+ "Sets DOUBLE, "+ "Reps DOUBLE)");
			return true;
		} else {
			return false;
		}
	}

	public void Insert(String date, int fastingtype, double weightvalue, int calorievalue) throws SQLException {
		// This function will insert only the first setup values for the application
		// Polymorphism proved to be useful here
		// System.out.println(conn);
		if (conn != null) {
			//VALUES NEED TO BE CHANGE
//			if (fastingtype==3) {
//				
//			}
//				System.out.println(""+date+" "+fastingtype+" "+weightvalue+" "+calorievalue);
//				conn.createStatement().execute("INSERT INTO Meals(ID, NAME, CARBS, PROTEIN, FATS) VALUES (DEFAULT, 'Dupa', 2.0, 3.0, 10.2)");
				conn.createStatement().execute("INSERT INTO Calendar(ID, DATE, FASTINGTYPE, WEIGHT, CALORIES) VALUES (DEFAULT, '"
						+ date + "'," + fastingtype + "," + weightvalue + "," + calorievalue+")");
//			conn.createStatement().execute("DELETE FROM MEALS WHERE ID BETWEEN 1 AND 69");
		
				//The values are inserted
		}
	}
	public String Query(String date) {
		if (conn != null) {

			Statement stmt;
			String temp=new String();
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT DATE FROM CALENDAR");
//				System.out.print("" + metadata.getColumnName(0) + "");
				while (rs.next()) {
					temp=temp+" "+rs.getString("DATE");
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				System.out.println(e.toString());
			}
//			System.out.println("Cycled");
			if (temp.contains(date)) {
				return "true";
			} else {
				return temp;
			}
		}
		return "temp";

	}
	
	public void Connection() throws SQLException {
		conn.close();
	}

	public DatabaseCreation() throws ClassNotFoundException, SQLException {
//		System.out.println("Created an instance of DatabaseCreation");
	}

}
