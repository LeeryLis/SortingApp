package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class MergeSort implements SortStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int len = students.size();
        if (len > 1) {
            int mid = len / 2;
            List<Student> left = new ArrayList<>(students.subList(0, mid));
            List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

            sort(left, comparator);
            sort(right, comparator);

            merge(students, left, right, comparator);
        }
    }

    private static void merge(List<Student> result, List<Student> left, List<Student> right, Comparator<Student> comparator) {
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
