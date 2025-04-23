package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class PayrollService{
    @Autowired
    private EmployeeRepo employeeRepo;



    public boolean isendofMonth()
    {
        LocalDate date = LocalDate.now();
        LocalDate lastDay=date.with(TemporalAdjusters.lastDayOfMonth());
        return date.equals(lastDay);
    }

    public Employee creditSalary(Employee employee){
        employee.setStatus(true);
        return employeeRepo.save(employee);
    }

    public Employee credit(Employee employee){
        if(isendofMonth()){
            creditSalary(employee);
        }
        else{
            return employee;
        }
        return employee;
    }




}
