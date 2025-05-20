package model;

public enum AcademicPerformance {
    POOR("Kém"), WEAK("Yếu"), AVERAGE("Trung bình"), GOOD("Khá"), EXCELLENT("Giỏi"), OUTSTANDING("Xuất sắc");

    private final String description;

    AcademicPerformance(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AcademicPerformance calculate(double point) {
        if (point < 3.0) return POOR;
        if (point < 5.0) return WEAK;
        if (point < 6.5) return AVERAGE;
        if (point < 7.5) return GOOD;
        if (point < 9.0) return EXCELLENT;
        return OUTSTANDING;
    }
}