package org.example.argen.service;

import org.example.argen.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public interface UserService {

    UserDetails loadUserByUsername(String username);

    boolean registerUser(User user);

    List<User> findAllUsers();

    void saveUser(User user, Boolean active, Map<String, String> form);
}
