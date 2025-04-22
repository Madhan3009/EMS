package com.EMS.demo.Service;


//Payroll Service
//Onboarding Service
//Sending an offer letter to employees
//Employee Service tracking

import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;


}
