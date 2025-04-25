package com.EMS.demo.Service;

import com.EMS.demo.Entities.ConfirmationToken;
import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Repositories.ConfirmationTokenRepo;
import com.EMS.demo.Repositories.EmployeeRepo;
import jakarta.mail.MessagingException;
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

    @Autowired
    private EmailService emailSender;

    @Autowired
    private TokenGeneration tokenGeneration;

    @Autowired
    private ConfirmationToken confirmationToken;

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepo;

    private EmpAuthImpl(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    public Employee register(Employee employee) throws MessagingException {
        if(employeeRepo.findByEmail(employee.getEmpEmail())!=null){
            throw new RuntimeException("Email already exists");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        confirmationToken = tokenGeneration.generateToken();
        employee.setEnabled(false);
        emailSender.sendEmail(employee.getEmpEmail(),"baa@gmail.com",confirmationToken);
        employeeRepo.save(employee);
        return employee;
    }

    public void checkCode(String code){
        Employee emp = new Employee();
        ConfirmationToken token = confirmationTokenRepo.findByConfirmationToken(code);
        emp.setEnabled(token != null);
        employeeRepo.save(emp);
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
