package org.hell.homework12.service;

import org.hell.homework12.repository.UserRepository;
import org.hell.homework12.model.User;
import org.hell.homework12.security.AuthorizedUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public UserRepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user != null) {
            return new AuthorizedUserDetails(user);
        }
        throw new UsernameNotFoundException(String.format("User '%s' not found", username));
    }
}
