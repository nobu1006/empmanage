package com.example.empmanage.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.empmanage.domain.Department;
import com.example.empmanage.domain.Employee;
import com.example.empmanage.form.EmployeeForm;
import com.example.empmanage.service.DepartmentService;
import com.example.empmanage.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute
    public EmployeeForm setuEmployeeForm() {
        return new EmployeeForm();
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("employeeList", employeeService.findAll());
        return "employee/list";
    }

    @RequestMapping("/detail")
    public String detail(Integer id, Model model) {
        model.addAttribute("employee", employeeService.load(id));
        return "employee/detail";
    }

    @RequestMapping("/toAddForm")
    public String toAddForm(Model model) {
        model.addAttribute("depMap", convertList2Map(departmentService.findAll()));
        return "employee/addForm";
    }

    @RequestMapping("/add")
    public String add(
            @Validated EmployeeForm form
            , BindingResult result
            , Model model) {

        if (result.hasErrors()) {
            return toAddForm(model);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(form, employee);
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
    /**
     * リストのまま画面にもっていってもおそらくプルダウンメニューは作れるが
     * 一応習った通りマップにして、マップをth:eachで回す構造にしたいので
     * リストからマップへの変換処理作成
     * 
     * @param depList
     * @return
     */
    private Map<Integer, String> convertList2Map(List<Department> depList) {
        Map<Integer, String> departmentMap = new LinkedHashMap<>();
        for (Department dep : depList) {
            departmentMap.put(dep.getId(), dep.getName());
        }
        return departmentMap;
    }
}
