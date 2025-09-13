package com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rohit.springbootwebtutorialweek2.springbootwebtutorialweek2.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in the name should in the range: {3, 10}")
    private String name;

    @Email(message = "Email should be valid email")
    private String email;

    @NotNull(message = "Age of the employee should not be null")
    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee should not be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of the Employee can be USER or  ADMIN")
    @EmployeeRoleValidation
    private String role;   //ADMIN, USER

    @NotNull(message = "Salary of Employee should be not null")
    @Positive(message = "Salary of Employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form of XXXXXX.YY")
    @DecimalMin(value = "10000.00")
    @DecimalMax(value = "10000000.99")
    private Double salary;

    @PastOrPresent(message = "Date of joining field in Employee cannot be in future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be Active")
    private Boolean isActive;
}
