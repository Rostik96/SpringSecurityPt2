package com.rost.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rost.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthProviderImp implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        if (!password.equals((personDetails.getPassword())))
            throw new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(personDetails, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; //stub
    }
}
