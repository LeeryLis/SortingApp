package com.sortingapp.controller;

import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentAverageScoreComparator;
import com.sortingapp.model.comparator.StudentGroupNumberComparator;
import com.sortingapp.model.comparator.StudentRecordBookComparator;
import com.sortingapp.sort.SortStrategy;
import com.sortingapp.sort.algorithms.BubbleSort;
import com.sortingapp.sort.algorithms.MergeSort;
import com.sortingapp.sort.algorithms.QuickSort;
import com.sortingapp.view.AlgorithmOptions;
import com.sortingapp.view.FieldOptions;

import java.util.Comparator;
import java.util.List;

public class SortController {
    SortStrategy sortStrategy;

    private void setStrategy(AlgorithmOptions algorithm) {
        this.sortStrategy = toStrategy(algorithm);
    }

    public void sort(List<Student> students, FieldOptions field, AlgorithmOptions algorithm) {

        setStrategy(algorithm);
        Comparator<Student> comparator = toComparator(field);

        sortStrategy.sort(students, comparator);
    }

    private Comparator<Student> toComparator(FieldOptions choice) {
        return switch (choice) {
            case GROUP_NUMBER -> new StudentGroupNumberComparator();
            case AVERAGE_SCORE -> new StudentAverageScoreComparator();
            case RECORD_BOOK_NUMBER -> new StudentRecordBookComparator();
        };
    }

    private SortStrategy toStrategy(AlgorithmOptions choice) {
        return switch (choice) {
            case BUBBLE_SORT -> new BubbleSort();
            case MERGE_SORT -> new MergeSort();
            case QUICK_SORT -> new QuickSort();
        };
    }
}
