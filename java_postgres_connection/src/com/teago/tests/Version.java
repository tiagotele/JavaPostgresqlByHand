package com.teago.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Version {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String password = "admin";
		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";

		try 
		{
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT VERSION()");
			
			if(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) 
		{
			Logger lgr = Logger.getLogger(Version.class.getName());
			lgr.log( Level.SEVERE , e.getMessage() , e);
		}
		finally
		{
			try {
				if(rs!= null)
				{
					rs.close();
				}
				
				if(st!= null)
				{
					st.close();
				}
				
				if(rs!= null)
				{
					con.close();
				}
			} catch (Exception e2) {
				Logger lgr = Logger.getLogger(Version.class.getName());
				lgr.log( Level.WARNING , e2.getMessage() , e2);
			}
			
		}
	}

}
