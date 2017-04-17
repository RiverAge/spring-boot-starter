package com.euky.student;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by euky on 2017/4/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void successCreateStudent() throws Exception{
        Student student = new Student();
        student.setWeight(120);
        student.setAge(12);
        student.setName("name");
        student.setGender("male");
        Student ret = testRestTemplate.postForObject("/students", student, Student.class);
        Assert.assertEquals(ret.getWeight(), 120.0, 0.1);
        Assert.assertEquals(ret.getName(), "name");
        Assert.assertEquals(ret.getGender(), "male");
        Assert.assertNull(student.getHighSchool());
        Assert.assertEquals(student.getAge(), 12);
    }

    @Test
    public void shouldReturnTooYoungOrTooLight() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Set expected = new HashSet();
        expected.add("你的年龄太小了");
        expected.add("你太轻了");

        Assert.assertFalse((Boolean)ret.get("result"));
        Assert.assertTrue(expected.contains(ret.get("msg")));
    }

    @Test
    public void shouldReturnTooYoung() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        student.setAge(1);
        student.setWeight(120);
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Assert.assertFalse((Boolean)ret.get("result"));
        Assert.assertEquals("你的年龄太小了",ret.get("msg"));
    }

    @Test
    public void shouldReturnTooLight() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        student.setAge(18);
        student.setWeight(1);
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Assert.assertFalse((Boolean)ret.get("result"));
        Assert.assertEquals("你太轻了",ret.get("msg"));
    }

    @Test
    public void shouldReturnTooHeavy() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        student.setAge(18);
        student.setWeight(201);
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Assert.assertFalse((Boolean)ret.get("result"));
        Assert.assertEquals("你太重了",ret.get("msg"));
    }

    @Test
    public void shouldReturnTooOld() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        student.setAge(101);
        student.setWeight(200);
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Assert.assertFalse((Boolean)ret.get("result"));
        Assert.assertEquals("你的年龄太大了",ret.get("msg"));
    }

    @Test
    public void shouldReturnTooOldOrTooHeavy() {
        Student student = new Student();
        student.setName("name");
        student.setGender("male");
        student.setAge(101);
        student.setWeight(201);
        Map ret = testRestTemplate.postForObject("/students", student, Map.class);

        Set expected = new HashSet();
        expected.add("你的年龄太大了");
        expected.add("你太重了");

        Assert.assertFalse((Boolean) ret.get("result"));
        Assert.assertTrue(expected.contains(ret.get("msg")));
    }
}
