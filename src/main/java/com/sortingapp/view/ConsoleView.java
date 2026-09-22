package com.sortingapp.view;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentBuilder;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public MenuOptions showMainMenuAndAsk() {
        System.out.println();
        System.out.println("========== МЕНЮ ==========");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }
        System.out.println("==========================");

        while (true) {
            System.out.print("Ваш выбор: ");
            String input = readLine();
            try {
                int code = Integer.parseInt(input);
                return MenuOptions.fromCode(code);
            } catch (NumberFormatException e) {
                showError("Введите число.");
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    public AlgorithmOptions showSortStrategyMenuAndAsk() {
        System.out.println();
        System.out.println("========== Способ сортировки ==========");
        for (AlgorithmOptions option : AlgorithmOptions.values()) {
            System.out.println(option);
        }
        System.out.println("==========================");

        while (true) {
            System.out.print("Ваш выбор: ");
            String input = readLine();
            try {
                int code = Integer.parseInt(input);
                return AlgorithmOptions.fromCode(code);
            } catch (NumberFormatException e) {
                showError("Введите число.");
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    public FieldOptions showFieldMenuAndAsk() {
        System.out.println();
        System.out.println("========== Выбор поля ==========");
        for (FieldOptions option : FieldOptions.values()) {
            System.out.println(option);
        }
        System.out.println("==========================");

        while (true) {
            System.out.print("Ваш выбор: ");
            String input = readLine();
            try {
                int code = Integer.parseInt(input);
                return FieldOptions.fromCode(code);
            } catch (NumberFormatException e) {
                showError("Введите число.");
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    public String askFilePath() {
        System.out.print("Введите путь к файлу: ");
        return readLine();
    }

    public int askCount() {
        while (true) {
            System.out.print("Сколько студентов создать? ");
            String input = readLine();
            try {
                int count = Integer.parseInt(input);
                if (count > 0 && count <= 1000) {
                    return count;
                }
                showError("Число должно быть в диапазоне 1..1000.");
            } catch (NumberFormatException e) {
                showError("Введите целое число.");
            }
        }
    }

    public Student askStudent() {
        System.out.println("--- Ввод студента ---");

        System.out.print("Номер группы: ");
        String group = readLine();

        double grade;
        while (true) {
            System.out.print("Средняя оценка (0..10): ");
            String input = readLine();
            try {
                grade = Double.parseDouble(input.replace(',', '.'));
                if (grade >= 0 && grade <= 10) {
                    break;
                }
                showError("Оценка должна быть в диапазоне 0..10.");
            } catch (NumberFormatException e) {
                showError("Введите число.");
            }
        }

        int recordBook;
        while (true) {
            System.out.print("Номер зачётной книжки: ");
            String input = readLine();
            try {
                recordBook = Integer.parseInt(input);
                if (recordBook >= 1) {
                    break;
                }
                showError("Номер зачётки должен быть положительным числом.");
            } catch (NumberFormatException e) {
                showError("Введите целое число.");
            }
        }

        return new StudentBuilder()
                .groupNumber(group)
                .averageScore(grade)
                .recordBookNumber(recordBook)
                .build();
    }

    public boolean askContinueInput() {
        System.out.print("Добавить ещё одного студента? (y/n): ");
        String input = readLine().toLowerCase();
        return input.equals("y") || input.equals("д") || input.equals("yes");
    }

    public void showStudents(List<Student> students) {
        System.out.println();
        if (students == null || students.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        System.out.println("+------------+----------------+------------------+");
        System.out.println("| Группа     | Ср. оценка     | Номер зачётки    |");
        System.out.println("+------------+----------------+------------------+");
        for (Student s : students) {
            System.out.printf("| %-10s | %-14.2f | %-16d |%n",
                    s.getGroupNumber(),
                    s.getAverageScore(),
                    s.getRecordBookNumber());
        }
        System.out.println("+------------+----------------+------------------+");
        System.out.printf("Всего: %d%n", students.size());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("[Ошибка] " + message);
    }

    public void showSuccess(String message) {
        System.out.println("[OK] " + message);
    }

    private String readLine() {
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    public void close() {
        scanner.close();
    }
}