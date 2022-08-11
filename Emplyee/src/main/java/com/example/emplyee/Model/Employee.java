package com.example.emplyee.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {

    @NotEmpty(message="id can't be empty")
    @Min(value=3, message="ID must be more than 2 char")
    private String id;

    @NotEmpty(message="name can't be empty")
    @Size(min=5,message="Name must be more than 4 char")
    private String name;

    @NotEmpty(message="age can't be null")
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]",message="Age must be a number")
    @Min(value=26 ,message="Age must be more than 25")
    private String age;

    @AssertFalse(message="On leave must be false")
    private boolean onLeave;

    @NotEmpty(message="The employment year can't be null")
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]",message="The employment year must be a number")
    @Pattern(regexp = "^(19|20)\\d{2}$",message="The employment year must be a valid year")
    private String employmentYear;

    @NotEmpty(message="The annual leave can't be null")
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]|[0]",message="The annual leave must be a number")
    private String annualLeave;


}
