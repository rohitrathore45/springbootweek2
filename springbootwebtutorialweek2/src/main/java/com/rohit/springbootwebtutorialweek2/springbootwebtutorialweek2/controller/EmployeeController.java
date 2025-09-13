package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.controller;


import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.dto.EmployeeDTO;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.entities.EmployeeEntity;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.repositories.EmployeeRepository;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("employees")        // parent url for all the  mapping field eg -> /employees/getSecretMessage  or eg -> /employees/{employeeId}
public class EmployeeController {


    @GetMapping(path = "/getSecretMessage")
    public String getSecretMessage(){
        return "Secret message: Hey how are you";
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // name field in PathVariable we can change the original name of field whatever we want
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        // in RequestParams if required is false the field can be neglected
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee (@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello from PUT mapping";
    }

}
