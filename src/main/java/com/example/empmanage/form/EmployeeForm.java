package com.example.empmanage.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeForm {

    private Integer id;
    @NotBlank(message="名前を入力してください")
    private String name;
    @NotNull(message="年齢を入力してください")
    private Integer age;
    @NotEmpty(message="性別を選択してください")
    private String gender;
    @NotNull(message="部署を選択してください")
    private Integer departmentId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    

}
