package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Entities.RolesAssign;
import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Security implements UserDetailsService {
    public EmployeeRepo userRepo;
    public Security(EmployeeRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        else{
            return new org.springframework.security.core.userdetails.User(
                    user.getEmpEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles())
            );
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<RolesAssign> roles){
        Collection<? extends GrantedAuthority> maproles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return maproles;
    }}