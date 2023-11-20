package com.yaseenworks.supermarket.user.services;

import com.yaseenworks.supermarket.exceptions.UserExistsException;
import com.yaseenworks.supermarket.user.entities.User;
import com.yaseenworks.supermarket.user.records.RegistrationRequest;
import com.yaseenworks.supermarket.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = findByEmail(request.email());
        if(user.isPresent()) {
            throw new UserExistsException("User with email " + request.email() + " already exists");
        }
        User newUser = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
