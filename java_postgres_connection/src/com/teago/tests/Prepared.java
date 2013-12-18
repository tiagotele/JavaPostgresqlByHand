package com.teago.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prepared {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;

		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";
		String password = "1234";

		try {
			int id = 7;
			String author = "Paulo Coelho";
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO authors(id, name) values(?,?)";
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.setString(2, author);
			pst.executeUpdate();

		} catch (SQLException e) {
			Logger lgr = Logger.getLogger(Prepared.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				Logger lgr = Logger.getLogger(Prepared.class.getName());
				lgr.log(Level.SEVERE, e2.getMessage(), e2);
			}
		}
		
		System.out.println("It works fine! Take a bier and go to next step!");

	}

}
