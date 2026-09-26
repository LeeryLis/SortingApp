package com.sortingapp.controller;

import com.sortingapp.io.FileService;
import com.sortingapp.model.Student;
import com.sortingapp.sort.SortService;
import com.sortingapp.util.RandomFiller;
import com.sortingapp.view.AlgorithmOptions;
import com.sortingapp.view.ConsoleView;
import com.sortingapp.view.FieldOptions;
import com.sortingapp.view.MenuOptions;

import java.util.List;


public class StudentController {

    private List<Student> students;
    private final ConsoleView view;
    private final SortService sortService = new SortService();

    private boolean running = true;

    public StudentController(List<Student> students, ConsoleView view) {
        this.students = students;
        this.view = view;
    }

    public void run() {
        while (running) {
            try {
                MenuOptions choice = view.showMainMenuAndAsk();
                handleMenuChoice(choice);
            } catch (IllegalArgumentException | IllegalStateException e) {
                view.showError(e.getMessage());
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
            case EXIT -> exit();
        }
    }

    private void loadFromFile() {
        String path = view.askFilePath();
        try {
            students = FileService.readFromFile(path);
            view.showSuccess("Загружено студентов: " + students.size());
            view.showStudents(students);
        } catch (Exception e) {
            view.showError("Не удалось прочитать файл: " + e.getMessage());
            //Это заглушка, тут нужен IOException, который нужно пробросить из FileService.readFromFile()
        }
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
        boolean evenOnly = false;
        if (students == null || students.isEmpty() || students.size() < 2) {
            view.showError("Список пуст — нечего сортировать.");
            return;
        }

        FieldOptions field = view.showFieldMenuAndAsk();
        AlgorithmOptions algorithm = view.showSortStrategyMenuAndAsk();

        if (field == FieldOptions.RECORD_BOOK_NUMBER) {
            evenOnly = view.confirmAction("Применить особый способ сортировки только чётных значений?");
        }

        sortService.sort(students, field, algorithm, evenOnly);
        view.showStudents(students);
    }

    private void exit() {
        view.showMessage("Завершение работы. Пока!");
        running = false;
    }
}
