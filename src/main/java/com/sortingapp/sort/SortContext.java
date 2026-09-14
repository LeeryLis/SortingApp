package com.sortingapp.sort;

import com.sortingapp.model.Student;

import java.util.Comparator;
import java.util.List;

public class SortContext {
    private SortStrategy strategy;

    public SortContext(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy must not be null");
        }
        this.strategy = strategy;
    }

    public SortStrategy getStrategy() {
        return strategy;
    }

    public void sort(List<Student> students, Comparator<Student> comparator) {
        strategy.sort(students, comparator);
    }
}
