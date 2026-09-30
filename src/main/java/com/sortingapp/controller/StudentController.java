package com.sortingapp.controller;

import com.sortingapp.collection.StudentList;
import com.sortingapp.io.ConsoleService;
import com.sortingapp.io.FileFormatException;
import com.sortingapp.io.FileService;
import com.sortingapp.model.Student;
import com.sortingapp.sort.SortService;
import com.sortingapp.util.RandomFiller;
import com.sortingapp.util.ThreadCounter;
import com.sortingapp.view.AlgorithmOptions;
import com.sortingapp.view.ConsoleView;
import com.sortingapp.view.FieldOptions;
import com.sortingapp.view.MenuOptions;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;


public class StudentController {

    private StudentList students;
    private final ConsoleView view;
    private final SortService sortService = new SortService();
    private final ConsoleService consoleService;

    private boolean running = true;

    public StudentController(StudentList students, ConsoleView view) {
        this.students = students;
        this.view = view;
        this.consoleService = new ConsoleService(view);
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
            case SHOW_ALL -> showStudents();
            case SEARCH -> searchCount();
            case EXIT -> exit();
        }
    }

    private void loadFromFile() {
        String path = view.askString("Введите путь к файлу: ");
        try {
            if (confirmOverwrite()) {
                students = new StudentList(FileService.readFromFile(path));
                view.showSuccess("Загружено студентов: " + students.size());
                showStudents();
            }
        } catch (FileNotFoundException e) {
            view.showError("Не удалось прочитать файл: " + e.getMessage());
        } catch (FileFormatException e) {
            view.showError("Неверный формат: " + e.getMessage());
        }
    }

    private void manualInput() {
        if (confirmOverwrite()) {
            students = new StudentList(consoleService.readAll());
            view.showSuccess("Загружено студентов: " + students.size());
            showStudents();
        }
    }

    private void randomFill() {
        if (confirmOverwrite()) {
            students = new StudentList(RandomFiller.generateStudents(view.askInt("Сколько студентов создать? ")));
            view.showSuccess("Загружено студентов: " + students.size());
            showStudents();
        }
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

        view.showMessage("Отсортировано по полю: " + field.getDescription());
        view.showMessage(algorithm.getDescription());
        showStudents();
    }

    private void saveToFile() {
        try {
            FileService.appendStudents(students, view.askString("Введите имя файла: "));
        } catch (UncheckedIOException e) {
            view.showError(e.getMessage());
        }
    }

    public void showStudents() {
        System.out.println();
        if (students == null || students.isEmpty()) {
            view.showMessage("Список пуст.");
            return;
        }

        view.showMessage("+------------+----------------+------------------+");
        view.showMessage("| Группа     | Ср. оценка     | Номер зачётки    |");
        view.showMessage("+------------+----------------+------------------+");
        for (Student s : students) {
            view.showMessage(String.format("| %-10s | %-14.2f | %-16d |%n",
                    s.getGroupNumber(),
                    s.getAverageScore(),
                    s.getRecordBookNumber()));
        }
        view.showMessage("+------------+----------------+------------------+");
        view.showMessage("Всего: " + students.size());
    }

    private void searchCount() {
        if (students == null || students.isEmpty()) {
            view.showMessage("Список пуст!");
        } else {
            Student student = consoleService.readOne();
            view.showMessage("Количество вхождений в список по заданным параметрам: "
                    + ThreadCounter.countOccurrences(students, student) + ".");
        }
    }

    private boolean confirmOverwrite() {
        if (students == null || students.isEmpty()) {
            return true;
        }
        return view.confirmAction("Список не пуст. Перезаписать данные?");
    }

    private void exit() {
        view.showMessage("Завершение работы. Пока!");
        running = false;
    }
}
