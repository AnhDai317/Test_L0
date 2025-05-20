package controller;

import model.Student;
import utils.Constants;
import utils.Validation;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class StudentManager {
    public static final int MAX_NUMBER_STUDENT = Constants.MAX_NUMBER_STUDENTS;
    private List<Student> students;
    private Validation validation = new Validation();

    // Constructor with choice of ArrayList or LinkedList
    public StudentManager(boolean useArrayList) {
        this.students = useArrayList ? new ArrayList<>() : new LinkedList<>();
    }

    // Add a new student (Create)
    public boolean addStudent(Student student) {
        if (students.size() >= MAX_NUMBER_STUDENT) {
            System.err.println("Reached maximum number of students");
            return false;
        }
        if (isDuplicate(student.getStudentNumber())) {
            System.err.println("Student number already exists");
            return false;
        }
        if (!validateStudent(student)) {
            return false;
        }
        students.add(student);
        System.out.println("Create student success");
        System.out.println(getTableHeader());
        System.out.println(student.toString());
        return true;
    }

    // Check if student number already exists
    public boolean isDuplicate(String studentNumber) {
        for (Student student : students) {
            if (student.getStudentNumber().equalsIgnoreCase(studentNumber)) {
                return true;
            }
        }
        return false;
    }

    // Validate student data using Validation class
    private boolean validateStudent(Student student) {
        if (!validation.isValidName(student.getName())) return false;
        if (!validation.isValidStudentNumber(student.getStudentNumber())) return false;
        if (!validation.isValidAddress(student.getAddress())) return false;
        if (!validation.isValidHeight(student.getHeight())) return false;
        if (!validation.isValidWeight(student.getWeight())) return false;
        if (!validation.isValidYear(student.getYear())) return false;
        if (student.getUniversity() == null || student.getUniversity().trim().isEmpty()) {
            System.err.println("University must not be empty.");
            return false;
        }
        if (student.getAcademicYear() < Constants.MIN_YEAR || student.getAcademicYear() > Constants.MAX_YEAR) {
            System.err.println("Academic year must be between " + Constants.MIN_YEAR + " and " + Constants.MAX_YEAR + ".");
            return false;
        }
        if (student.getPoint() < 0.0 || student.getPoint() > 10.0) {
            System.err.println("GPA must be between 0.0 and 10.0.");
            return false;
        }
        return true;
    }

    // Find student by ID (Read)
    public Student findStudentById(long id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        System.err.println("No student found with ID: " + id);
        return null;
    }

    // Update student by ID
    public boolean updateStudent(long id, Student newStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                if (!newStudent.getStudentNumber().equalsIgnoreCase(students.get(i).getStudentNumber())
                        && isDuplicate(newStudent.getStudentNumber())) {
                    System.err.println("Student number already exists");
                    return false;
                }
                if (!validateStudent(newStudent)) {
                    return false;
                }
                students.set(i, newStudent);
                System.out.println("Update student success");
                System.out.println(getTableHeader());
                System.out.println(newStudent.toString());
                return true;
            }
        }
        System.err.println("No student found with ID: " + id);
        return false;
    }

    // Delete student by ID
    public boolean deleteStudent(long id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                System.out.println("Delete student success");
                return true;
            }
        }
        System.err.println("No student found with ID: " + id);
        return false;
    }

    // Get table header for display
    private String getTableHeader() {
        return String.format("| %-8s | %-20s | %-12s | %-20s | %-8s | %-8s | %-12s | %-20s | %-12s | %-6s | %-12s |",
                "ID", "Name", "Year", "Address", "Height", "Weight", "Student No", "University", "Academic Yr", "GPA", "Performance");
    }

    // Get the current number of students
    public int getCount() {
        return students.size();
    }

    // Get the list of students
    public List<Student> getStudents() {
        return students;
    }
}