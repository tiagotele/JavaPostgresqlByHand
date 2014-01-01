package com.teago.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListTables {
	
public static void main(String[] args) {
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String password = "admin";
		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";
 
        try {

            con = DriverManager.getConnection(url, user, password);
            String query = "SELECT table_name FROM information_schema.tables "
                    + "WHERE table_schema = 'public'";
            pst = con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ListTables.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                
                Logger lgr = Logger.getLogger(ListTables.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }

}
