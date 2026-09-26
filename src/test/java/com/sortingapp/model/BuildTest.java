package com.sortingapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void fieldsCheck(){
        //Проверка через поля
        assertEquals("14367", student.getGroupNumber());
        assertEquals(3.0, student.getAverageScore(), 0.0001);
        assertEquals(6267, student.getRecordBookNumber());
}
    @Test
    public void equalsCheck(){
        //Проверка через equals
        Student student1 = new Student("14367",3,6267);
        assertEquals(student1, student);
    }
    @Test
    public void allFieldsCheck(){
        //Проверка на НЕ создание всех 3-ёх полей
        assertThrows(IllegalStateException.class, () ->
            Student.builder()
                    .groupNumber("14367")
                    .recordBookNumber(6267)
                    .build());
    }
    @Test
    public void groupNumberEmptyValidationCheck(){
        //Проверяет на пустой ввод поля groupNumber
        assertThrows(IllegalArgumentException.class,() -> Student.builder()
                .groupNumber("")
                .averageScore(3)
                .recordBookNumber(6267)
                .build());
    }
    @Test
    public void groupNumberNullValidationCheck(){
        //Проверяет на пустой ввод поля groupNumber
        assertThrows(IllegalArgumentException.class,() -> Student.builder()
                .groupNumber(null)
                .averageScore(3)
                .recordBookNumber(6267)
                .build());
    }
}