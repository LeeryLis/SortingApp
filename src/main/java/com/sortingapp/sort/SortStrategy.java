package com.sortingapp.sort;

import com.sortingapp.collection.StudentList;
import com.sortingapp.model.Student;

import java.util.Comparator;

public interface SortStrategy {
    void sort(StudentList students, Comparator<Student> comparator);
}
