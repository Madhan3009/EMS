package com.EMS.demo.Service;

import com.EMS.demo.DTO.AuthResponse;
import com.EMS.demo.DTO.EmployeeDTO;
import com.EMS.demo.DTO.LoginReq;
import com.EMS.demo.Entities.ConfirmationToken;
import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Repositories.ConfirmationTokenRepo;
import com.EMS.demo.Repositories.EmployeeRepo;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
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
    private ConfirmationTokenRepo confirmationTokenRepo;

    public EmpAuthImpl(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    @Transactional
    public void register(Employee employee) throws MessagingException {
        if(employeeRepo.findByEmail(employee.getEmail())!=null){
            throw new RuntimeException("Email already exists");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        ConfirmationToken confirmationToken = tokenGeneration.generateToken();
        employee.setEnabled(false);
        emailSender.sendEmail(employee.getEmail(),"baa@gmail.com", confirmationToken);
        employeeRepo.save(employee);
    }

    @Transactional
    public void checkCode(String Email,StringBuilder code){
        Employee emp = employeeRepo.findByEmail(Email);
        ConfirmationToken token = confirmationTokenRepo.findByConfirmationToken(code);
        if(token==null || token.getEmployee().getEmpId()!=emp.getEmpId() || token.getEmployee().getEnabled()==true){
                throw new RuntimeException("Invalid code");
        }
        emp.setEnabled(true);
        confirmationTokenRepo.delete(token);
        employeeRepo.save(emp);
    }
    @Transactional
    public String verify(Employee employee){
        Authentication authentication = authMana.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(),employee.getPassword()));
        if(authentication.isAuthenticated()){
            return jwt.generateToken(employee.getEmpFirstname());
        }
        else {
            return "Redirect to login page";
        }
    }


        public AuthResponse login(LoginReq loginReq){
            Authentication authentication = authMana.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(),loginReq.getPassword()));
            if(!authentication.isAuthenticated()){
                throw new RuntimeException("Invalid credentials");
            }
            Employee employee = employeeRepo.findByEmail(loginReq.getEmail());
            if (employee.getEnabled()==false){
                throw new RuntimeException("User not enabled so kindly check your email");
            }
            String token = jwt.generateToken(employee.getEmpFirstname());
            return new AuthResponse(token,employee.getEmpId(),employee.getEmpFirstname(),employee.getRoles());
        }

}
