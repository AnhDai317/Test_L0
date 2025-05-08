package model;

import java.time.LocalDateTime;

public class Student extends People{
    private String studentNumber;
    private String university;
    private int year;
    private double point;

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

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", university='" + university + '\'' +
                ", year=" + year +
                ", point=" + point +
                '}';
    }

    public Student(String studentNumber, String university, int year, double point) {
        this.studentNumber = studentNumber;
        this.university = university;
        this.year = year;
        this.point = point;
    }

    public Student(long id, String name, int year, String address, Double height, Double weight, String studentNumber, String university, int year1, double point) {
        super(id, name, year, address, height, weight);
        this.studentNumber = studentNumber;
        this.university = university;
        this.year = year1;
        this.point = point;
    }
}
