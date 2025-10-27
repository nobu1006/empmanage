package com.example.empmanage.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.empmanage.domain.Department;

@Repository
public class DepartmentRepository {

    private static final RowMapper<Department> DEP_ROW_MAPPER
        = new DepartmentRowMapper();

    @Autowired
    private NamedParameterJdbcTemplate template;

    public Department load(Integer id) {
        String sql = """
            select
                  id
                , name
            from departments
            where
                id = :id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return template.queryForObject(sql, param, DEP_ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Department> findAll() {
        String sql = """
            select
                  id
                , name
            from departments
                """;
        return template.query(sql, DEP_ROW_MAPPER);
    }

    public Department save(Department department) {
        SqlParameterSource param =
            new BeanPropertySqlParameterSource(department);
        if (department.getId() == null) {
            String sql = """
                insert into departments (
                    name
                ) values (
                    :name
                ) returning id
            """;
            Integer id = template.queryForObject(sql, param, Integer.class);
            department.setId(id);
        } else {
            String sql = """
                update department set
                    name = :name
                were
                    id = :id
            """;
            template.update(sql, param);
        }
        return department;
    }

    public void deleteById(Integer id) {
        String sql = """
            delete from departments
            where
                id = :id
        """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
