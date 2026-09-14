package com.sortingapp.model.comparator;

import com.sortingapp.model.Student;

import java.util.Comparator;

public class StudentRecordBookComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getRecordBookNumber(), o2.getRecordBookNumber());
    }
}
