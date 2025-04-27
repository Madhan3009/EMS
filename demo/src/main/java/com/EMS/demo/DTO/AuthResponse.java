package com.EMS.demo.DTO;

import com.EMS.demo.Entities.RolesAssign;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Long empId;
    private String email;
    private String Name;
    private Collection<? extends GrantedAuthority> roles;

    public AuthResponse(String token, int empId, String empFirstname, List<RolesAssign> roles) {
    }
}
