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

import com.workshop.student.entity.FacultyEntity;
import com.workshop.student.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping({ "", "/" })
    public String getAll() {
        System.out.println("GetAll()");
        List<FacultyEntity> faculty = facultyService.getFacultyAll();
        System.out.println("GetAll() Result");
        System.out.println("Size: " + faculty.size());
        return "index";
    }

    @GetMapping("/{facuty-id}")
    public String getById(
            @PathVariable(name = "facuty-id") Integer facutyID) {
        System.out.println("getById() : " + facutyID);

        FacultyEntity entity = facultyService.getFacultyById(facutyID);
        System.out.println("getById() Result");
        System.out.println("Faculty Name " + entity.getFacultyName());
        return "index";
    }

    @GetMapping("/delete/{facuty-id}")
    public String getDeleteById(
            @PathVariable(name = "facuty-id") Integer facutyID) {
        System.out.println("getDeleteById() : " + facutyID);

        System.out.println("getDeleteById() : Result");
        facultyService.deleteFucltyById(facutyID);

        return "index";
    }

    @PostMapping("/")
    public String postInserAndUpdate(
            @RequestParam() Map<String, String> param) {
        System.out.println("Post Inser And Update");
        System.out.println("Post Inser And Update ID : " + param.get("faculty-id"));
        System.out.println("Post Inser And Update Name : " + param.get("faculty-name"));

        System.out.println("Post Inser And Update : Result");
        FacultyEntity entity = new FacultyEntity();
        if (null != param.get("faculty-id")) {
            entity.setFacultyId(Integer.parseInt(param.get("faculty-id")));
        }
        entity.setFacultyName(param.get("faculty-name"));
        FacultyEntity result = facultyService.saveFaculty(entity);
        System.out.println("Faculty ID : " + result.getFacultyId());
        System.out.println("Faculty Name : " + result.getFacultyName());

        return "index";
    }
}
