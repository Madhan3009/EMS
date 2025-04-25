package com.EMS.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "employeeTable"  )
public class ConfirmationToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private StringBuilder confirmationToken;

    @Column(name = "enabled")
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private Boolean isenabled(){
        return enabled;
    }
}
