package com.yaseenworks.supermarket.user.services;

import com.yaseenworks.supermarket.user.models.AuthenticationResponse;
import com.yaseenworks.supermarket.user.records.AuthenticationRequest;
import com.yaseenworks.supermarket.user.records.RegistrationRequest;

public interface IAuthenticationService {
    AuthenticationResponse register(RegistrationRequest request);

     AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse tokenCheck(String bearerToken);
}
