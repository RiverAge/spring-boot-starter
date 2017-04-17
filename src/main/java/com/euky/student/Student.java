package com.euky.student;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by euky on 2017/4/17.
 */
public class Student {

    private String name;
    private String gender;
    private int age;
    private float weight;

    private String highSchool;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    @Min(value = 10, message = "你的年龄太小了")
    @Max(value = 100, message = "你的年龄太大了")
    public int getAge() {
        return age;
    }

    @Min(value = 10, message = "你太轻了")
    @Max(value = 200, message = "你太重了")
    public float getWeight() {
        return weight;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    @Override
    public String toString() {
        return "name:->" + name + " gender:->" +
                gender + " age:->" + age + " weight:->" + weight +
                " high school:->" + highSchool;
    }
}
