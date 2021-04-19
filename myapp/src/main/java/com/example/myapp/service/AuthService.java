package com.example.myapp.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;


public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    public boolean authenticate(final String authCredentials){
        if(authCredentials == null){
            return false;
        }
        final String encodedUserAuthCredentials = authCredentials.replaceFirst("Basic ","");
        String decodedUserAuthCredentials = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedUserAuthCredentials);
            decodedUserAuthCredentials = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            LOGGER.error("errorMessage: {}", e.getMessage(), e);
        }

        final StringTokenizer tokenizer = new StringTokenizer(decodedUserAuthCredentials, ":");
        final String userName = tokenizer.nextToken();
        final String userPassword = tokenizer.nextToken();

        return "admin".equals(userName) && "admin".equals(userPassword);
    }


}
