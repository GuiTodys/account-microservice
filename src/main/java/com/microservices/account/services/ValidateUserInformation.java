package com.microservices.account.services;

import com.microservices.account.payloads.UserValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ValidateUserInformation {

    public UserValidationResponse execute(String userLogIn, String userPassword) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> params = new HashMap<String, String>();
        params.put("cpf", userLogIn);
        params.put("password", userPassword);

        HttpEntity<?> entity = new HttpEntity<>(params, headers);


        ResponseEntity<UserValidationResponse> userValidationResponseEntity = restTemplate.exchange("http://localhost:8080/users/validation", HttpMethod.POST,
                entity, UserValidationResponse.class);

        return userValidationResponseEntity.getBody();
    }
}
