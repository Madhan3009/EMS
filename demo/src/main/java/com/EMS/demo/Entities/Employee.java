package com.EMS.demo.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "employeeTable"  )
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @Column
    private String empFirstname;

    @Column
    private String empLastname;

    @Column
    private String empEmail;

    @Column
    private LocalDateTime in_time;

    @Column
    private LocalDateTime out_time;

    @Column
    private String empPhone;


    @Column
    private String empAddress;

    @Column
    private String empCity;

    @Column
    private LocalDate contract_start_date;

    @Column
    private LocalDate contract_end_date;

    @Column
    private String password;

    @Column(name = "salaryRupees")
    private int salary;

    @Column
    private String designation;

    @Column
    private Boolean salaryStatus;

    @Column
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "HRid")
    private HR hr;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "employee_roles",joinColumns = @JoinColumn(name = "empId"),inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<RolesAssign> roles;
}
