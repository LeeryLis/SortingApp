package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentRecordBookComparator;
import com.sortingapp.sort.SortStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvenOddRecordBookSortTest {

    Comparator<Student> comparator = new StudentRecordBookComparator();
    SortStrategy innnerSortStrategy = new QuickSort();
    SortStrategy sortStrategy = new EvenOddRecordBookSort(innnerSortStrategy);

    @Test
    void manualTest() {
        // Подготовка данных
        // Нечетные зачетки (11, 13) должны зафиксироваться на индексах 1 и 3.
        // Четные зачетки (12, 14, 10) должны выстроиться по возрастанию
        Student s1 = Student.builder().groupNumber("А-1").averageScore(4.9).recordBookNumber(12).build(); // четный
        Student s2 = Student.builder().groupNumber("А-1").averageScore(5.0).recordBookNumber(11).build(); // нечетный (на месте)
        Student s3 = Student.builder().groupNumber("А-1").averageScore(3.5).recordBookNumber(14).build(); // четный
        Student s4 = Student.builder().groupNumber("А-1").averageScore(3.0).recordBookNumber(13).build(); // нечетный (на месте)
        Student s5 = Student.builder().groupNumber("А-1").averageScore(4.2).recordBookNumber(10).build(); // четный

        List<Student> students = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));

        // Ожидаемый результат
        List<Student> expectedStudents = new ArrayList<>(Arrays.asList(s5, s2, s1, s4, s3));

        // Сортируем нашим алгоритмом
        sortStrategy.sort(students, comparator);

        // Проверяем неподвижность нечетных элементов
        // Проверяем правильный порядок отсортированных четных элементов
        for (int i = 0; i < expectedStudents.size(); i++) {
            Student expected = expectedStudents.get(i);
            Student actual = students.get(i);
            assertEquals(expected, actual,
                    "Несовпадение элемента на позиции " + i);
        }
    }
}
