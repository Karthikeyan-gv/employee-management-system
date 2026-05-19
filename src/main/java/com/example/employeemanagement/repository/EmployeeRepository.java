package com.example.employeemanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByName(String name);

    List<Employee> findByGender(String gender);

    List<Employee> findByEmail(String email);

    List<Employee> findByPhone(String phone);

    List<Employee> findBySalary(double salary);
    
}
