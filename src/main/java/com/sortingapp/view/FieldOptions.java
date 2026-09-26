package com.sortingapp.view;

public enum FieldOptions {
    GROUP_NUMBER(1, "Номер группы"),
    AVERAGE_SCORE(2, "Средний балл"),
    RECORD_BOOK_NUMBER(3, "Номер зачетной книжки");

    private final int code;
    private final String description;

    FieldOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static FieldOptions fromCode(int code) {
        for (FieldOptions option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        throw new IllegalArgumentException("Неизвестный пункт меню: " + code);
    }

    @Override
    public String toString() {
        return String.format("[%d] - сортировать по полю: %s", code, description);
    }
}
