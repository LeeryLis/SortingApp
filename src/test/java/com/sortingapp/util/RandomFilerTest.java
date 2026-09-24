package com.sortingapp.util;

import com.sortingapp.model.Student;
import com.sortingapp.util.RandomFiller;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomFilerTest {

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
            assertTrue(intGroupNumber >= 0);
            assertTrue(intGroupNumber <= 100000);

            assertTrue(student.getAverageScore() >= 1.0);
            assertTrue(student.getAverageScore() <= 5.0);

            assertTrue(student.getRecordBookNumber() >= 1);
            assertTrue(student.getRecordBookNumber() <= 100001);
        }
    }
}
