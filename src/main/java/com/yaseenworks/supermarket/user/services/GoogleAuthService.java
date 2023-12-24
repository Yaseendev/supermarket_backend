package com.yaseenworks.supermarket.user.services;

import com.yaseenworks.supermarket.exceptions.GoogleAccessTokenException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleAuthService {
    private final String CLIENT_ID;

    @Autowired
    public GoogleAuthService(Dotenv dotenv) {
        CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    }


    public String verifyGoogleAccessToken(String accessToken) throws GoogleAccessTokenException {

        String googleTokenInfoUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(googleTokenInfoUrl, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("aud")) {
                String audience = (String) responseBody.get("aud");
                if (audience.equals(CLIENT_ID)) {
                    // Token is valid
                    return (String) responseBody.get("email");
                } else {
                    // Invalid audience (client ID)
                    throw new GoogleAccessTokenException("Invalid Google access token");
                }
            } else {
                // Unable to verify token
                throw new GoogleAccessTokenException("Unable to verify Google access token");
            }
        } else {
            // Token verification failed
            throw new GoogleAccessTokenException("Google access token verification failed");
        }
    }
}
