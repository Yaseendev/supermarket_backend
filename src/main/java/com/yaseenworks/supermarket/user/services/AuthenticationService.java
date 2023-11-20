package com.yaseenworks.supermarket.user.services;

import com.yaseenworks.supermarket.configurations.JwtUtils;
import com.yaseenworks.supermarket.exceptions.UserExistsException;
import com.yaseenworks.supermarket.exceptions.WrongCredentialsException;
import com.yaseenworks.supermarket.user.models.AuthenticationResponse;
import com.yaseenworks.supermarket.user.entities.User;
import com.yaseenworks.supermarket.user.records.AuthenticationRequest;
import com.yaseenworks.supermarket.user.records.RegistrationRequest;
import com.yaseenworks.supermarket.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()){
            throw new UserExistsException("A user with the email address entered is already registered, Login in or try another email address");
        }
       var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .build();
        userRepository.save(user);
        var authToken = jwtService.generateToken(user);
        System.out.println(authToken);
        return AuthenticationResponse.builder()
                .user(user.toUserResponse())
                .token(authToken)
                .build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new WrongCredentialsException("Email or password are incorrect");
        }


        var user = userRepository.findByEmail(request.email()).orElseThrow();
      //  userRepository.save(user);
        var authToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(authToken)
                .user(user.toUserResponse())
                .build();
    }

    @Override
    public AuthenticationResponse tokenCheck(String bearerToken) {
        if(bearerToken.startsWith("Bearer ")){
            final String token = bearerToken.substring("Bearer ".length());
            final String email = jwtService.extractUsername(token);
            if(email != null){
                Optional<User> userDetails = userRepository.findByEmail(email);
                if(userDetails.isPresent() && jwtService.isTokenValid(token, userDetails.get().getUsername())) {
                    return  AuthenticationResponse
                            .builder()
                            .token(token)
                            .user(userDetails.get().toUserResponse())
                            .build();
                }
            }
        }
        return AuthenticationResponse.builder().token(bearerToken).build();
    }
}
