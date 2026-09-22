package com.sortingapp.controller;

import com.sortingapp.io.FileService;
import com.sortingapp.model.Student;
import com.sortingapp.model.comparator.StudentAverageScoreComparator;
import com.sortingapp.model.comparator.StudentGroupNumberComparator;
import com.sortingapp.model.comparator.StudentRecordBookComparator;
import com.sortingapp.sort.SortStrategy;
import com.sortingapp.sort.algorithms.BubbleSort;
import com.sortingapp.sort.algorithms.MergeSort;
import com.sortingapp.sort.algorithms.QuickSort;
import com.sortingapp.util.RandomFiller;
import com.sortingapp.view.AlgorithmOptions;
import com.sortingapp.view.ConsoleView;
import com.sortingapp.view.FieldOptions;
import com.sortingapp.view.MenuOptions;

import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;


public class StudentsController {

    private List<Student> students;
    ConsoleView view;

    private boolean running = true;

    public StudentsController(List<Student> students, ConsoleView view) {
        this.students = students;
        this.view = view;
    }

    public void run() {
        while (running) {
            try {
                MenuOptions choice = view.showMainMenuAndAsk();
                handleMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("[Ошибка]: " + e.getMessage());
            }
        }
        view.close();
    }

    private void handleMenuChoice(MenuOptions choice) {
        switch (choice) {
            case LOAD_FROM_FILE -> loadFromFile();
            case MANUAL_INPUT -> manualInput();
            case RANDOM_FILL -> randomFill();
            case SORT -> sort();
            case SAVE_TO_FILE -> saveToFile();
            case SHOW_ALL -> view.showStudents(students);
            case EXIT -> running = false;
        }
    }

    private void loadFromFile() {
        students = FileService.readFromFile(Paths.get(view.askFilePath()));
    }

    private void manualInput() {
        System.out.println("manualInput");
    }

    private void randomFill() {
        students = RandomFiller.generateStudents(view.askCount());
    }

    private void saveToFile() {
        System.out.println("saveToFile");
    }

    private void sort() {
        List<Student> sortedStudents = List.copyOf(students);

        Comparator<Student> comparator = chooseSortField(view.showFieldMenuAndAsk());
        SortStrategy sortStrategy = chooceSortStrategy(view.showSortStrategyMenuAndAsk());

        if (comparator != null && sortStrategy != null) {
            sortStrategy.sort(sortedStudents, comparator);
            view.showStudents(students);
        } else {
            System.out.println("Сортировка невозможна! Не выбрано поле или способ сортировки!");
        }
    }

    private Comparator<Student> chooseSortField(FieldOptions choice) {
        return switch (choice) {
            case GROUP_NUMBER -> new StudentGroupNumberComparator();
            case AVERAGE_SCORE -> new StudentAverageScoreComparator();
            case RECORD_BOOK_NUMBER -> new StudentRecordBookComparator();
        };
    }

    private SortStrategy chooceSortStrategy(AlgorithmOptions choice) {
        return switch (choice) {
            case BABBLE_SORT -> new BubbleSort();
            case MERGE_SORT -> new MergeSort();
            case QUICK_SORT -> new QuickSort();
        };
    }
}
