package com.yaseenworks.supermarket.user.services;

import com.yaseenworks.supermarket.user.entities.User;
import com.yaseenworks.supermarket.user.records.RegistrationRequest;

import java.util.Optional;

public interface IUserService {
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
}
