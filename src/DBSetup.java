package db;

import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {

    public static void createTables() {
        String createStudents = "CREATE TABLE IF NOT EXISTS students (" +
                "id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "email VARCHAR(100) UNIQUE NOT NULL, " +
                "date_of_birth DATE NOT NULL" +
                ");";

        String createCourses = "CREATE TABLE IF NOT EXISTS courses (" +
                "id SERIAL PRIMARY KEY, " +
                "course_name VARCHAR(100) UNIQUE NOT NULL, " +
                "course_description TEXT" +
                ");";

        String createMarks = "CREATE TABLE IF NOT EXISTS marks (" +
                "student_id INT NOT NULL, " +
                "course_id INT NOT NULL, " +
                "marks FLOAT NOT NULL, " +
                "PRIMARY KEY(student_id, course_id), " +
                "FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE, " +
                "FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE CASCADE" +
                ");";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createStudents);
            stmt.execute(createCourses);
            stmt.execute(createMarks);

            System.out.println("All tables created successfully (if they didn't exist).");

        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}