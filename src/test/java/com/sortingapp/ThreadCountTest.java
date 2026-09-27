

package com.sortingapp;
import com.sortingapp.util.ThreadCounter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadCounterTest {

    private List<Integer> groups; // Список групп
    private List<Double> scores; // Список средних баллов
    private List<Integer> recordBooks; // Список номеров зачетных книжек

    @BeforeEach
    void setUp() {
        // Инициализация списков
        groups = new ArrayList<>();
        groups.add(732);
        groups.add(732);
        groups.add(731);
        groups.add(732);

        scores = new ArrayList<>();
        scores.add(4.5);
        scores.add(4.8);
        scores.add(5.0);
        scores.add(3.7);

        recordBooks = new ArrayList<>();
        recordBooks.add(123456);
        recordBooks.add(123457);
        recordBooks.add(123458);
        recordBooks.add(123456);
    }

    @Test
    void testCountOccurrencesByRecordBook() {
        int targetRecordBook = 123456;
        long count = ThreadCounter.countOccurences(recordBooks, targetRecordBook); // Метод, который будет принимать только номера зачетных книжек
        assertEquals(2, count, "Количество вхождений зачетной книжки 123456 должно быть 2");
    }

    @Test
    void testCountOccurrencesByGroup() {
        int targetGroup = 732;
        long count = ThreadCounter.countOccurences(groups, targetGroup); // Метод, который будет принимать только группы
        assertEquals(3, count, "Количество студентов в группе 732 должно быть 3");
    }

    @Test
    void testCountOccurrencesByAverageScore() {
        double targetScore = 4.5;
        long count = ThreadCounter.countOccurences(scores, targetScore); // Метод для среднего балла
        assertEquals(1, count, "Количество студентов с баллом 4.5 должно быть 1");
    }

    @Test
    void testCountOccurrencesByNullValue() {
        long count = ThreadCounter.countOccurences(scores, null); // Проверяем на null
        assertEquals(0, count, "Количество вхождений null должно быть 0"); // Ожидаем 0
    }

    @Test
    void testCountOccurrencesInEmptyCollection() {
        List<Integer> emptyList = Collections.emptyList(); // Пустой список зачетных книжек
        long count = ThreadCounter.countOccurences(emptyList, 123456); // Сравниваем с несуществующим номером
        assertEquals(0, count, "Количество вхождений в пустой коллекции должно быть 0"); // Ожидаем 0
    }
}