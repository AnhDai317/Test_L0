package view;

import controller.StudentManager;
import model.AcademicPerformance;
import model.Student;
import utils.Constants;
import utils.Validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class StudentView {
    private final Validation validation;
    private final StudentManager studentManager;

    public StudentView(StudentManager studentManager) {
        this.validation = new Validation();
        this.studentManager = studentManager;
    }

    // Display main menu
    public int displayMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add new student");
        System.out.println("2. Display all students");
        System.out.println("3. Find student by ID");
        System.out.println("4. Update student by ID");
        System.out.println("5. Delete student by ID");
        System.out.println("6. Display academic performance statistics");
        System.out.println("7. Display GPA distribution");
        System.out.println("8. Display students by academic performance");
        System.out.println("9. Exit");
        return Validation.getInt("Enter your choice (1-9): ", 1, 9);
    }

    // Get student information
    public Student getStudentInput() {
        String name;
        while (true) {
            name = Validation.getString("Enter student name: ");
            if (validation.isValidName(name)) break;
        }

        String studentNumber;
        while (true) {
            studentNumber = Validation.getString("Enter student number: ");
            if (!validation.isValidStudentNumber(studentNumber)) continue;
            if (studentManager.isDuplicate(studentNumber)) {
                System.err.println("Student number already exists.");
                continue;
            }
            break;
        }

        String address;
        while (true) {
            address = Validation.getString("Enter address: ");
            if (validation.isValidAddress(address)) break;
        }

        double height;
        while (true) {
            height = Validation.getDouble("Enter height (cm): ", Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
            if (validation.isValidHeight(height)) break;
        }

        double weight;
        while (true) {
            weight = Validation.getDouble("Enter weight (kg): ", Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
            if (validation.isValidWeight(weight)) break;
        }

        int year;
        while (true) {
            year = Validation.getInt("Enter year of birth: ", Constants.MIN_YEAR, Constants.MAX_YEAR);
            if (validation.isValidYear(year)) break;
        }

        String university;
        while (true) {
            university = Validation.getString("Enter university: ");
            if (!university.trim().isEmpty()) break;
            System.err.println("University must not be empty.");
        }

        int academicYear;
        while (true) {
            academicYear = Validation.getInt("Enter academic year: ", Constants.MIN_YEAR, Constants.MAX_YEAR);
            if (academicYear >= Constants.MIN_YEAR && academicYear <= Constants.MAX_YEAR) break;
            System.err.println("Academic year must be between " + Constants.MIN_YEAR + " and " + Constants.MAX_YEAR + ".");
        }

        double point;
        while (true) {
            point = Validation.getDouble("Enter GPA (0.0-10.0): ", 0.0, 10.0);
            if (point >= 0.0 && point <= 10.0) break;
            System.err.println("GPA must be between 0.0 and 10.0.");
        }

        return new Student(0, name, year, address, height, weight, studentNumber, university, academicYear, point);
    }

    // Display all students
    public void displayAllStudents() {
        if (studentManager.getCount() == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n=== List of Students ===");
        System.out.println(getTableHeader());
        System.out.println(new String(new char[150]).replace('\0', '-'));
        for (Student student : studentManager.getStudents()) {
            System.out.println(student.toString());
        }
        System.out.println(new String(new char[150]).replace('\0', '-'));
    }

    // Find and display student by ID
    public void findStudentById() {
        long id = Validation.getInt("Enter student ID: ", 1, Integer.MAX_VALUE);
        Student student = studentManager.findStudentById(id);
        if (student != null) {
            System.out.println("\n=== Student Found ===");
            System.out.println(getTableHeader());
            System.out.println(new String(new char[150]).replace('\0', '-'));
            System.out.println(student.toString());
            System.out.println(new String(new char[150]).replace('\0', '-'));
        }
    }

    // Update student by ID with selective fields
    public void updateStudentById() {
        long id = Validation.getInt("Enter student ID to update: ", 1, Integer.MAX_VALUE);
        Student existingStudent = studentManager.findStudentById(id);
        if (existingStudent == null) {
            return;
        }

        System.out.println("\n=== Student Found ===");
        System.out.println(getTableHeader());
        System.out.println(new String(new char[150]).replace('\0', '-'));
        System.out.println(existingStudent.toString());
        System.out.println(new String(new char[150]).replace('\0', '-'));

        Student newStudent = new Student(
                existingStudent.getId(),
                existingStudent.getName(),
                existingStudent.getYear(),
                existingStudent.getAddress(),
                existingStudent.getHeight(),
                existingStudent.getWeight(),
                existingStudent.getStudentNumber(),
                existingStudent.getUniversity(),
                existingStudent.getAcademicYear(),
                existingStudent.getPoint()
        );

        while (true) {
            System.out.println("\nSelect field to update:");
            System.out.println("1. Name");
            System.out.println("2. Student Number");
            System.out.println("3. Address");
            System.out.println("4. Height");
            System.out.println("5. Weight");
            System.out.println("6. Year of Birth");
            System.out.println("7. University");
            System.out.println("8. Academic Year");
            System.out.println("9. GPA");
            System.out.println("10. Finish updating");
            int choice = Validation.getInt("Enter choice (1-10): ", 1, 10);

            switch (choice) {
                case 1:
                    String name;
                    while (true) {
                        name = Validation.getString("Enter new name: ");
                        if (validation.isValidName(name)) break;
                    }
                    newStudent.setName(name);
                    break;
                case 2:
                    String studentNumber;
                    while (true) {
                        studentNumber = Validation.getString("Enter new student number: ");
                        if (!validation.isValidStudentNumber(studentNumber)) continue;
                        if (!studentNumber.equalsIgnoreCase(existingStudent.getStudentNumber())
                                && studentManager.isDuplicate(studentNumber)) {
                            System.err.println("Student number already exists.");
                            continue;
                        }
                        break;
                    }
                    newStudent.setStudentNumber(studentNumber);
                    break;
                case 3:
                    String address;
                    while (true) {
                        address = Validation.getString("Enter new address: ");
                        if (validation.isValidAddress(address)) break;
                    }
                    newStudent.setAddress(address);
                    break;
                case 4:
                    double height;
                    while (true) {
                        height = Validation.getDouble("Enter new height (cm): ", Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
                        if (validation.isValidHeight(height)) break;
                    }
                    newStudent.setHeight(height);
                    break;
                case 5:
                    double weight;
                    while (true) {
                        weight = Validation.getDouble("Enter new weight (kg): ", Constants.MIN_WEIGHT, Constants.MAX_HEIGHT);
                        if (validation.isValidWeight(weight)) break;
                    }
                    newStudent.setWeight(weight);
                    break;
                case 6:
                    int year;
                    while (true) {
                        year = Validation.getInt("Enter new year of birth: ", Constants.MIN_YEAR, Constants.MAX_YEAR);
                        if (validation.isValidYear(year)) break;
                    }
                    newStudent.setYear(year);
                    break;
                case 7:
                    String university;
                    while (true) {
                        university = Validation.getString("Enter new university: ");
                        if (!university.trim().isEmpty()) break;
                        System.err.println("University must not be empty.");
                    }
                    newStudent.setUniversity(university);
                    break;
                case 8:
                    int academicYear;
                    while (true) {
                        academicYear = Validation.getInt("Enter new academic year: ", Constants.MIN_YEAR, Constants.MAX_YEAR);
                        if (academicYear >= Constants.MIN_YEAR && academicYear <= Constants.MAX_YEAR) break;
                        System.err.println("Academic year must be between " + Constants.MIN_YEAR + " and " + Constants.MAX_YEAR + ".");
                    }
                    newStudent.setAcademicYear(academicYear);
                    break;
                case 9:
                    double point;
                    while (true) {
                        point = Validation.getDouble("Enter new GPA (0.0-10.0): ", 0.0, 10.0);
                        if (point >= 0.0 && point <= 10.0) break;
                        System.err.println("GPA must be between 0.0 and 10.0.");
                    }
                    newStudent.setPoint(point);
                    break;
                case 10:
                    studentManager.updateStudent(id, newStudent);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Delete student by ID
    public void deleteStudentById() {
        long id = Validation.getInt("Enter student ID to delete: ", 1, Integer.MAX_VALUE);
        studentManager.deleteStudent(id);
    }

    // Display academic performance statistics
    public void displayAcademicPerformanceStats() {
        if (studentManager.getCount() == 0) {
            System.out.println("No students available.");
            return;
        }

        Map<AcademicPerformance, Integer> stats = new HashMap<>();
        for (AcademicPerformance perf : AcademicPerformance.values()) {
            stats.put(perf, 0);
        }

        for (Student student : studentManager.getStudents()) {
            AcademicPerformance perf = student.getAcademicPerformance();
            stats.put(perf, stats.get(perf) + 1);
        }

        System.out.println("\n=== Academic Performance Statistics ===");
        System.out.println(String.format("| %-12s | %-6s |", "Performance", "Percent"));
        System.out.println(new String(new char[22]).replace('\0', '-'));
        AcademicPerformance[] sortedPerf = AcademicPerformance.values();
        Arrays.sort(sortedPerf, (p1, p2) -> p2.ordinal() - p1.ordinal());
        for (AcademicPerformance perf : sortedPerf) {
            double percent = (stats.get(perf) * 100.0) / studentManager.getCount();
            System.out.println(String.format("| %-12s | %-6.1f%% |", perf.getDescription(), percent));
        }
        System.out.println(new String(new char[22]).replace('\0', '-'));
    }

    // Display GPA distribution
    public void displayGpaDistribution() {
        if (studentManager.getCount() == 0) {
            System.out.println("No students available.");
            return;
        }

        Map<Double, Integer> gpaCount = new HashMap<>();
        for (Student student : studentManager.getStudents()) {
            double gpa = student.getPoint();
            gpaCount.put(gpa, gpaCount.getOrDefault(gpa, 0) + 1);
        }

        System.out.println("\n=== GPA Distribution ===");
        System.out.println(String.format("| %-6s | %-6s |", "GPA", "Percent"));
        System.out.println(new String(new char[16]).replace('\0', '-'));
        for (Map.Entry<Double, Integer> entry : gpaCount.entrySet()) {
            double percent = (entry.getValue() * 100.0) / studentManager.getCount();
            System.out.println(String.format("| %-6.2f | %-6.1f%% |", entry.getKey(), percent));
        }
        System.out.println(new String(new char[16]).replace('\0', '-'));
    }

    // Display students by academic performance
    public void displayStudentsByPerformance() {
        System.out.println("\nAvailable academic performances:");
        for (AcademicPerformance perf : AcademicPerformance.values()) {
            System.out.println("- " + perf.getDescription());
        }
        String input = Validation.getString("Enter academic performance: ").trim().toLowerCase();
        AcademicPerformance targetPerf = null;
        for (AcademicPerformance perf : AcademicPerformance.values()) {
            if (perf.getDescription().toLowerCase().equals(input)) {
                targetPerf = perf;
                break;
            }
        }

        if (targetPerf == null) {
            System.err.println("Invalid academic performance.");
            return;
        }

        boolean found = false;
        System.out.println("\n=== Students with " + targetPerf.getDescription() + " Performance ===");
        System.out.println(getTableHeader());
        System.out.println(new String(new char[150]).replace('\0', '-'));
        for (Student student : studentManager.getStudents()) {
            if (student.getAcademicPerformance() == targetPerf) {
                System.out.println(student.toString());
                found = true;
            }
        }
        System.out.println(new String(new char[150]).replace('\0', '-'));
        if (!found) {
            System.out.println("No students found with " + targetPerf.getDescription() + " performance.");
        }
    }

    // Get table header
    private String getTableHeader() {
        return String.format("| %-8s | %-20s | %-12s | %-20s | %-8s | %-8s | %-12s | %-20s | %-12s | %-6s | %-12s |",
                "ID", "Name", "Year", "Address", "Height", "Weight", "Student No", "University", "Academic Yr", "GPA", "Performance");
    }

    // Run the view
    public void run() {
        while (true) {
            int choice = displayMenu();
            switch (choice) {
                case 1:
                    Student student = getStudentInput();
                    studentManager.addStudent(student);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    findStudentById();
                    break;
                case 4:
                    updateStudentById();
                    break;
                case 5:
                    deleteStudentById();
                    break;
                case 6:
                    displayAcademicPerformanceStats();
                    break;
                case 7:
                    displayGpaDistribution();
                    break;
                case 8:
                    displayStudentsByPerformance();
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}