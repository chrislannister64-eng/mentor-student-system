package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DB_URL ="jdbc:mysql://localhost:3306/mentor_student_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    private static final String DB_USER = "root";
    private static final String DB_PASS = "123qwe!@#";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println(" Database connected");
            return conn;
        } catch (SQLException e) {
            System.out.println(" Database connection FAILED");
            e.printStackTrace();
            return null;


        }
    }
}


