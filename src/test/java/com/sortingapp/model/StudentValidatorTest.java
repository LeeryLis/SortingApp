package com.sortingapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"ИУ7-32Б", "а", "1234"})
    void validGroupNumbers(String g) {
        assertTrue(StudentValidator.isValidGroupNumber(g));
    }

    @Test
    void invalidGroupNumbers() {
        assertFalse(StudentValidator.isValidGroupNumber(null));
        assertFalse(StudentValidator.isValidGroupNumber(""));
        assertFalse(StudentValidator.isValidGroupNumber("   "));
        assertFalse(StudentValidator.isValidGroupNumber("\t"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.0, 2.5, 5.0})
    void validScores(double s) {
        assertTrue(StudentValidator.isValidAverageScore(s));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, 5.01, Double.NaN})
    void invalidScores(double s) {
        assertFalse(StudentValidator.isValidAverageScore(s));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 500_000, 999_999})
    void validRecordBooks(int n) {
        assertTrue(StudentValidator.isValidRecordBookNumber(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1_000_000})
    void invalidRecordBooks(int n) {
        assertFalse(StudentValidator.isValidRecordBookNumber(n));
    }
}
