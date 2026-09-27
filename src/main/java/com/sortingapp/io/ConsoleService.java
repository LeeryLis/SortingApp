package com.sortingapp.io;

import com.sortingapp.collection.StudentList;
import com.sortingapp.model.Student;
import com.sortingapp.model.StudentBuilder;
import com.sortingapp.view.ConsoleView;

public class ConsoleService {
    ConsoleView view;
    StudentList students = new StudentList();

    public ConsoleService(ConsoleView view) {
        this.view = view;
    }

    public StudentList readAll() {
        StudentList result = new StudentList();
        boolean addMore;

        do {
            Student student = readOne();
            if (student != null) {
                result.add(student);
            }
            addMore = view.confirmAction("Продолжаем ввод?");
        } while (addMore);

        return result;
    }

    private Student readOne() {
        view.showMessage("--- Ввод студента ---");

        String group      = view.askString("Номер группы: ").trim();
        double score      = view.askDouble("Средняя оценка (2..5): ");
        int recordBook    = view.askInt("Номер зачётной книжки: ");

        try {
            StudentBuilder builder = new StudentBuilder();
            builder.groupNumber(group)
                    .averageScore(score)
                    .recordBookNumber(recordBook);
            return  builder.build();
        } catch (IllegalArgumentException e) {
            view.showError("Данные некорректны: " + e.getMessage());
            return null;
        }
    }
}
