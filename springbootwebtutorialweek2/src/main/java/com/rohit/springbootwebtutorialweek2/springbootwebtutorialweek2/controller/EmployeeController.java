package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.controller;


import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.dto.EmployeeDTO;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){      // name field in PathVariable we can change the original name of field whatever we want
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.
                map(employeeDTO1 ->
                ResponseEntity.ok(employeeDTO1)).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy){
        // in RequestParams if required is false the field can be neglected
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee (@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping( path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployee(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
