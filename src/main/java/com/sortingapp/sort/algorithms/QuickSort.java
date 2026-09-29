package com.sortingapp.sort.algorithms;

import com.sortingapp.collection.StudentList;
import com.sortingapp.model.Student;
import com.sortingapp.sort.SortStrategy;

import java.util.Comparator;

public class QuickSort implements SortStrategy {
    @Override
    public void sort(StudentList students, Comparator<Student> comparator) {
        int len = students.size();
        if (len <= 1) {
            return;
        }
        Student pivot = students.get(len / 2);
        StudentList less = new StudentList();
        StudentList greater = new StudentList();
        StudentList equal = new StudentList();

        for (Student student : students) {
            if (comparator.compare(student, pivot) < 0) {
                less.add(student);
            } else if(comparator.compare(student, pivot) > 0) {
                greater.add(student);
            }else{
                equal.add(student);
            }
        }
        sort(less, comparator);
        sort(greater, comparator);

        students.clear();
        students.addAll(less);
        students.addAll(equal);
        students.addAll(greater);
    }
}
