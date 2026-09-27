package com.sortingapp.sort.algorithms;

import com.sortingapp.collection.StudentList;
import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;

public class MergeSort implements SortStrategy {
    @Override
    public void sort(StudentList students, Comparator<Student> comparator) {
        int len = students.size();
        if (len > 1) {
            int mid = len / 2;
            StudentList left = new StudentList(students.subList(0, mid));
            StudentList right = new StudentList(students.subList(mid, students.size()));

            sort(left, comparator);
            sort(right, comparator);

            merge(students, left, right, comparator);
        }
    }

    private static void merge(StudentList result, StudentList left, StudentList right, Comparator<Student> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }
}
