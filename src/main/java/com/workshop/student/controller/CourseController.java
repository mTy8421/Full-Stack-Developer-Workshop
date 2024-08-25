package com.workshop.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String getAll(ModelMap model) {
        System.out.println("getAll()");

        // List<CourseEntity> course = courseService.getCourseAll();
        // System.out.println("GetAll() Result");
        // System.out.println("Size: " + course.size());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }

    @GetMapping("/{course-id}")
    public String getById(
            ModelMap model,
            @PathVariable(name = "course-id") Integer CourseId) {
        System.out.println("getByAll() : " + CourseId);

        CourseEntity course = courseService.getCourseById(CourseId);
        // System.out.println("getById() Result");
        // System.out.println("Name :" + course.getCourseName());

        model.addAttribute("course", course);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }

    @GetMapping("/delete/{course-id}")
    public String getDeleteById(
            ModelMap model,
            @PathVariable(name = "course-id") Integer CourseId) {
        System.out.println("getDeleteByAll() : " + CourseId);

        // System.out.println("getDeleteById() : Result");
        courseService.deleteCouseById(CourseId);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
            ModelMap model,
            @RequestParam() Map<String, String> param) {
        // System.out.println("Post Insert And Update ID : " + param.get("course-id"));
        // System.out.println("Post Insert And Update Name : " +
        // param.get("course-name"));

        System.out.println("Post Inser And Update : Result");

        CourseEntity course = new CourseEntity();
        if (null != param.get("course-id")) {
            course.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        course.setCourseName(param.get("course-name"));
        course.setCourseDescription(param.get("course-desc"));
        CourseEntity result = courseService.saveCourse(course);
        // System.out.println("Course ID : " + result.getCourseId());
        // System.out.println("Course Name : " + result.getCourseName());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }

}