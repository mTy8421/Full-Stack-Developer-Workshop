package com.workshop.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.student.entity.CourseEntity;
import com.workshop.student.service.CourseService;

/**
 * CourseController
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping({ "", "/" })
    public String getAll() {
        System.out.println("getAll()");

        List<CourseEntity> course = courseService.getCourseAll();
        System.out.println("GetAll() Result");
        System.out.println("Size: " + course.size());

        return "index";
    }

    @GetMapping("/{course-id}")
    public String getById(
            @PathVariable(name = "course-id") Integer CourseId) {
        System.out.println("getByAll() : " + CourseId);

        CourseEntity course = courseService.getCourseById(CourseId);
        System.out.println("getById() Result");
        System.out.println("Name :" + course.getCourseName());

        return "index";
    }

    @GetMapping("/delete/{course-id}")
    public String getDeleteById(
            @PathVariable(name = "course-id") Integer CourseId) {
        System.out.println("getDeleteByAll() : " + CourseId);

        System.out.println("getDeleteById() : Result");
        courseService.deleteCouseById(CourseId);

        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
            @RequestParam() Map<String, String> param) {
        System.out.println("Post Insert And Update ID : " + param.get("course-id"));
        System.out.println("Post Insert And Update Name : " + param.get("course-name"));

        System.out.println("Post Inser And Update : Result");

        CourseEntity course = new CourseEntity();
        if (null != param.get("course-id")) {
            course.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        course.setCourseName(param.get("course-fname"));
        course.setCourseDescription(param.get("course-lname"));
        CourseEntity result = courseService.saveCourse(course);
        System.out.println("Student ID : " + result.getCourseId());
        System.out.println("Student Frist Name : " + result.getCourseName());

        return "index";
    }

}