package com.dojo.jdbchistory.domain.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public EmployeeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Employee> findAll() {
		return jdbcTemplate.query("select * from Employee", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	public List<Employee> findByEmpNm(String empNm) {
		return jdbcTemplate.query("select * from Employee where emp_nm like '%'||?||'%'", new Object[] { empNm },
				new BeanPropertyRowMapper<Employee>(Employee.class));
	}

}
