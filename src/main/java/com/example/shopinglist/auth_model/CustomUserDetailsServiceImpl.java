package com.example.shopinglist.auth_model;

import com.example.shopinglist.repository.AuthUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserRepo authUserRepo;

    public CustomUserDetailsServiceImpl(AuthUserRepo authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authUserRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Not find User: " + email)
        );
    }
}
