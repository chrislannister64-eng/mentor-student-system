package util;

import java.sql.*;

public class DBInitializer {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/mentor_student_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASS = "123qwe!@#"; // change this

    public static void initialize() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement st = conn.createStatement()) {

            // Create database
            st.execute("CREATE DATABASE IF NOT EXISTS mentor_student_db");
            st.execute("USE mentor_student_db");

            // Users
            st.execute("""
                CREATE TABLE IF NOT EXISTS users(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100),
                    password VARCHAR(100),
                    role VARCHAR(50)
                )
            """);

            // Courses
            st.execute("""
                CREATE TABLE IF NOT EXISTS courses(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    description VARCHAR(255)
                )
            """);

            // Sessions
            st.execute("""
                CREATE TABLE IF NOT EXISTS sessions(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    mentor_id INT,
                    course_id INT,
                    session_date DATE
                )
            """);

            // Attendance
            st.execute("""
                CREATE TABLE IF NOT EXISTS attendance(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    session_id INT,
                    student_id INT,
                    status VARCHAR(50)
                )
            """);

            // Notes
            st.execute("""
                CREATE TABLE IF NOT EXISTS notes(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    session_id INT,
                    student_id INT,
                    note_text TEXT
                )
            """);

            // Insert demo users
            st.execute("""
                INSERT IGNORE INTO users(id,name,email,password,role) VALUES
                (1,'Alice Mentor','alice@school.com','mentor123','Mentor'),
                (2,'Bob Student','bob@student.com','student123','Student')
            """);

            System.out.println("Database initialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database initialization failed.");
        }
    }
}
