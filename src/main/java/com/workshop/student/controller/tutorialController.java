package com.workshop.student.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * tutorialController
 */
@Controller
@RequestMapping("/tutorial")
public class tutorialController {
    @GetMapping({ "", "/" })
    public String getTutorial(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id) {
        System.out.println("ID: " + id);
        return "template";
    }

    @GetMapping("/{id}")
    public String getTutorialPath(
            @PathVariable(name = "id") Integer id) {
        System.out.println("ID: " + id);
        return "index";

    }

    // @PostMapping("/")
    // public String postTutorial(
    // @RequestParam(name = "id", required = false, defaultValue = "0") Integer id)
    // {
    // System.out.println("ID: " + id);

    // return "index";
    // }

    @PostMapping("/")
    public String postTutorial(
            @RequestParam() Map<String, String> param) {
        System.out.println("Post ID: " + param.get("id"));
        System.out.println("Post Code: " + param.get("code"));
        return "index";
    }

}