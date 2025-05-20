package main;

import controller.StudentManager;
import view.StudentView;
import utils.Validation;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Select Storage Type ===");
        System.out.println("1. Use ArrayList");
        System.out.println("2. Use LinkedList");
        int choice = Validation.getInt("Enter your choice (1-2): ", 1, 2);
        boolean useArrayList = (choice == 1);
        StudentManager studentManager = new StudentManager(useArrayList);
        StudentView view = new StudentView(studentManager);
        view.run();
    }
}