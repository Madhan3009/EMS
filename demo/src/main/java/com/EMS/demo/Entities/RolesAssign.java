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
public class RolesAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column
    private String roleName;

    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;
}
