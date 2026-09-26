package com.sortingapp.io;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentBuilder;
import com.sortingapp.model.StudentValidator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FileService {
    private static final String CSV_DELIMITER = ";";
    private static final int EXPECTED_FIELDS = 3;

    private FileService() {}

    public static List<Student> readFromFile(String rawPath) {
        requireValidPath(rawPath);

        Path path = Path.of(rawPath);
        if (!Files.exists(path)) {
            return List.of();
        }
        if (!Files.isRegularFile(path)) {
            throw new FileFormatException("Not a regular file: " + rawPath);
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            List<String> rawLines = lines.collect(Collectors.toList());
            return parseAll(rawLines);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read file: " + rawPath, e);
        }
    }

    public static void appendStudents(List<Student> students, String rawPath) {
        if (students == null || students.isEmpty()) {
            return;
        }

        String text = students.stream()
                .map(Student::toCsv)
                .collect(Collectors.joining(System.lineSeparator(),
                        "", System.lineSeparator()));
        appendText(text, rawPath);
    }

    public static void appendText(String text, String rawPath) {
        requireValidPath(rawPath);

        if (text == null || text.isEmpty()) {
            return;
        }

        Path path = Path.of(rawPath);
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(
                    path,
                    text,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write file: " + rawPath, e);
        }
    }

    private static void requireValidPath(String rawPath) {
        if (rawPath == null || rawPath.isBlank()) {
            throw new IllegalArgumentException("Path must not be null or blank");
        }
    }

    private static List<Student> parseAll(List<String> rawLines) {
        return IntStream.range(0, rawLines.size())
                .filter(i -> !rawLines.get(i).isBlank())
                .mapToObj(i -> parseLine(rawLines.get(i), i + 1))
                .collect(Collectors.toList());
    }

    private static Student parseLine(String line, int lineNumber) {
        String[] parts = splitFields(line, lineNumber);
        String groupNumber = parseGroupNumber(parts[0], lineNumber);
        double averageScore = parseAverageScore(parts[1], lineNumber);
        int recordBookNumber = parseRecordBookNumber(parts[2], lineNumber);

        return new StudentBuilder()
                .groupNumber(groupNumber)
                .averageScore(averageScore)
                .recordBookNumber(recordBookNumber)
                .build();
    }

    private static String[] splitFields(String line, int lineNumber) {
        String[] parts = line.split(CSV_DELIMITER);
        if (parts.length != EXPECTED_FIELDS) {
            throw new FileFormatException(String.format(
                    "Line %d: expected %d fields, got %d -> \"%s\"",
                    lineNumber, EXPECTED_FIELDS, parts.length, line));
        }
        return parts;
    }

    private static String parseGroupNumber(String raw, int lineNumber) {
        String groupNumber = raw.trim();
        if (!StudentValidator.isValidGroupNumber(groupNumber)) {
            throw new FileFormatException(String.format(
                    "Line %d: invalid groupNumber \"%s\"", lineNumber, raw));
        }
        return groupNumber;
    }

    private static double parseAverageScore(String raw, int lineNumber) {
        double averageScore;
        try {
            averageScore = Double.parseDouble(raw.trim());
        } catch (NumberFormatException e) {
            throw new FileFormatException(String.format(
                    "Line %d: invalid averageScore \"%s\"", lineNumber, raw), e);
        }
        if (!StudentValidator.isValidAverageScore(averageScore)) {
            throw new FileFormatException(String.format(
                    "Line %d: averageScore out of range: %s", lineNumber, averageScore));
        }
        return averageScore;
    }

    private static int parseRecordBookNumber(String raw, int lineNumber) {
        int recordBookNumber;
        try {
            recordBookNumber = Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw new FileFormatException(String.format(
                    "Line %d: invalid recordBookNumber \"%s\"", lineNumber, raw), e);
        }
        if (!StudentValidator.isValidRecordBookNumber(recordBookNumber)) {
            throw new FileFormatException(String.format(
                    "Line %d: recordBookNumber out of range: %d", lineNumber, recordBookNumber));
        }
        return recordBookNumber;
    }
}
