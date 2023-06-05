package com.example.seru.security;

import com.example.seru.exeption.ResourceNotFoundExeption;
import com.example.seru.model.user.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsServiceImp.loadUserByUsername(username);

        if (user != null){
            if (passwordEncoder.matches(password, user.getPassword())){
                // if it matches, then we can initialize UsernamePasswordAuthenticationToken.
                // Attention! We used its 3 parameters constructor.
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
                return authenticationToken;
            }
        }
        throw new ResourceNotFoundExeption("User is not found!");
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
    }
}
