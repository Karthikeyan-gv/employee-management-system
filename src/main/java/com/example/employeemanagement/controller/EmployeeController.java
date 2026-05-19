package com.example.employeemanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;

@Controller
// @RestController("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/find-emp")
    public String findEmployee() {
        return "find-employee";
    }

    @GetMapping("/find-employee")
    public String getEmployee( Model model,@RequestParam(required = false) String id , @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender, @RequestParam(required = false) String email,
@RequestParam(required = false) String phone, @RequestParam(required = false) String salary) {
                
                List<Employee> employees=employeeService.get(id, name, gender, email, phone, salary);
                model.addAttribute("employees", employees);
                return "find-employee";
    }

    @GetMapping("/add-employee")
    public String addEmployeePage(Model model){
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.add(employee);
        return "redirect:/employees";
    }

     @GetMapping("/update-employee/{id}")
    public String updateEmployeePage(Model model,@PathVariable long id){
        model.addAttribute("employee", employeeService.getById(id));
        return "update-employee";
    }

    @PostMapping("/employee/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.update(employee);
        return "redirect:/employees";
    } 

    @PostMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
