package com.sortingapp.sort.algorithms;

import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort implements SortStrategy {
    @Override
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int len = students.size();
        if (len <= 1) {
            return;
        }
        Student pivot = students.get(len / 2);
        List<Student> less = new ArrayList<>();
        List<Student> greater = new ArrayList<>();

        for (Student student : students) {
            if (comparator.compare(student, pivot) <= 0) {
                less.add(student);
            } else {
                greater.add(student);
            }
        }
        sort(less, comparator);
        sort(greater, comparator);

        students.clear();
        students.addAll(less);
        students.addAll(greater);
    }
}
