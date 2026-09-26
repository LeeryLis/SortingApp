package com.sortingapp.io;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    @TempDir
    Path tempDir;

    private Student student(String group, double score, int recordBook) {
        return new StudentBuilder()
                .groupNumber(group)
                .averageScore(score)
                .recordBookNumber(recordBook)
                .build();
    }

    private Path write(String fileName, String content) {
        Path file = tempDir.resolve(fileName);
        try {
            Files.writeString(file, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return file;
    }

    @Test
    void readsSingleStudent() throws FileNotFoundException {
        Path file = write("one.csv", "ИУ7-32Б;4.5;123456\n");

        List<Student> result = FileService.readFromFile(file.toString());

        assertEquals(1, result.size());
        assertEquals(student("ИУ7-32Б", 4.5, 123456), result.getFirst());
    }

    @Test
    void readsMultipleStudents() throws FileNotFoundException {
        Path file = write("many.csv", String.join("\n",
                "ИУ7-32Б;4.5;123456",
                "ИУ7-31Б;3.8;100000",
                "ИУ7-33Б;5.0;999999"
        ));

        List<Student> result = FileService.readFromFile(file.toString());

        assertEquals(3, result.size());
        assertEquals(student("ИУ7-32Б", 4.5, 123456), result.get(0));
        assertEquals(student("ИУ7-31Б", 3.8, 100000), result.get(1));
        assertEquals(student("ИУ7-33Б", 5.0, 999999), result.get(2));
    }

    @Test
    void skipsEmptyAndBlankLines() throws FileNotFoundException {
        Path file = write("blank.csv", String.join("\n",
                "ИУ7-32Б;4.5;123456",
                "",
                "   ",
                "ИУ7-31Б;3.8;100000"
        ));

        List<Student> result = FileService.readFromFile(file.toString());

        assertEquals(2, result.size());
    }

    @Test
    void trimsWhitespaceAroundFields() throws FileNotFoundException {
        Path file = write("spaces.csv", "  ИУ7-32Б ; 4.5 ; 123456 \n");

        List<Student> result = FileService.readFromFile(file.toString());

        assertEquals(1, result.size());
        assertEquals(student("ИУ7-32Б", 4.5, 123456), result.getFirst());
    }

    @Test
    void throwsWhenFileDoesNotExist() {
        Path missing = tempDir.resolve("missing.csv");

        assertThrows(FileNotFoundException.class,
                () -> FileService.readFromFile(missing.toString()));
    }

    @Test
    void rejectsNullPath() {
        assertThrows(IllegalArgumentException.class,
                () -> FileService.readFromFile(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void rejectsBlankPath(String path) {
        assertThrows(IllegalArgumentException.class,
                () -> FileService.readFromFile(path));
    }

    @Test
    void rejectsDirectoryAsFile() throws IOException {
        Path dir = Files.createDirectory(tempDir.resolve("dir"));

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(dir.toString()));
    }

    @Test
    void rejectsLineWithTooFewFields()  {
        Path file = write("bad.csv", "ИУ7-32Б;4.5\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsLineWithTooManyFields()  {
        Path file = write("bad.csv", "ИУ7-32Б;4.5;123456;extra\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsBlankGroupNumber() {
        Path file = write("bad.csv", ";4.5;123456\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsNonNumericAverageScore()  {
        Path file = write("bad.csv", "ИУ7-32Б;abc;123456\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsAverageScoreOutOfRange()  {
        Path file = write("bad.csv", "ИУ7-32Б;5.5;123456\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsNonNumericRecordBookNumber()  {
        Path file = write("bad.csv", "ИУ7-32Б;4.5;abc\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    @Test
    void rejectsRecordBookNumberOutOfRange()  {
        Path file = write("bad.csv", "ИУ7-32Б;4.5;0\n");

        assertThrows(FileFormatException.class,
                () -> FileService.readFromFile(file.toString()));
    }

    // appendStudents

    @Test
    void appendStudentsCreatesFileIfMissing() throws IOException {
        Path file = tempDir.resolve("new.csv");

        FileService.appendStudents(List.of(
                student("ИУ7-32Б", 4.5, 123456),
                student("ИУ7-31Б", 3.8, 100000)
        ), file.toString());

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(2, lines.size());
        assertEquals("ИУ7-32Б;4.5;123456", lines.get(0));
        assertEquals("ИУ7-31Б;3.8;100000", lines.get(1));
    }

    @Test
    void appendStudentsAppendsToExistingFile() throws IOException {
        Path file = write("existing.csv", "ИУ7-32Б;4.5;123456\n");

        FileService.appendStudents(List.of(
                student("ИУ7-31Б", 3.8, 100000)
        ), file.toString());

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(2, lines.size());
        assertEquals("ИУ7-32Б;4.5;123456", lines.get(0));
        assertEquals("ИУ7-31Б;3.8;100000", lines.get(1));
    }

    @Test
    void appendStudentsDoesNothingForEmptyList() throws IOException {
        Path file = write("existing.csv", "ИУ7-32Б;4.5;123456\n");

        FileService.appendStudents(List.of(), file.toString());

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(1, lines.size());
    }

    @Test
    void appendStudentsDoesNothingForNullList() throws IOException {
        Path file = write("existing.csv", "ИУ7-32Б;4.5;123456\n");

        FileService.appendStudents(null, file.toString());

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(1, lines.size());
    }

    @Test
    void appendStudentsCreatesParentDirectories() {
        Path file = tempDir.resolve("nested/dir/students.csv");

        FileService.appendStudents(List.of(
                student("ИУ7-32Б", 4.5, 123456)
        ), file.toString());

        assertTrue(Files.exists(file));
    }

    // appendText

    @Test
    void appendTextCreatesFile() throws IOException {
        Path file = tempDir.resolve("text.txt");

        FileService.appendText("hello\n", file.toString());

        assertEquals("hello\n", Files.readString(file, StandardCharsets.UTF_8));
    }

    @Test
    void appendTextAppendsToExistingFile() throws IOException {
        Path file = write("text.txt", "line1\n");

        FileService.appendText("line2\n", file.toString());

        assertEquals("line1\nline2\n", Files.readString(file, StandardCharsets.UTF_8));
    }

    @Test
    void appendTextDoesNothingForEmptyString() throws IOException {
        Path file = write("text.txt", "original\n");

        FileService.appendText("", file.toString());

        assertEquals("original\n", Files.readString(file, StandardCharsets.UTF_8));
    }

    @Test
    void appendTextDoesNothingForNullString() throws IOException {
        Path file = write("text.txt", "original\n");

        FileService.appendText(null, file.toString());

        assertEquals("original\n", Files.readString(file, StandardCharsets.UTF_8));
    }

    @Test
    void appendTextRejectsBlankPath() {
        assertThrows(IllegalArgumentException.class,
                () -> FileService.appendText("data", "   "));
    }

    @Test
    void appendTextRejectsNullPath() {
        assertThrows(IllegalArgumentException.class,
                () -> FileService.appendText("data", null));
    }

    // round-trip

    @Test
    void roundTripPreservesStudents() throws FileNotFoundException {
        Path file = tempDir.resolve("round.csv");
        List<Student> original = List.of(
                student("ИУ7-32Б", 4.5, 123456),
                student("ИУ7-31Б", 3.8, 100000),
                student("ИУ7-33Б", 5.0, 999999)
        );

        FileService.appendStudents(original, file.toString());
        List<Student> restored = FileService.readFromFile(file.toString());

        assertEquals(original, restored);
    }
}
