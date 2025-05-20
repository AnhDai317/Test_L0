package utils;

import java.util.Scanner;

public class Validation {
    private static final Scanner SC = new Scanner(System.in);

    // Common method to check for non-empty string
    private boolean isNonEmptyString(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            printError(fieldName + " must not be empty.");
            return false;
        }
        return true;
    }

    // Centralized error message printing
    private void printError(String message) {
        System.err.println(message);
    }

    public boolean isValidAddress(String address) {
        return isNonEmptyString(address, "Address");
    }

    public boolean isValidName(String name) {
        if (!isNonEmptyString(name, "Name")) return false;
        if (name.length() > Constants.MAX_NAME_LENGTH) {
            printError("Name must be less than or equal to " + Constants.MAX_NAME_LENGTH + " characters.");
            return false;
        }
        return true;
    }

    public boolean isValidStudentNumber(String studentNumber) {
        if (!isNonEmptyString(studentNumber, "Student number")) return false;
        if (studentNumber.length() != Constants.STUDENT_NUMBER_LENGTH) {
            printError("Student number must be exactly " + Constants.STUDENT_NUMBER_LENGTH + " characters long.");
            return false;
        }
        return true;
    }

    public boolean isValidHeight(double height) {
        if (height < Constants.MIN_HEIGHT || height > Constants.MAX_HEIGHT) {
            printError("Height must be between " + Constants.MIN_HEIGHT + " and " + Constants.MAX_HEIGHT + " cm.");
            return false;
        }
        return true;
    }

    public boolean isValidWeight(double weight) {
        if (weight < Constants.MIN_WEIGHT || weight > Constants.MAX_WEIGHT) {
            printError("Weight must be between " + Constants.MIN_WEIGHT + " and " + Constants.MAX_WEIGHT + " kg.");
            return false;
        }
        return true;
    }

    public boolean isValidYear(int year) {
        if (year < Constants.MIN_YEAR) {
            printError("Year of birth is too old. Minimum is " + Constants.MIN_YEAR + ".");
            return false;
        }
        if (year > Constants.MAX_YEAR) {
            printError("Year of birth cannot be later than " + Constants.MAX_YEAR + ".");
            return false;
        }
        return true;
    }

    public boolean isValidGender(String gender) {
        if (!isNonEmptyString(gender, "Gender")) return false;
        gender = gender.trim().toLowerCase();
        if (!gender.equals("male") && !gender.equals("female")) {
            printError("Gender must be 'Male' or 'Female'.");
            return false;
        }
        return true;
    }

    public static double getDouble(String message, double min, double max) {
        while (true) {
            try {
                System.out.print(message);
                double number = Double.parseDouble(SC.nextLine());
                if (min <= number && number <= max) return number;
                System.out.println("Please enter a real number between " + min + " and " + max + ".");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a real number between " + min + " and " + max + ".");
            }
        }
    }

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(SC.nextLine());
                if (min <= number && number <= max) return number;
                System.out.println("Please enter an integer between " + min + " and " + max + ".");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer between " + min + " and " + max + ".");
            }
        }
    }

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            String input = SC.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input must not be empty.");
        }
    }
}