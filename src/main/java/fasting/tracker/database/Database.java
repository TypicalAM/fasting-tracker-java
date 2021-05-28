package fasting.tracker.database;

import java.sql.*;
import java.util.Date;

import org.apache.derby.tools.sysinfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Database {
	static Connection conn;
	String JDBC_URL;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData metadata;

	public int connectionToDerby() throws ClassNotFoundException {
//		System.out.println("connectionToDerby");
		final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		JDBC_URL = "jdbc:derby:Internal";
		Class.forName(DRIVER);
		try {
			conn = DriverManager.getConnection(JDBC_URL);
//			System.out.println(conn);

		} catch (Exception e) {
//			System.out.println(e.toString());
			return 0;
		}
		return 1;
	}

	public void Insert(String date, double weight, int calories, int weightmoved, String meals) {
		// An insert function for the right pane at "Calendar"
		if (conn != null) {
			try {
				if (meals.charAt(0) == '3') {
					conn.createStatement()
							.execute("UPDATE Calendar SET DATE='" + date + "',WEIGHT=" + weight + ",CALORIES="
									+ calories + ",WEIGHTMOVED=" + weightmoved + ",MEALS='" + meals.substring(1)
									+ "' WHERE DATE = '" + date + "'");
				} else {
//					System.out.println("" + date + " " + weight + " " + calories + " " + weightmoved + " " + meals);
					conn.createStatement()
							.execute("INSERT INTO Calendar (ID, DATE, WEIGHT, CALORIES, WEIGHTMOVED,MEALS) VALUES "
									+ "(DEFAULT,'" + date + "'," + weight + "," + calories + "," + weightmoved + ",'"
									+ meals + "')");
				}
//				System.out.println("Inserted");
				// Here polymorphism is used to make the original Insert method to insert all of
				// the data without any exceptions
				// as in the calendar tab
			} catch (SQLException e) {
//				System.out.println(e.toString());
			}
		}
	}

	public void InsertMeals(String name, double weight, double protein, double fats) {
		// An insert function for the right pane at "Calendar"
		if (conn != null) {
			try {
				conn.createStatement().execute("INSERT INTO Meals (ID, NAME, CARBS, PROTEIN, FATS) VALUES "
						+ "(DEFAULT,'" + name + "'," + weight + "," + protein + "," + fats + ")");
				// Here polymorphism is used to make the original Insert method to insert all of
				// the data without any exceptions
				// as in the calendar tab
			} catch (SQLException e) {
//				System.out.println(e.toString());
			}
		}
	}

	public void InsertExercises(String name, String category, int sets, int reps) {
		// An insert function for the right pane at "Calendar"
//		System.out.println(conn);
		if (conn != null) {
			try {
				conn.createStatement().execute("INSERT INTO EXERCISES (ID, NAME, CATEGORY, SETS, reps) VALUES "
						+ "(DEFAULT,'" + name + "','" + category + "'," + sets+ "," + reps+ ")");
				// Here polymorphism is used to make the original Insert method to insert all of
				// the data without any exceptions
				// as in the calendar tab
			} catch (SQLException e) {
//				System.out.println(e.toString());
			}
		}
	}

	public void InsertGeneral(String table, String name, int int1, int int2, int int3) {
		if (conn != null) {
			try {
				conn.createStatement().execute("INSERT INTO " + table + " VALUES " + "(DEFAULT,'" + name + "','" + int1
						+ "','" + int2 + "','" + int3 + "')");
//				System.out.println("Inserted");
			} catch (SQLException e) {
//				System.out.println(e.toString());
			}
		}
	}

	public void Print(String table) {
		if (conn != null) {
			try {

				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM " + table);
				metadata = rs.getMetaData();
				int columncount = metadata.getColumnCount();
				for (int x = 1; x <= columncount; x++)
					System.out.print("" + metadata.getColumnName(x) + " | ");
				while (rs.next()) {
//					System.out.println("");
					for (int x = 1; x <= columncount; x++)
						System.out.print("" + rs.getString(x) + " | ");
				}
			} catch (Exception e) {
//				System.out.println(e.toString());
			}
		}
	}

	public String Query(String table, String date, String value) {
		String temp9 = new String();
//		System.out.println(conn);
		if (conn != null) {
			if (!date.contains("ID")) {
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT " + value + " FROM " + table + " WHERE DATE = '" + date + "'");
					metadata = rs.getMetaData();
					int columncount = metadata.getColumnCount();
//						System.out.print("" + metadata.getColumnName(x) + " | ");
					while (rs.next()) {
//						System.out.println("");
						for (int y = 1; y <= columncount; y++) {
							temp9 = rs.getString(y);
						}
					}
//					System.out.println(temp9);
				} catch (Exception e) {
//					System.out.println(e.toString());
				}
				if (temp9 != null) {
					return temp9;
				} else {
					return "No Data";
				}
			} else {
				// IT DOES CONATAIN ID
				try {
					stmt = conn.createStatement();
//					System.out.println("SELECT "+ date.substring(2)+" FROM " + table + " WHERE ID =" + value + "");
					rs = stmt.executeQuery("SELECT "+ date.substring(2)+" FROM " + table + " WHERE ID =" + value + "");
					metadata = rs.getMetaData();
					int columncount = metadata.getColumnCount();
					if (rs.next()) {
						for (int y = 1; y <= columncount; y++) {
							temp9 = rs.getString(y);
//							System.out.println(temp9);
						}
					} else {
						return "No";
					}
				} catch (Exception e) {
//					System.out.println(e.toString());
				}
				if (temp9 != null) {
					return temp9;
				} else {
					return "No Data";
				}
			}
		} else {
			return "null";
		}
		// TODO THIS PROBABLY ISN'T USEFUL IT JUST STAYS FOR THE POLYMORPH
	}

	public String Fast(String Date) {
		if (conn != null) {
			try {
				String tempo = new String();
				int fastingtype[] = new int[3];
				ResultSet temp = conn.createStatement()
						.executeQuery("SELECT (FASTINGTYPE) from CALENDAR where DATE='" + Date + "'");
				// THERE SHOULD BE NO MORE THAN ONE DATE HERE
				while (temp.next()) {
					tempo = tempo + (temp.getString("FASTINGTYPE"));
				}
				if (tempo.contains("3")) {
					;
					return "3";
					// SIXTEEN EIGHT FAST
				} else {
					fastingtype[0] = Integer.parseInt(String.valueOf(tempo.charAt(0)));
					fastingtype[1] = Integer.parseInt(String.valueOf(tempo.charAt(1)));
					SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
					Date tempdate = form.parse(Date);
//					System.out.println(tempo);
//					System.out.println(fastingtype[0]);
//					System.out.println(tempdate.toString().substring(0, 3));
					if (fastingtype[0] == 1) {
						fastingtype[2] = Integer.parseInt(String.valueOf(tempo.charAt(2)));
						// This case takes into account the 5/2 scenario
					}
					boolean tempn = false;
					if (fastingtype[0] != 1) {

						switch (tempdate.toString().substring(0, 3)) {
						case "Mon": {
							tempn = true;
						}
						case "Wed": {
							tempn = true;
						}
						case "Fri": {
							tempn = true;
						}
						}
						if (fastingtype[1] == 1) {
							return "2 " + Boolean.toString(tempn);
						} else {
							return "2 " + Boolean.toString(!tempn);
						}
					} else {
						switch (tempdate.toString().substring(0, 3)) {

						case "Mon": {
							if ((fastingtype[1] == 1) || (fastingtype[2] == 1)) {
								tempn = true;
							}
						}
						case "Tue": {
							if ((fastingtype[1] == 2) || (fastingtype[2] == 2)) {
								tempn = true;
							}
						}
						case "Wed": {
							if ((fastingtype[1] == 3) || (fastingtype[2] == 3)) {
								tempn = true;
							}
						}
						case "Thu": {
							if ((fastingtype[1] == 4) || (fastingtype[2] == 4)) {
								tempn = true;
							}
						}
						case "Fri": {
							if ((fastingtype[1] == 5) || (fastingtype[2] == 5)) {
								tempn = true;
							}
						}
						case "Sat": {
							if ((fastingtype[1] == 6) || (fastingtype[2] == 6)) {
								tempn = true;
							}
						}
						case "Sun": {
							if ((fastingtype[1] == 7) || (fastingtype[2] == 7)) {
								tempn = true;
							}
						}

						}
						return "" + fastingtype[0] + " " + tempn;
					}

				}

			} catch (Exception e) {
//				System.out.println(e.toString());
			}
		}

		return "nothin";
	}
}
