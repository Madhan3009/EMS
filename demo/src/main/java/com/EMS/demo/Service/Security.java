package com.EMS.demo.Service;

import com.EMS.demo.Entities.Employee;
import com.EMS.demo.Entities.RolesAssign;
import com.EMS.demo.Repositories.EmployeeRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Security implements UserDetailsService {
    public EmployeeRepo employeeRepo;
    public Security(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                employee.getEmail(),
                employee.getPassword(),
                employee.getEnabled(),
                true, true, true,
                getAuthorities(employee));
    }
    public Collection<? extends GrantedAuthority>getAuthorities(Employee employee){
        return employee.getRoles().stream()
                .map(RolesAssign::getRoleName)
                .map(role -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role;
                    }
                })
                .toList();
    }}