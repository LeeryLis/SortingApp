package com.sortingapp.view;

public enum AlgorithmOptions {
    BUBBLE_SORT(1, "Метод сортировки \"BubbleSort\""),
    MERGE_SORT(2, "Метод сортировки \"MergeSort\""),
    QUICK_SORT(3, "Метод сортировки \"QuickSort\"");

    private final int code;
    private final String description;

    AlgorithmOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AlgorithmOptions fromCode(int code) {
        for (AlgorithmOptions option : values()) {
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
