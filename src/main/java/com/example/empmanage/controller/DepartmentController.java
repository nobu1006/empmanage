package com.example.empmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.empmanage.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @RequestMapping("/list")
    public String execute(Model mode) {
        mode.addAttribute("departmentList", service.findAll());
        return "department/list";
    }

    @RequestMapping("/detail")
    public String detail(Integer id, Model model) {
        model.addAttribute("department", service.load(id));
        return "department/detail";
    }
}
