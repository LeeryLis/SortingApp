package com.sortingapp.sort.algorithms;

import com.sortingapp.collection.StudentList;
import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;

public class BubbleSort implements SortStrategy {
    @Override
    public void sort(StudentList students, Comparator<Student> comparator) {
        int len = students.size();
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 0; i < len - 1; i++) {
                if (comparator.compare(students.get(i), students.get(i + 1)) > 0) {
                    Student temp = students.get(i);
                    students.set(i, students.get(i + 1));
                    students.set(i + 1, temp);
                    needIteration = true;
                }
            }
        }
    }

}
