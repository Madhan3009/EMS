package com.EMS.demo.Controller;

import com.EMS.demo.DTO.EmployeeDTO;
import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Service.EmpAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    public EmpAuthImpl empAuth;

    @Autowired
    private Employee employee;

    public AuthController(EmpAuthImpl empAuth,Employee employee){
        this.empAuth= empAuth;
        this.employee=employee;
    }

    @GetMapping("/register")
    public ResponseEntity<Employee> register(Employee employee){
        try{
            empAuth.register(employee);
            return ResponseEntity.ok(employee);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register/save")
    public ResponseEntity<EmployeeDTO> save(EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeDTO);
    }
}
