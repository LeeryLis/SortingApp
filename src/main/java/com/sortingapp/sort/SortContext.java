package com.sortingapp.sort;

import com.sortingapp.model.Student;

import java.util.Comparator;
import java.util.List;

public class SortContext {

    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Стратегия не может быть null");
        }
        this.strategy = strategy;
    }

    public SortStrategy getStrategy() {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия ещё не установлена");
        }

        return strategy;
    }

    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students == null || students.size() < 2) {
            return;
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Компаратор не может быть null");
        }
        strategy.sort(students, comparator);
    }
}
