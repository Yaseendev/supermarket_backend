package com.yaseenworks.supermarket.repositories;

import com.yaseenworks.supermarket.user.models.Name;
import com.yaseenworks.supermarket.user.entities.User;
import com.yaseenworks.supermarket.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void printAllUsers() {

        List<User> users = userRepository.findAll();

        System.out.println("Users = " + users);
    }

    @Test
    public void insertUser() {

        User user = User.builder()
                .name(new Name(
                        "Ahmad" , "LasName"
                ))
                .email("email@example.com")
                .password(passwordEncoder.encode("12345678"))
                .phoneNumber("+201225562556")
                .build();

        System.out.println("User = " + userRepository.save(user));
    }

}
