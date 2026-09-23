package model;

import com.sortingapp.model.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildTest {
@Test
public void testBuilder(){
    Student student = Student.builder().groupNumber("14367").averageScore(3).recordBookNumber(6267).build();
    assertEquals("14367", student.getGroupNumber());
    assertEquals(3, student.getAverageScore());
    assertEquals(6267, student.getRecordBookNumber());
}
}
