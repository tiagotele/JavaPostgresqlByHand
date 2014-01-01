package com.teago.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteImage {
	
	public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;
        FileInputStream fin = null;

        String password = "admin";
		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";               

        try {

            File img = new File("C:/Users/Tiago Melo/git/JavaPostgresqlByHand/java_postgres_connection/src/com/teago/tests/woman.png");
            fin = new FileInputStream(img);

            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO images(data) VALUES(?)");
            pst.setBinaryStream(1, fin, (int) img.length());
            pst.executeUpdate();

        } catch (FileNotFoundException | SQLException ex) {
            Logger lgr = Logger.getLogger(WriteImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
                if (fin != null) {
                    fin.close();
                }

            } catch (IOException | SQLException ex) {
                Logger lgr = Logger.getLogger(WriteImage.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);

            }
        }
        
        System.out.println("Image stored on db");
    }

}
