package com.datasource.multiconnect.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.datasource.multiconnect.model.Employee;
import com.datasource.multiconnect.model.Retailer;
import com.datasource.multiconnect.model.Student;
import com.datasource.multiconnect.repository.EmployeeRepository;
import com.datasource.multiconnect.repository.RetailerRepository;
import com.datasource.multiconnect.repository.StudentRepository;

@Service
public class MultipleDaoService {
	
	@Autowired
	ApplicationContext applicationContext;
	
	private SqlSessionTemplate getSqlSessionTemplate(String datasourceName) {
		return (SqlSessionTemplate) applicationContext.getBean("sqlSessionTemplate"+datasourceName);
	}
	
	public List<Employee> getAllEmployees(String datasourceName) {
		EmployeeRepository mapper=getSqlSessionTemplate(datasourceName).getMapper(EmployeeRepository.class);
		return mapper.findAll();
	}
	
	public List<Student> getAllStudents(String datasourceName) {
		StudentRepository mapper=getSqlSessionTemplate(datasourceName).getMapper(StudentRepository.class);
		return mapper.findAllStudents();
	}
	
	public List<Retailer> getAllRetailers(String datasourceName) {
		RetailerRepository mapper = getSqlSessionTemplate(datasourceName).getMapper(RetailerRepository.class);
		return mapper.findAllRetailers();
	}

}
