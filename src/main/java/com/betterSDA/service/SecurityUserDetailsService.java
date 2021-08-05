package com.betterSDA.service;

import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final PersonRepo personRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PersonEntity userEntity = personRepo.findById(UUID.fromString(s))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole())));
    }
}
