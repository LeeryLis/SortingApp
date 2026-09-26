package com.sortingapp.util;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomFillerTest {

    //Этот тест проверяет результат RandomFiler на кол-во студентов
    //и их поля на диапазон рандомайзера

    @Test
    public void generateStudentsTest(){
        List<Student> students = RandomFiller.generateStudents(1000);

        assertEquals(1000,students.size());
        for (Student student : students) {

            String groupNumber = student.getGroupNumber();
            assertTrue(groupNumber.matches("\\d+"));
            int intGroupNumber = Integer.parseInt(groupNumber);
            assertTrue(StudentValidator.isValidGroupNumber(groupNumber));
            assertTrue(intGroupNumber >= 0);
            assertTrue(intGroupNumber <= 99999);

            assertTrue(StudentValidator.isValidAverageScore(student.getAverageScore()));

            assertTrue(StudentValidator.isValidRecordBookNumber(student.getRecordBookNumber()));
        }
    }
}
