package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.controller;


import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.dto.EmployeeDTO;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.entities.EmployeeEntity;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.repositories.EmployeeRepository;
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

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // name field in PathVariable we can change the original name of field whatever we want
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        // in RequestParams if required is false the field can be neglected
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee (@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello from PUT mapping";
    }

}
