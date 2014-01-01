package com.teago.tests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadImage {

	 public static void main(String[] args) {

	        Connection con = null;
	        PreparedStatement pst = null;
	        FileOutputStream fos = null;

	        String password = "admin";
			String url = "jdbc:postgresql://localhost/teago";
			String user = "postgres";

	        try {

	            con = DriverManager.getConnection(url, user, password);

	            String query = "SELECT data, LENGTH(data) FROM images WHERE id = 1";
	            pst = con.prepareStatement(query);

	            ResultSet result = pst.executeQuery();
	            result.next();

	            fos = new FileOutputStream("woman2.jpg");

	            int len = result.getInt(2);
	            byte[] buf = result.getBytes("data");
	            fos.write(buf, 0, len);


	        } catch (IOException | SQLException ex) {
	            Logger lgr = Logger.getLogger(ReadImage.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);

	        } finally {

	            try {
	                if (pst != null) {
	                    pst.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	                if (fos != null) {
	                    fos.close();
	                }

	            } catch (IOException | SQLException ex) {
	                Logger lgr = Logger.getLogger(ReadImage.class.getName());
	                lgr.log(Level.WARNING, ex.getMessage(), ex);

	            }
	        }
	        
	        System.out.println("");
	    }
	 
	 
	
}
