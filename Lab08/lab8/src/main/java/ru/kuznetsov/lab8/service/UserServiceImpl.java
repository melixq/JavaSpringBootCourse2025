package ru.kuznetsov.lab8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kuznetsov.lab8.dto.UserDto;
import ru.kuznetsov.lab8.entity.Role;
import ru.kuznetsov.lab8.entity.User;
import ru.kuznetsov.lab8.repository.RoleRepository;
import ru.kuznetsov.lab8.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        var user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        var role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null)
            role = checkRoleExist();

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        var users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        var userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        var role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
