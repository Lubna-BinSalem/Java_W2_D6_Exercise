package com.example.emplyee.Controller;

import com.example.emplyee.Model.ApiResponse;
import com.example.emplyee.Model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class EmployeeController {

    ArrayList<Employee> employeeList= new ArrayList<Employee>();

    @GetMapping("/employee")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(employeeList);
    }

    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        employeeList.add(employee);
        return ResponseEntity.status(201).body( new ApiResponse("New user added !",201));
    }

    @PutMapping("/employee/{index}")
    public ResponseEntity updateUser(@RequestBody @Valid Employee myUser
            ,@PathVariable int index,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }
        if(index>=employeeList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        employeeList.set(index,myUser);
        return ResponseEntity.status(201).body( new ApiResponse("User updated !",201));
    }

    @DeleteMapping("/employee/{index}")
    public ResponseEntity deleteUser(@PathVariable int index){
        if(index>=employeeList.size()){
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index",400));
        }
        employeeList.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted !",200));
    }

    @PostMapping(path="/employee/leave")
    public ResponseEntity ApplyLeave(@RequestBody String id){
        for (int i = 0; i < employeeList.size(); i++) {
        if(employeeList.get(i).getId().equals(id)){
            if(employeeList.get(i).isOnLeave()==true){
            return ResponseEntity.status(400).body(new ApiResponse("Now currently on leave",400));
            }else if (employeeList.get(i).getAnnualLeave().equals("0")){
                return ResponseEntity.status(400).body(new ApiResponse("You have no leaves left",400));
            }else {
                employeeList.get(i).setOnLeave(true);
                employeeList.get(i).setAnnualLeave( Integer.toString(Integer.parseInt(employeeList.get(i).getAnnualLeave())-1));
                return ResponseEntity.status(201).body(new ApiResponse("on leave application successful",201));
            }
        }// end if id
        }//end loop
        return ResponseEntity.status(400).body(new ApiResponse("Unable to find employee",400));

    }
}
