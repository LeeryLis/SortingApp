package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class EvenOddRecordBookSort implements SortStrategy {
    private final SortStrategy inner;

    public EvenOddRecordBookSort(SortStrategy inner) {
        this.inner = inner;
    }

    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        /*
            Дополнительное задание 1:
            дополнительно к основным сортировкам реализовать эти же алгоритмы
            сортировки таким образом, что объекты классов будут сортироваться
            по какому-либо числовому полю: объекты с четными значениями этого поля
            должны быть отсортированы в натуральном порядке, а с нечетными – оставаться
            на исходных позициях.

            Поле для сортировки:
            Номер зачётной книжки (recordBookNumber)
         */
    }
}
