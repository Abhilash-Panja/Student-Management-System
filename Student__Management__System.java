import java.sql.*;
import java.util.Scanner;
public class Student__Management__System {
    // Database Connection Setup
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sms"; // Replace with your database URL
    private static final String USER = "root";                               // Replace with your MySQL username
    private static final String PASS = "password";                           // Replace with your MySQL password

    // Method to connect to the database
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to add a new student
    public static void addStudent(int id, String name, int age, String gender, String contact, String address) {
        Connection conn = connect();
        String query = "INSERT INTO students (student_id, name, age, gender, contact, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, gender);
            stmt.setString(5, contact);
            stmt.setString(6, address);
            stmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to add a new course
    public static void addCourse(int id, String name, int credits) {
        Connection conn = connect();
        String query = "INSERT INTO courses (course_id, course_name, credits) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, credits);
            stmt.executeUpdate();
            System.out.println("Course added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to enroll a student into a course
    public static void enrollStudent(int studentId, int courseId, String grade) {
        Connection conn = connect();
        String query = "INSERT INTO enrollments (student_id, course_id, grade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setString(3, grade);
            stmt.executeUpdate();
            System.out.println("Student enrolled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view a student's details
    public static void viewStudent(int id) {
        Connection conn = connect();
        String query = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("student_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Contact: " + rs.getString("contact"));
                System.out.println("Address: " + rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view course details
    public static void viewCourse(int id) {
        Connection conn = connect();
        String query = "SELECT * FROM courses WHERE course_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Course ID: " + rs.getInt("course_id"));
                System.out.println("Course Name: " + rs.getString("course_name"));
                System.out.println("Credits: " + rs.getInt("credits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all enrollments of a student
    public static void viewEnrollments(int studentId) {
        Connection conn = connect();
        String query = "SELECT * FROM enrollments INNER JOIN courses ON enrollments.course_id = courses.course_id WHERE student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Courses Enrolled By Student ID: " + studentId);
            while (rs.next()) {
                System.out.println("Course: " + rs.getString("course_name") + " | Grade: " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method with options for user interaction
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Student Details");
            System.out.println("5. View Course Details");
            System.out.println("6. View Student Enrollments");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Add student
                    System.out.print("Enter student ID: ");
                    int studentId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Enter address: ");
                    String address = sc.nextLine();
                    addStudent(studentId, name, age, gender, contact, address);
                    break;
                case 2:
                    // Add course
                    System.out.print("Enter course ID: ");
                    int courseId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter course name: ");
                    String courseName = sc.nextLine();
                    System.out.print("Enter credits: ");
                    int credits = sc.nextInt();
                    addCourse(courseId, courseName, credits);
                    break;
                case 3:
                    // Enroll student
                    System.out.print("Enter student ID: ");
                    int enrollStudentId = sc.nextInt();
                    System.out.print("Enter course ID: ");
                    int enrollCourseId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    enrollStudent(enrollStudentId, enrollCourseId, grade);
                    break;
                case 4:
                    // View student details
                    System.out.print("Enter student ID: ");
                    int viewStudentId = sc.nextInt();
                    viewStudent(viewStudentId);
                    break;
                case 5:
                    // View course details
                    System.out.print("Enter course ID: ");
                    int viewCourseId = sc.nextInt();
                    viewCourse(viewCourseId);
                    break;
                case 6:
                    // View student enrollments
                    System.out.print("Enter student ID: ");
                    int viewEnrollmentsStudentId = sc.nextInt();
                    viewEnrollments(viewEnrollmentsStudentId);
                    break;
                case 7:
                    // Exit
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
