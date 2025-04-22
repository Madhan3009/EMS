package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Service
public class PayrollService{
    @Autowired
    private EmployeeRepo employeeRepo;



    public LocalDate isendofMonth(Employee employee)
    {
        LocalDateTime today = employee.getOut_time();
        LocalDate date = today.toLocalDate();

        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public LocalDate isstartofMonth(Employee employee)
    {
        LocalDateTime today = employee.getIn_time();
        LocalDate date = today.toLocalDate();

        return date.with(TemporalAdjusters.firstDayOfMonth());
    }





}
