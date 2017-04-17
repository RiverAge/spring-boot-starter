package com.euky.student;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by euky on 2017/4/17.
 */
public class StudentTest {

    @Test
    public void testToString() {
        Student student = new Student();
        student.setAge(12);
        student.setGender("male");
        student.setHighSchool("New Orc");
        student.setName("Wisthon");
        student.setWeight(60);

        String expected = "name:->Wisthon gender:->male age:->12 weight:->60.0 high school:->New Orc";

        Assert.assertEquals(student.toString(), expected);

    }

}
