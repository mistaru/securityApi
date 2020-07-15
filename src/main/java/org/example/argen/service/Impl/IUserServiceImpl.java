package org.example.argen.service.Impl;

import org.example.argen.enums.Role;
import org.example.argen.entity.User;
import org.example.argen.repository.UserRepository;
import org.example.argen.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IUserServiceImpl implements UserDetailsService, IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public IUserServiceImpl(@NotNull PasswordEncoder passwordEncoder, @NotNull UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("This user was not found!");
        }

        return user;
    }

    public boolean registerUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        if (this.userRepository.count() == 0) {
            user.setActivity(true);
            user.setRoles(Collections.singleton(Role.ADMIN));
        } else {
            user.setActivity(false);
            user.setRoles(Collections.singleton(Role.USER));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user, Boolean active, Map<String, String> form) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setActivity(active);

        userRepository.save(user);
    }

}
