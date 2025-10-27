package com.example.empmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.empmanage.domain.Employee;
import com.example.empmanage.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("employeeList", service.findAll());
        return "employee/list";
    }
}
