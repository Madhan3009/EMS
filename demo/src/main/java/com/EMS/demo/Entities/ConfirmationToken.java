package com.EMS.demo.Entities;

import jakarta.persistence.*;

import java.util.Date;

public class ConfirmationToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
