package model;

public class Student extends People {
    private String studentNumber;
    private String university;
    private int academicYear;
    private double point;
    private AcademicPerformance academicPerformance;

    public Student(String studentNumber, String university, int academicYear, double point) {
        this.studentNumber = studentNumber;
        this.university = university;
        this.academicYear = academicYear;
        this.setPoint(point); // Use setter to update academicPerformance
    }

    public Student(long id, String name, int yearOfBirth, String address, Double height, Double weight,
                   String studentNumber, String university, int academicYear, double point) {
        super(id, name, yearOfBirth, address, height, weight);
        this.studentNumber = studentNumber;
        this.university = university;
        this.academicYear = academicYear;
        this.setPoint(point); // Use setter to update academicPerformance
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
        this.academicPerformance = AcademicPerformance.calculate(point);
    }

    public AcademicPerformance getAcademicPerformance() {
        return academicPerformance;
    }

    @Override
    public String toString() {
        return String.format("| %-8d | %-20s | %-12s | %-20s | %-8.1f | %-8.1f | %-12s | %-20s | %-12d | %-6.2f | %-12s |",
                getId(), getName(), getYear(), getAddress(), getHeight(), getWeight(),
                studentNumber, university, academicYear, point, academicPerformance.getDescription());
    }
}