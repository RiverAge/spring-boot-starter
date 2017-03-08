package com.euky.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by euky on 2017/3/7.
 */

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/topics/{id}/courses", method = RequestMethod.GET)
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/topics/{topicId}/course/{id}", method = RequestMethod.GET)
    public Course getCourse(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @RequestMapping(value = "/topics/{topicId}/courses/{id}", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return course;
    }

    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    public Course updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return course;
    }

    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public void delCourse(@PathVariable String id) {
         courseService.delCourse(id);
    }

}
