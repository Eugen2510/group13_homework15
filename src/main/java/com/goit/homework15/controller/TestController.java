package com.goit.homework15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ModelAndView showTestHtml(@RequestParam(value = "name", required = false) String name){
        ModelAndView model = new ModelAndView("test");
        model.addObject("name", name);
        return model;
    }
}
