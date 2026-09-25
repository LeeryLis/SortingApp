package com.sortingapp.util;

import com.sortingapp.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomFiller {

    //Cоздаёт в формате "чтобы были" через случайный генератор поля студента
    // и добавляет в лист count число студентов через StudentBuilder.

    private static final Random RANDOM = new Random();
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
        return String.valueOf(RANDOM.nextInt(100001));
    }

    private static double getRandomAverageScore() {
        return (RANDOM.nextInt(301) + 200) / 100.0;
    }

    private static int getRandomRecordBookNumber() {
        return RANDOM.nextInt(100001)+1;
    }
}
