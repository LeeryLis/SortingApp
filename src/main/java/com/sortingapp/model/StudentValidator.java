package com.sortingapp.model;

public final class StudentValidator {
    private StudentValidator() {}

    public static boolean isValidGroupNumber(String g) {
        return g != null && !g.isBlank();
    }

    public static boolean isValidAverageScore(double s) {
        return s >= StudentConstraints.AVERAGE_SCORE_MIN && s <= StudentConstraints.AVERAGE_SCORE_MAX;
    }

    public static boolean isValidRecordBookNumber(int n) {
        return n >= StudentConstraints.RECORD_BOOK_NUMBER_MIN && n <= StudentConstraints.RECORD_BOOK_NUMBER_MAX;
    }

    public static void validate(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }

        if (!isValidGroupNumber(student.getGroupNumber())) {
            throw new IllegalArgumentException("Invalid group number");
        }

        if (!isValidAverageScore(student.getAverageScore())) {
            throw new IllegalArgumentException("Invalid average score");
        }

        if (!isValidRecordBookNumber(student.getRecordBookNumber())) {
            throw new IllegalArgumentException("Invalid record book number");
        }
    }
}
