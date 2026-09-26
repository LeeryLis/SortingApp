package com.sortingapp.util;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentConstraints;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class RandomFiller {

    //Cоздаёт в формате StudentValidator через случайный генератор поля студента
    // и возвращает список из count числа студентов через StudentBuilder.

    private RandomFiller() {}

    public static List<Student> generateStudents(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count must be non-negative");
        }
        return IntStream.range(0, count)
                .mapToObj(i -> randomStudent())
                .toList();
    }

    private static Student randomStudent() {
        return Student.builder()
                .groupNumber(getRandomGroupNumber())
                .averageScore(getRandomAverageScore())
                .recordBookNumber(getRandomRecordBookNumber())
                .build();
    }

    private static String getRandomGroupNumber() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000));
    }

    private static double getRandomAverageScore() {
        int x100 = ThreadLocalRandom.current().nextInt(
                toHundredths(StudentConstraints.AVERAGE_SCORE_MIN),
                toHundredths(StudentConstraints.AVERAGE_SCORE_MAX) + 1);
        return x100 / 100.0;
    }

    private static int getRandomRecordBookNumber() {
        return ThreadLocalRandom.current().nextInt(
                StudentConstraints.RECORD_BOOK_NUMBER_MIN,
                StudentConstraints.RECORD_BOOK_NUMBER_MAX+1);
    }

    private static int toHundredths(double score) {
        return (int) Math.round(score * 100);
    }
}