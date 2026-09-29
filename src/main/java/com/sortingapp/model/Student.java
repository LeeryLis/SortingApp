package com.sortingapp.model;

import java.util.Objects;

public final class Student {
    private final String groupNumber;
    private final double averageScore;
    private final int recordBookNumber;

    Student(String groupNumber, double averageScore, int recordBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.recordBookNumber = recordBookNumber;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public String getGroupNumber() { return groupNumber; }
    public double getAverageScore() { return averageScore; }
    public int getRecordBookNumber() { return recordBookNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.averageScore, averageScore) == 0
                && recordBookNumber == student.recordBookNumber
                && Objects.equals(groupNumber, student.groupNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageScore, recordBookNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber='" + groupNumber + "'" +
                ", averageScore=" + averageScore +
                ", recordBookNumber=" + recordBookNumber +
                "}";
    }

    public String toCsv() {
        return groupNumber + ";" + averageScore + ";" + recordBookNumber;
    }
}
