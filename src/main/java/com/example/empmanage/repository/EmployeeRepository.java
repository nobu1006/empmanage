package com.example.empmanage.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.empmanage.domain.Employee;

@Repository
public class EmployeeRepository {

    private static final RowMapper<Employee> EMP_ROW_MAPPER
       = new EmployeeRowMapper();

    @Autowired
    private NamedParameterJdbcTemplate template;

    // 主キーでselect
    public Employee load(Integer id) {
        String sql = """
                select
                      id
                     ,name
                     ,age
                     ,gender
                     ,department_id
                from employees
                where
                    id = :id
                """;
        SqlParameterSource param
          = new MapSqlParameterSource().addValue("id", id);

        try {
            return template.queryForObject(sql, param, EMP_ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 全件select
    public List<Employee> findAll() {
        String sql = """
                select
                     id
                    ,name
                    ,age
                    ,gender
                    ,department_id
                from employees
                """;
        return template.query(sql, EMP_ROW_MAPPER);
    }

    // insert or update
    public Employee save(Employee employee) {
        SqlParameterSource param =
         new BeanPropertySqlParameterSource(employee);

        String sql = "";
        if (employee.getId() == null) {
            sql = """
                insert into employees (
                    name
                    ,age
                    ,gender
                    ,department_id )
                values (
                    :name
                    ,:age
                    ,:gender
                    ,:departmentId ) returning id
                """;
            Integer id = template.queryForObject(sql, param, Integer.class);
            employee.setId(id);
        } else {
            sql = """
                update employees set
                    name = :name
                    , age = :age
                    , gender = :gender
                    , department_id = :departmentId
                where
                    id = :id
                """;
            template.update(sql, param);
        }
        return employee;
    }

    // delete
    public void deleteById(Integer id) {
        String sql = """
                    delete from employees
                    where
                    id = :id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
