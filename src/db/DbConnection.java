package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    //Rule 01
        private static DbConnection dbConnection;

        private Connection connection;

    //Rule 02

        private DbConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduManager","root","1234");
        }

    //Rule 03
        public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
            if (dbConnection==null){
                dbConnection=new DbConnection();
            }
            return dbConnection;
        }
        public Connection getConnection(){
            return connection;
        }
}
