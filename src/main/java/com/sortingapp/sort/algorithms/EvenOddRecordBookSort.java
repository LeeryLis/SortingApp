package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class EvenOddRecordBookSort implements SortStrategy {
    private final SortStrategy inner;

    public EvenOddRecordBookSort(SortStrategy inner) {
        this.inner = inner;
    }

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
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        List<Student> tempStudents = new ArrayList<>();
        List<Boolean> evenElements = new ArrayList<>();
        for (Student s : students) {
            if (s.getRecordBookNumber() % 2 == 0) {
                tempStudents.add(s);
                evenElements.add(true);
            } else {
                evenElements.add(false);
            }
        }

        inner.sort(tempStudents, comparator);

        int j = 0;
        for (int i = 0; i < students.size(); i++) {
            if (evenElements.get(i) == true) {
                students.set(i, tempStudents.get(j));
                j++;
            }
        }
    }
}
