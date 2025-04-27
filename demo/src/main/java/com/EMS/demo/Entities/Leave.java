package com.EMS.demo.Entities;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LeaveRequests")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @Column
    private String leaveType;

    @Column
    private String leaveReason;

    @Column
    private String leaveStatus;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;
}
