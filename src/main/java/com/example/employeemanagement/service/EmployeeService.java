package com.example.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String home() {
        return "index";
    }

    public List<Employee> get(String id, String name, String gender, String email, String phone, String salary) {

        List<Employee> employees=new ArrayList<>();

        if (!id.isBlank()) {
            employeeRepository.findById(Long.parseLong(id)).ifPresent(employees::add);
        }
        else if (!name.isBlank()) {
            employees.addAll(employeeRepository.findByName(name));            
        }
        else if (!gender.isBlank()) {
            employees.addAll(employeeRepository.findByGender(gender));            
        }
        else if (!email.isBlank()) {
            employees.addAll(employeeRepository.findByEmail(email));            
        }
        else if (!phone.isBlank()) {
            employees.addAll(employeeRepository.findByPhone(phone));            
        }
        else if (!salary.isBlank()) {
             employees.addAll(employeeRepository.findBySalary(Double.parseDouble(salary)));  
        }
        return employees;
    }

    public Employee getById(long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Eployee not fount with id: "+id));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee add(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
}
