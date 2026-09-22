package com.sortingapp;

import com.sortingapp.controller.StudentController;
import com.sortingapp.model.Student;
import com.sortingapp.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        ConsoleView view = new ConsoleView();

        StudentController studentController = new StudentController(students, view);
        studentController.run();
    }
}
