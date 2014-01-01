package com.teago.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotPrepared {

	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;

		String password = "admin";
		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";

		try {

			con = DriverManager.getConnection(url, user, password);

			st = con.createStatement();

			for (int i = 1; i <= 1000; i++) {
				String query = "INSERT INTO Testing(Id) VALUES(" + 2 * i + ")";
				st.executeUpdate(query);
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(NotPrepared.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(NotPrepared.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		
		System.out.println("Done! No my DB is full");
	}

}
