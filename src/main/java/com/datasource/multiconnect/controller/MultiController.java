package com.datasource.multiconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datasource.multiconnect.model.Employee;
import com.datasource.multiconnect.model.Retailer;
import com.datasource.multiconnect.model.Student;
import com.datasource.multiconnect.service.MultipleDaoService;

@RestController
@RequestMapping("/")
public class MultiController {
	
	@Autowired
	MultipleDaoService multipleDaoService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return multipleDaoService.getAllEmployees("Primary");
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> addEmployee(@RequestBody Employee emp ) {
		try {
			multipleDaoService.addEmployee("Primary", emp);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return multipleDaoService.getAllStudents("Secondary");
	}
	
	@GetMapping("/retailers")
	public List<Retailer> getAllRetailers() {
		return multipleDaoService.getAllRetailers("Tertiary");
	}

}
