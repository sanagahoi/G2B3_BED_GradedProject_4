package com.greatLearning.employeeService.controller;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.employeeService.entity.Employee;
import com.greatLearning.employeeService.entity.Role;
import com.greatLearning.employeeService.entity.User;
import com.greatLearning.employeeService.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


	private EmployeeService empService;
	
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		empService = employeeService;
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user)
	{
		return empService.saveUser(user);
	}
	
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role)
	{
		return empService.saveRole(role);
	}
	
	//to list all the employees stored in the database.
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		
		return empService.findAll();
	}

	//get an employee record specifically based on the id of that employee
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id) {
		
		Employee emp = empService.findById(id);
		
		if (emp == null) {
			throw new RuntimeException("Employee with  id: " + id + " not found..");
		}
		
		return emp;
	}
	
	// add employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {
		
		emp.setId(0);
		
		empService.save(emp);
		
		return emp;
	}
	
	// to update an existing employee record with the given updated json object.
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee emp) {
		
		empService.save(emp);
		
		return emp;
	}
	
	// delete an existing employee record based on the id of the employee.
	
	@DeleteMapping("/employees/{id}")

	public String deleteEmployee(@PathVariable int id) {
		
		Employee tempEmployee = empService.findById(id);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee for deletion with id: " + id + " not found..");
		}
		
		empService.deleteById(id);
		
		return "Employee with id - " + id +" hes been deleted";
	}
	
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName)
	{
		return empService.searchByFirstName(firstName);
	}
	
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam(name = "order") String order){
		
		return empService.sortByFirstName(order);
	}
	
}










