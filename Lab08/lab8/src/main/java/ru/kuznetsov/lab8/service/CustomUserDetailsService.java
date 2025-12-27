package ru.kuznetsov.lab8.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kuznetsov.lab8.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(usernameOrEmail);

        if (user == null)
            throw  new UsernameNotFoundException("Invalid username or email");

        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map((role -> new SimpleGrantedAuthority(role.getName())))
                        .collect(Collectors.toList())
        );
    }
}
