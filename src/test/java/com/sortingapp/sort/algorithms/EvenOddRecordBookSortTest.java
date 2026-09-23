package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentRecordBookComparator;
import com.sortingapp.util.RandomFiller;

import java.util.List;

public class EvenOddRecordBookSortTest {
    public static void main(String[] args) {
        List<Student> students = RandomFiller.generateStudents(10);

        System.out.println("До сортировки: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Быстрая сортировка: ");
        new EvenOddRecordBookSort(new QuickSort()).sort(students, new StudentRecordBookComparator());
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Сортировка Пузырьком: ");
        new EvenOddRecordBookSort(new BubbleSort()).sort(students, new StudentRecordBookComparator());
        // после сортировки по groupNumber
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Сортировка слиянием: ");
        new EvenOddRecordBookSort(new MergeSort()).sort(students, new StudentRecordBookComparator());
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
