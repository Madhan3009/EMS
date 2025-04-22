package com.EMS.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HRTABLE")
public class HR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int HRid;

    @Column
    private String HRName;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "hr")
    private List<Employee> employees;
}
