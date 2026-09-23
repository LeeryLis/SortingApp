package model;

import com.sortingapp.model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildTest {
@Test
public void testBuilder(){
    Student student = Student.builder().groupNumber("1488").averageScore(3).recordBookNumber(228).build();
    assertEquals("1488", student.getGroupNumber());
    assertEquals(3, student.getAverageScore());
    assertEquals(228, student.getRecordBookNumber());
}
}
