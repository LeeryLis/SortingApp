package com.sortingapp.util;

import com.sortingapp.model.Student;
import com.sortingapp.model.StudentValidator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomFillerTest {

    //Этот тест проверяет результат generateStudents в RandomFiler на кол-во студентов
    //и их поля в соответствии с StudentValidator

    @Test
    public void generateStudentsTest(){
        List<Student> students = RandomFiller.generateStudents(1000);

        assertEquals(1000,students.size());

        for (Student student : students) {
            assertTrue(StudentValidator.isValidGroupNumber(student.getGroupNumber()));
            assertTrue(StudentValidator.isValidAverageScore(student.getAverageScore()));
            assertTrue(StudentValidator.isValidRecordBookNumber(student.getRecordBookNumber()));
        }
    }
}
