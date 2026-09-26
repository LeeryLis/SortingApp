package com.sortingapp.collection;

import com.sortingapp.model.Student;
import java.util.ArrayList;
import java.util.Collection;

public class StudentList extends ArrayList<Student> {
    public StudentList() {
        super();
    }

    public StudentList(int initialCapacity) {
        super(initialCapacity);
    }

    public StudentList(Collection<? extends Student> c) {
        super(c);
    }
}
