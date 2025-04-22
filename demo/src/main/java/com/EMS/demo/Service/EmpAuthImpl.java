package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpAuthImpl {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authMana;

    @Autowired
    private JWTService jwt;

    private EmpAuthImpl(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    public Employee register(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepo.save(employee);
        return employee;
    }

    public String verify(Employee employee){
        Authentication authentication = authMana.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmpFirstname(),employee.getPassword()));
        if(authentication.isAuthenticated()){
            return jwt.generateToken(employee.getEmpFirstname());
        }
        else {
            return "Failed to login";
        }
    }
}
