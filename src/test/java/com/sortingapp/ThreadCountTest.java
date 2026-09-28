

package com.sortingapp;




import com.sortingapp.model.Student;
import com.sortingapp.util.ThreadCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadCounterTest {

     List<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
        students.add(new Student("732", 4.5, 123456));
        students.add(new Student("732", 4.8, 123457));
        students.add(new Student("731", 5.0, 123458));
        students.add(new Student("732", 4.5, 123456));
    }

    @Test
    void testCountOccurrencesOfExactStudent() {
        Student target = new Student("732", 4.5, 123456);
        long count = ThreadCounter.countOccurences(students, target);
        assertEquals(2L, count, "Студент с такими полями должен встречаться 2 раза");
    }

    @Test
    void testCountOccurrencesOfMissingStudent() {
        Student target = new Student("999", 3.0, 999999);
        long count = ThreadCounter.countOccurences(students, target);
        assertEquals(0L, count, "Такого студента в списке нет");
    }

    @Test
    void testCountOccurrencesInEmptyCollection() {

        long count = ThreadCounter.countOccurences(Collections.emptyList(), new Student("732", 4.5, 123456));
        assertEquals(0L, count, "В пустой коллекции вхождений 0");
    }

    @Test
    void testCountOccurrencesOfSingleElementMatch() {

        Student target = new Student("732", 4.5, 123456);
        long count = ThreadCounter.countOccurences(Collections.singletonList(target), target);
        assertEquals(1L, count, "В единственном элементе совпадение должно возвращать 1");
    }

    @Test
    void testCountOccurrencesInLargeCollection() throws InterruptedException {

        List<Student> largeCollection = new ArrayList<>(10_000);
        IntStream.range(0, 10_000).forEach(i -> {
            largeCollection.add(new Student(i % 2 == 0 ? "732" : "731",
                    i % 2 == 0 ? 4.5 : 3.7,
                    i % 2 == 0 ? 123456 : 123457));
        });


        Student target = new Student("732", 4.5, 123456);
        largeCollection.add(target);
        largeCollection.add(target);


        long count = ThreadCounter.countOccurences(largeCollection, target);
        assertEquals(5000L, count, "Число 732 с оценкой 4.5 и номером зачётки 123456 должно встретиться 5000 раз");
    }

    @Test
    void testCountOccurrencesWithAlmostMatchingData() {

        Student target = new Student("732", 4.5, 123456);


        List<Student> almostMatchingStudents = new ArrayList<>();
        almostMatchingStudents.add(new Student("732", 4.5, 123457));
        almostMatchingStudents.add(new Student("731", 4.5, 123456));
        almostMatchingStudents.add(target);

        long count = ThreadCounter.countOccurences(almostMatchingStudents, target);
        assertEquals(1L, count, "Должно учитываться только полное совпадение материальных полей");
    }
}