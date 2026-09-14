package com.sortingapp.model.comparator;

import com.sortingapp.model.Student;

import java.util.Comparator;

public class StudentGroupNumberComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getGroupNumber().compareTo(o2.getGroupNumber());
    }
}
