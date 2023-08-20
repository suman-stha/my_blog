package com.myblog.blog.security;

import com.myblog.blog.entities.User;
import com.myblog.blog.exceptions.ResourceNotFoundException;
import com.myblog.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from dataase by username

        User user = this.userRepo.findByEmail(username).orElseThrow(() ->
                new ResourceNotFoundException("User", "email: " + username, 0));
        return user;
    }
}
