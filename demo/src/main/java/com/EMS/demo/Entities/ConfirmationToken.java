package com.EMS.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Builder
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeeTable")

public class ConfirmationToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private StringBuilder confirmationToken;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "empId")
    private Employee employee;

    @Column(name = "enabled")
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
