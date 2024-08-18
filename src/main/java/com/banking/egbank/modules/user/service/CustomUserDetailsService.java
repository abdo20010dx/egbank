package com.banking.egbank.modules.user.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.egbank.modules.user.entities.UserEntity;
import com.banking.egbank.modules.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        System.out.println("User: __________________" + user);

        if (user == null || user.getEmail() == null) {
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("User found: " + user.getEmail());

        // Ensure roles are not null and print them
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User roles cannot be null or empty");
        }

        // Debugging the roles and authorities
        user.getRoles().forEach(role -> {
            System.out.println("Role: " + role.getName());
        });

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles()
                        .stream()
                        .map(role -> {
                            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                            System.out.println("Granted Authority: " + authority.getAuthority());
                            return authority;
                        })
                        .collect(Collectors.toList()))
                .build();
    }
}
