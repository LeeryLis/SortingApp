package com.sortingapp;

import com.sortingapp.collection.StudentList;
import com.sortingapp.controller.StudentController;
import com.sortingapp.view.ConsoleView;
public class Main {
    public static void main(String[] args) {
        StudentList students = new StudentList();
        ConsoleView view = new ConsoleView();

        StudentController studentController = new StudentController(students, view);
        studentController.run();
    }
}
