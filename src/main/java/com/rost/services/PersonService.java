package com.rost.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rost.repositories.PersonRepository;
import com.rost.security.PersonDetails;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PersonDetails(
                personRepository.findUserByName(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );
    }
}
