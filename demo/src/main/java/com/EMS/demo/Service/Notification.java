package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import org.springframework.stereotype.Service;

@Service
public class Notification {

    public void checkStatus(Employee employee){
        if(employee.getEnabled() == false){
            System.out.println("Employee not enabled,So Services are not available");
        }
    }

}
