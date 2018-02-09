package connectDB;

import java.sql.*;

public class Connect {
    public Connection conn;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String DB_URL = "jdbc:mysql://localhost/";
    public String DB_URL_TABLE = "jdbc:mysql://localhost/DZIEKANAT";
    public String USER = "root";
    public String PASS = "";
    public String sqlRecord = "";

    public void connect1() {
        conn = null;
        preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = conn.prepareStatement(sqlRecord);
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } catch (ClassNotFoundException e1) {
            System.out.println("Error: " + e1);
        }
    }

    public void connect() {
        conn = null;
        preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL_TABLE, USER, PASS);
            preparedStatement = conn.prepareStatement(sqlRecord);
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } catch (ClassNotFoundException e1) {
            System.out.println("Error: " + e1);
        }
    }

    public void disconnet() {
        try {
            if (preparedStatement != null)
                conn.close();
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
    }

}
