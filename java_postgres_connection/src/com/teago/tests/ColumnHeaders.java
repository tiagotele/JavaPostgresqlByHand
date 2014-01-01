package com.teago.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColumnHeaders {
public static void main(String[] args) {
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String password = "admin";
		String url = "jdbc:postgresql://localhost/teago";
		String user = "postgres";

        try {

            con = DriverManager.getConnection(url, user, password);
            String query = "SELECT name, title From authors, " +
               "books WHERE authors.id=books.author_id";
            pst = con.prepareStatement(query);

            rs = pst.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();

            String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);

            Formatter fmt1 = new Formatter();
            fmt1.format("%-21s%s", colname1, colname2);
            System.out.println(fmt1);

            while (rs.next()) {
                Formatter fmt2 = new Formatter();
                fmt2.format("%-21s", rs.getString(1));
                System.out.print(fmt2);
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ColumnHeaders.class.getName());
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
                
                Logger lgr = Logger.getLogger(ColumnHeaders.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
