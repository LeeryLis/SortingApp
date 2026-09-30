package com.sortingapp.view;

import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public MenuOptions showMainMenuAndAsk() {
        System.out.println();
        showMessage("========== МЕНЮ ==========");

        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }

        showMessage("==========================");

        while (true) {
            String input = askString("Ваш выбор: ");

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
        showMessage("========== Способ сортировки ==========");
        for (AlgorithmOptions option : AlgorithmOptions.values()) {
            System.out.println(option);
        }
        showMessage("==========================");

        while (true) {
            String input = askString("Ваш выбор: ");

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
        showMessage("========== Выбор поля ==========");
        for (FieldOptions option : FieldOptions.values()) {
            System.out.println(option);
        }
        showMessage("==========================");

        while (true) {
            String input = askString("Ваш выбор: ");

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

    public boolean confirmAction(String message) {
        showMessage(message);
        String input = askString("(y/n): ").toLowerCase();

        return input.equals("y") || input.equals("д") || input.equals("yes");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        showMessage("[Ошибка] " + message);
    }

    public void showSuccess(String message) {
        showMessage("[OK] " + message);
    }

    private String readLine() {
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    public double askDouble(String message) {
        while (true) {
            String input = askString(message);
            try {
                return Double.parseDouble(input.replace(',', '.'));
            } catch (NumberFormatException e) {
                showError("Введите число.");
            }
        }
    }

    public int askInt(String message) {
        while (true) {
            String input = askString(message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                showError("Введите целое число.");
            }
        }
    }

    public String askString(String message) {
        System.out.print(message);
        return readLine();
    }

    public void close() {
        scanner.close();
    }
}