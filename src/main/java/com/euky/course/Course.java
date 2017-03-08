package com.euky.course;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by euky on 2017/3/7.
 */

@Entity
public class Course {

    @Id
    private String id;

    private String name;

    private String description;

    public Course(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Course() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
