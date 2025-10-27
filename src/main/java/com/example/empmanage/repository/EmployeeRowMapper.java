package com.example.empmanage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.empmanage.domain.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        // System.out.println(i + "件目");
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setAge(rs.getInt("age"));
        employee.setGender(rs.getString("gender"));
        employee.setDepartmentId(rs.getInt("department_id"));
        return employee;
    }
}
