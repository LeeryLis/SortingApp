package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentAverageScoreComparator;
import com.sortingapp.model.comparator.StudentGroupNumberComparator;
import com.sortingapp.model.comparator.StudentRecordBookComparator;
import com.sortingapp.sort.SortStrategy;
import com.sortingapp.util.RandomFiller;

import java.util.List;

public class QuickSortTest {
    public static void main(String[] args) {
        List<Student> students = RandomFiller.generateStudents(10);

        System.out.println("До сортировки: ");
        for (Student student : students) {
            System.out.println(student);
        }

        SortStrategy sortStrategy = new QuickSort();

        System.out.println("После сортировки по группам: ");
        sortStrategy.sort(students, new StudentGroupNumberComparator());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("После сортировки по оценкам: ");
        sortStrategy.sort(students, new StudentAverageScoreComparator());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("После сортировки по recordBook: ");
        sortStrategy.sort(students, new StudentRecordBookComparator());
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
