package utils;

public class Validation {
    public boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }
    public boolean isValidName(String name){
        return name != null && !name.trim().isEmpty() && name.length() < 100;
    }
    public String validateYearOfBirth(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "EMPTY_YEAR";
        }
        try {
            int year = Integer.parseInt(input);
            int currentYear = java.time.LocalDate.now().getYear();
            if (year < 1900 || year > currentYear) {
                return "INVALID_YEAR";
            }
        } catch (NumberFormatException e) {
            return "INVALID_FORMAT";
        }
        return "OK";
    }
    public boolean isValidHeight(double height) {
        return height >= 50.0 && height <= 300.0;
    }

    public boolean isValidWeight(double weight) {
        return weight >= 5.0 && weight <= 1000.0;
    }

}
