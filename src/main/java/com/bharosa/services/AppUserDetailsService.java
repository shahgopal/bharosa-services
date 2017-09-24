package com.bharosa.services;

import com.bharosa.model.Role;
import com.bharosa.model.User;
import com.bharosa.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(AppUserDetailsService.class);

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername( s );
        if ( user.isPresent() ) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername( username );
        if ( user.isPresent() ) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }

    public User registerUser(User user) {
    	System.out.println("passwordEncoder"+passwordEncoder+ user.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.grantAuthority(Role.ROLE_USER);
            return userRepo.save( user );
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public boolean removeAuthenticatedUser() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findUserByUsername(username);
        int del = userRepo.deleteUserById(user.getId());
        return del > 0;
    }
}