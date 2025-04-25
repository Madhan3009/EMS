package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingService {


    private EmpAuthImpl empAuth;

    @Autowired
    public OnboardingService(EmpAuthImpl empAuth){
        this.empAuth=empAuth;
    }
    public Employee onboardEmployee(Employee employee) throws MessagingException {
        empAuth.register(employee);
        return employee;
    }
}
