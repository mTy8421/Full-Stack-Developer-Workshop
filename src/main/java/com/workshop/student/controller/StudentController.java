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

import com.workshop.student.entity.FacultyEntity;
import com.workshop.student.entity.StudentEntity;
import com.workshop.student.service.FacultyService;
import com.workshop.student.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping({ "", "/" })
    public String getAll(ModelMap model) {

        System.out.println("getAll()");

        // List<StudentEntity> student = studentService.getStudentAll();
        // System.out.println("GetAll() Result");
        // System.out.println("Size: " + student.size());

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "student/index";
    }

    @GetMapping("/{student-id}")
    public String getById(
            ModelMap model,
            @PathVariable(name = "student-id") Integer StudentID) {
        System.out.println("getById() : " + StudentID);

        StudentEntity student = studentService.getStudentById(StudentID);

        // System.out.println("-------------------- getById() Result
        // ------------------------------------- ");
        // System.out.println("First Name : " + student.getStudentFirstName());
        // System.out.println("Last Name : " + student.getStudentFirstName());
        model.addAttribute("student", student);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        return "student/index";
    }

    @GetMapping("/delete/{student-id}")
    public String getDeleteById(
            ModelMap model,
            @PathVariable(name = "student-id") Integer StudentID) {
        System.out.println("getDelteByID : " + StudentID);

        // System.out.println("getDeleteById() : Result");
        studentService.deleteStudentById(StudentID);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        return "student/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
            ModelMap model,
            @RequestParam() Map<String, String> param) {
        // System.out.println("Post Insert And Update Id : " + param.get("student-id"));
        // System.out.println("Post Insert And Update FName : " +
        // param.get("student-fname"));
        // System.out.println("Post Insert And Update LName : " +
        // param.get("student-lname"));

        System.out.println("Post Inser And Update : Result");

        Integer facultyId = Integer.parseInt(param.get("faculty-id"));
        FacultyEntity facultyEntity = facultyService.getFacultyById(facultyId);
        // System.out.println(facultyEntity.getFacultyId());

        StudentEntity student = new StudentEntity();
        if (null != param.get("student-id")) {
            student.setStudentId(Integer.parseInt(param.get("student-id")));
        }
        student.setStudentCode(param.get("student-code"));
        student.setStudentFirstName(param.get("student-fname"));
        student.setStudentLastName(param.get("student-lname"));
        student.setFaculty(facultyEntity);
        StudentEntity result = studentService.saveStudent(student);
        // System.out.println("Student ID : " + result.getStudentId());
        // System.out.println("Student Frist Name : " + result.getStudentFirstName());
        // System.out.println("Student last Name : " + result.getStudentLastName());

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        return "student/index";
    }

}
