package com.example.seru.security;

import com.example.seru.model.user.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImp userDetailsServiceImp;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("masuk sini ngak");
        System.out.println(authentication);

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsServiceImp.loadUserByUsername(username);


        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(username,password,user.getAuthorities());

        System.out.println(userToken);

        return userToken;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
    }
}
