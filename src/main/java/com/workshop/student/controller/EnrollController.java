package com.workshop.student.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.student.entity.CourseEntity;
import com.workshop.student.entity.EnrollEntity;
import com.workshop.student.entity.StudentEntity;
import com.workshop.student.service.CourseService;
import com.workshop.student.service.EnrollService;
import com.workshop.student.service.StudentService;

/**
 * EnrollController
 */
@Controller
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollService enrollService;

    @GetMapping({ "", "/" })
    public String getAll(ModelMap model) {
        System.out.println("getAll()");

        // List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        // System.out.println("getAll() result");
        // System.out.println("Size: " + enrolls.size());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "enroll/index";
    }

    @GetMapping("/{student-id}")
    public String getById(
            ModelMap model,
            // @PathVariable(name = "enroll-id") Integer EnrollId
            @PathVariable(name = "student-id") Integer StudentId) {
        // System.out.println("getByAll() : ------------------------ " + EnrollId);
        // System.out.println("getByAll() : ------------------------ " + StudentId);

        // EnrollEntity entity = enrollService.getEnrollById(EnrollId);
        System.out.println("------------------------- getByAll() : Result ------------------------- ");
        // System.out.println("Course Name : " + entity.getCourse().getCourseName());
        // System.out.println("Student First Name : " +
        // entity.getStudent().getStudentFirstName());
        // System.out.println("Student Last Name : " +
        // entity.getStudent().getStudentLastName());

        StudentEntity entity = studentService.getStudentById(StudentId);
        model.addAttribute("student", entity);

        List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        List<EnrollEntity> resultList = enrolls.stream()
                .filter(enroll -> enroll.getStudent().getStudentId() == StudentId).collect(Collectors.toList());
        model.addAttribute("enrolls", resultList);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "enroll/index";
    }

    @GetMapping("/delete/{enroll-id}")
    public String getDeleteById(
            ModelMap model,
            @PathVariable(name = "enroll-id") Integer EnrollId) {
        // System.out.println("getDeleteByAll() : " + EnrollId);

        System.out.println("getDeleteByAll() : Result");
        enrollService.deleteEnrollById(EnrollId);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "enroll/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
            ModelMap model,
            @RequestParam() Map<String, String> param) {
        // System.out.println("Post Insert And Update ID : " + param.get("enroll-id"));
        // System.out.println("Post Insert And Update Name : " +
        // param.get("enroll-name"));

        // System.err.println("Post Insert And Update Result");
        Integer courseId = Integer.parseInt(param.get("course-id"));
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        // System.err.println("Course ID : " + courseEntity.getCourseId());

        Integer studentId = Integer.parseInt(param.get("student-id"));
        StudentEntity studentEntity = studentService.getStudentById(studentId);
        System.err.println("Student ID: " + studentEntity.getStudentId());

        EnrollEntity entity = new EnrollEntity();
        if (null != param.get("eroll-id")) {
            entity.setEnrollId(Integer.parseInt(param.get("enroll-id")));
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        // System.err.println("Enroll ID: " + result.getEnrollId());
        // System.err.println("Course Name: " + result.getCourse().getCourseName());
        // System.err.println("Student Code: " + result.getStudent().getStudentCode());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "enroll/index";
    }

}