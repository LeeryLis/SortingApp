package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class EvenOddRecordBookSort implements SortStrategy {
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
        int len = students.size();
        for (int i = 0; i < len - 1; i++) {
            if (isNotEven(students.get(i).getRecordBookNumber())) continue;
            for (int j = i + 1; j < len; j++) {
                if (isNotEven(students.get(j).getRecordBookNumber()))
                    continue;
                if (comparator.compare(students.get(i), students.get(j)) > 0) {
                    Student temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
    }

    private boolean isNotEven(int n) {
        return n % 2 != 0;
    }
}
