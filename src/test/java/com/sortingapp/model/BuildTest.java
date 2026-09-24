package com.sortingapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class BuildTest {

    private Student student;
    @BeforeEach
    void setUp() {
        student = Student.builder()
                .groupNumber("14367")
                .averageScore(3)
                .recordBookNumber(6267)
                .build();
    }
@Test
public void fieldsChek(){
    //Проверка через поля
    assertEquals("14367", student.getGroupNumber());
    assertEquals(3, student.getAverageScore());
    assertEquals(6267, student.getRecordBookNumber());
}
    @Test
    public void equalsChek(){
        //Проверка через equals
        Student student1 = new Student("14367",3,6267);
        assertEquals(student1, student);
    }
    @Test
    public void allFieldsChek(){
        //Проверка на НЕ создание всех 3-ёх полей
        Student student = Student.builder().groupNumber("14367").recordBookNumber(6267).build();
    }
    @Test
    public void groupNumberEmptyValidationChek(){
        //Проверяет на пустой ввод поля groupNumber
        Student student = Student.builder()
                .groupNumber("")
                .averageScore(3)
                .recordBookNumber(6267)
                .build();
    }
    @Test
    public void groupNumberNullValidationChek(){
        //Проверяет на пустой ввод поля groupNumber
        Student student = Student.builder()
                .groupNumber(null)
                .averageScore(3)
                .recordBookNumber(6267)
                .build();
    }

}