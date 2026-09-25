package com.sortingapp.util;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomFiller {

    //Cоздаёт в формате "чтобы были" через случайный генератор поля студента
    // и добавляет в лист count число студентов через StudentBuilder.

    private static final int AVERAGE_SCORE_MIN_PLUS_RUSSIAN_ADJUSTMENT = (int) (StudentConstraints.AVERAGE_SCORE_MIN * 100) + 200;
    private static final int AVERAGE_SCORE_MAX = (int) (StudentConstraints.AVERAGE_SCORE_MAX * 100);

    private RandomFiller() {}

    public static List<Student> generateStudents(int count) {

        List<Student> students = new ArrayList<>(count);
        for(int i =0; i < count; i++) {
            students.add(Student.builder()
                    .groupNumber(getRandomGroupNumber())
                    .averageScore(getRandomAverageScore())
                    .recordBookNumber(getRandomRecordBookNumber())
                    .build());
        }
        return students;
    }

    private static String getRandomGroupNumber() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000));
    }

    private static double getRandomAverageScore() {
        return ThreadLocalRandom.current().nextInt(
                AVERAGE_SCORE_MIN_PLUS_RUSSIAN_ADJUSTMENT,
                AVERAGE_SCORE_MAX+1) / 100.0;
    }

    private static int getRandomRecordBookNumber() {
        return ThreadLocalRandom.current().nextInt(
                StudentConstraints.RECORD_BOOK_NUMBER_MIN,
                StudentConstraints.RECORD_BOOK_NUMBER_MAX+1);
    }
}