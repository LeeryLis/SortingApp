package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentGroupNumberComparator;
import com.sortingapp.sort.SortStrategy;
import com.sortingapp.util.RandomFiller;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSortTest {

    SortStrategy sortStrategy = new BubbleSort();
    Comparator<Student> comparator = new StudentGroupNumberComparator();

    @Test
    void sortTest() {
        // 1. Создаем исходный список студентов со случайными данными
        List<Student> students = RandomFiller.generateStudents(1000);

        // 2. Создаем две независимые копии этого списка
        List<Student> listForStandardSort = new ArrayList<>(students);
        List<Student> listForCustomSort = new ArrayList<>(students);

        // 3. Сортируем первую копию стандартным методом Java
        listForStandardSort.sort(comparator);

        // 4. Сортируем вторую копию нашим алгоритмом
        sortStrategy.sort(listForCustomSort, comparator);

        // 5. Проверяем, что списки идентичны по размеру и порядку элементов
        assertEquals(listForStandardSort.size(), listForCustomSort.size(),
                "Размеры списков после сортировки не совпадают!");

        for (int i = 0; i < listForStandardSort.size(); i++) {
            Student expected = listForStandardSort.get(i);
            Student actual = listForCustomSort.get(i);
            assertEquals(expected, actual,
                    "Несовпадение элемента на позиции " + i);
        }
    }
}
