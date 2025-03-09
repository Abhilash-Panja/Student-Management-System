-- Table to store student information
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(10),
    contact VARCHAR(20),
    address VARCHAR(100)
);
INSERT INTO students (student_id, name, age, gender, contact, address)
VALUES (1, 'John Doe', 20, 'Male', '1234567890', '123 Main St');
-- Table to store course information
CREATE TABLE courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(50),
    credits INT
);
INSERT INTO courses (course_id, course_name, credits)
VALUES 
(101, 'Database Management Systems', 4),
(102, 'Operating Systems', 3),
(103, 'Data Structures', 4);

-- Table to store student-course enrollments
CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade VARCHAR(2),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
INSERT INTO enrollments (student_id, course_id, grade)
VALUES 
(1, 101, 'A'),   -- Student 1 enrolled in course 101
(1, 102, 'B'),   -- Student 1 enrolled in course 102
(2, 103, 'A'),   -- Student 2 enrolled in course 103
(3, 101, 'C');   -- Student 3 enrolled in course 101

