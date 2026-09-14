package com.sortingapp.io;

import com.sortingapp.model.Student;

import java.nio.file.Path;
import java.util.List;

public final class FileService {
    private FileService() {}

    public static List<Student> readFromFile(Path path) {
        return List.of();
    }

    public static void appendStudents(List<Student> students, Path path) {

    }

    public static void appendText(String text, Path path) {

    }
}
