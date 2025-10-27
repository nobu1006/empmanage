package com.example.empmanage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.empmanage.domain.Department;

public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));
        return department;
    }

}
