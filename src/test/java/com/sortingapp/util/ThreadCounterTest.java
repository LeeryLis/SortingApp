package com.sortingapp.util;

import com.sortingapp.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadCounterTest {

    List<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
        students.add(student("732", 4.5, 123456));
        students.add(student("732", 4.8, 123457));
        students.add(student("731", 5.0, 123458));
        students.add(student("732", 4.5, 123456));
    }

    private static Student student(String group, double score, int recordBook) {
        return Student.builder()
                .groupNumber(group)
                .averageScore(score)
                .recordBookNumber(recordBook)
                .build();
    }

    @Test
    void testCountOccurrencesOfExactStudent() {
        Student target = student("732", 4.5, 123456);
        long count = ThreadCounter.countOccurences(students, target);
        assertEquals(2L, count, "Студент с такими полями должен встречаться 2 раза");
    }

    @Test
    void testCountOccurrencesOfMissingStudent() {
        Student target = student("999", 3.0, 999999);
        long count = ThreadCounter.countOccurences(students, target);
        assertEquals(0L, count, "Такого студента в списке нет");
    }

    @Test
    void testCountOccurrencesInEmptyCollection() {
        long count = ThreadCounter.countOccurences(
                Collections.emptyList(), student("732", 4.5, 123456));
        assertEquals(0L, count, "В пустой коллекции вхождений 0");
    }

    @Test
    void testCountOccurrencesOfSingleElementMatch() {
        Student target = student("732", 4.5, 123456);
        long count = ThreadCounter.countOccurences(
                Collections.singletonList(target), target);
        assertEquals(1L, count, "В единственном элементе совпадение должно возвращать 1");
    }

    @Test
    void testCountOccurrencesInLargeCollection() {
        List<Student> largeCollection = new ArrayList<>(10_000);
        IntStream.range(0, 10_000).forEach(i -> largeCollection.add(i % 2 == 0
                ? student("732", 4.5, 123456)
                : student("731", 3.7, 123456)));

        Student target = student("732", 4.5, 123456);

        long count = ThreadCounter.countOccurences(largeCollection, target);
        assertEquals(5000L, count, "Должно быть ровно 5000 вхождений в многопоточном режиме");
    }

    @Test
    void testCountOccurrencesWithAlmostMatchingData() {
        Student target = student("732", 4.5, 123456);

        List<Student> almostMatchingStudents = new ArrayList<>();
        almostMatchingStudents.add(student("732", 4.5, 123457));
        almostMatchingStudents.add(student("731", 4.5, 123456));
        almostMatchingStudents.add(target);

        long count = ThreadCounter.countOccurences(almostMatchingStudents, target);
        assertEquals(1L, count, "Должно учитываться только полное совпадение материальных полей");
    }
}
