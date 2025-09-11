package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.controller;


import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("employees")        // parent url for all the  mapping field eg -> /employees/getSecretMessage  or eg -> /employees/{employeeId}
public class EmployeeController {


    @GetMapping(path = "/getSecretMessage")
    public String getSecretMessage(){
        return "Secret message: Hey how are you";
    }



    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        // name field in PathVariable we can change the original name of field whatever we want

        return new EmployeeDTO(id, "Rohit", "rohit@gmail.com", 23, LocalDate.of(2026, 1, 28), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        // in RequestParams if required is false the field can be neglected

        return "Hi your age is " + age + " " + sortBy;

    }

    @PostMapping
    public EmployeeDTO createNewEmployee (@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "Hello from PUT mapping";
    }

}
