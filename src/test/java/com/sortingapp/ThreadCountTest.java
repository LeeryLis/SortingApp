package com.sortingapp;

import com.sortingapp.model.Student;
import org.junit.jupiter.api.Test;
import com.sortingapp.util.ThreadCounter;
import com.sortingapp.util.RandomFiller;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ThreadCountTest {

   @Test
   public void countOccurrencesByRecordBookNumberTest() {
       List<Student> students = RandomFiller.generateStudents(1000);

       Student target = students.get(0);
       long count = ThreadCounter.count0ccurences(students, target);

       //минимум один — самого себя найдёт
       assertTrue(count >= 1);
       //больше всего списка быть не может физически
       assertTrue(count <= 1000);
   }

    @Test
    public void countOccurrencesIgnoresGroupAndScoreTest() {
        List<Student> students = RandomFiller.generateStudents(50);
        Student source = students.get(0);

        //Двойник: та же зачётка, но другие группа и средний балл
        Student twin = newStudent("777", 2.0, source.getRecordBookNumber());

        List<Student> data = List.of(source, twin);

        assertEquals(2, ThreadCounter.count0ccurences(data, source));
    }

    @Test
    public void countOccurrencesExactCountTest() {
        //Берём реальных студентов из генератора, чтобы не зависеть от конструктора
        List<Student> base = RandomFiller.generateStudents(20);
        Student hit = base.get(0);
        Student miss = base.get(1);

        List<Student> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(hit);
        }
        for (int i = 0; i < 5; i++) {
            data.add(miss);
        }

        assertEquals(5, ThreadCounter.count0ccurences(data, hit));
        assertEquals(5, ThreadCounter.count0ccurences(data, miss));
        assertEquals(0, ThreadCounter.count0ccurences(data, newStudent("999999", 3.0, 123456789)));
    }

    @Test
    public void countOccurrencesEmptyAndNullTest() {
        assertEquals(0, ThreadCounter.count0ccurences(new ArrayList<Student>(), newStudent("1", 4.0, 1)));
        assertEquals(0, ThreadCounter.count0ccurences(null, newStudent("1", 4.0, 1)));

        //null внутри списка тоже должен считаться, а не ронять поток с NPE
        List<Student> withNull = new ArrayList<>();
        withNull.add(null);
        withNull.add(newStudent("1", 4.0, 1));
        withNull.add(null);

        assertEquals(2, ThreadCounter.count0ccurences(withNull, null));
    }

    @Test
    public void countOccurrencesLargeCollectionTest() {
        //Тот самый тест на производительность: миллион студентов через RandomFiller
        List<Student> students = RandomFiller.generateStudents(1_000_000);
        Student target = students.get(0);

        long start = System.nanoTime();
        long count = ThreadCounter.count0ccurences(students, target);
        long ms = (System.nanoTime() - start) / 1_000_000;

        assertTrue(count >= 1);
        assertTrue(ms < 5000, "Миллион элементов считается слишком долго: " + ms + " мс");
    }

    //Помощник. Поправь порядок аргументов под свой конструктор Student,
    //и если recordBookNumber у тебя long — замени int на long
   public Student newStudent(String groupNumber, double averageScore, int recordBookNumber) {
       Student student1=new Student(groupNumber, averageScore, recordBookNumber);
        return student1 ;
    }
}


