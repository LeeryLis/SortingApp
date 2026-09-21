package com.sortingapp.view;

public enum MenuOptions {

    LOAD_FROM_FILE(1, "Загрузить список из файла"),
    MANUAL_INPUT(2, "Ввести список вручную"),
    RANDOM_FILL(3, "Заполнить список случайно"),
    SORT(4, "Отсортировать список"),
    SAVE_TO_FILE(5, "Сохранить список в файл"),
    SHOW_ALL(6, "Показать текущий список"),
    EXIT(0, "Выход");

    private final int code;
    private final String description;

    MenuOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOptions fromCode(int code) {
        for (MenuOptions option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        throw new IllegalArgumentException("Неизвестный пункт меню: " + code);
    }

    @Override
    public String toString() {
        return String.format("[%d] - %s", code, description);
    }
}
