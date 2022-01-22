package com.example.registration.service.impl;

import com.example.registration.dto.IpApiResponse;
import com.example.registration.dto.RegistrationRequest;
import com.example.registration.dto.RegistrationResponse;
import com.example.registration.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {

        RegistrationResponse registrationResponse = new RegistrationResponse();

        //validate if password is proper as per mentioned requirements
        boolean isPasswordValidated = validatePassword(registrationRequest.getPassword(), registrationResponse);

        if (isPasswordValidated) {

            //ip-api call to get geoloaction details
            String url = "http://ip-api.com/json/" + registrationRequest.getIp();
            IpApiResponse result = restTemplate.getForObject(url, IpApiResponse.class);

            //check to validate user location
            if (result != null && result.getCountry() != null && !result.getCountry().equalsIgnoreCase("Canada")) {
                registrationResponse.setError("User is not eligible to Register.");
            } else {
                //if all validations are passed populate response
                registrationResponse.setUuid(String.valueOf(UUID.randomUUID()));
                registrationResponse.setMessage("Welcome " + registrationRequest.getUsername() + ". City : " + result.getCity());
            }
        }
        return registrationResponse;
    }

    private boolean validatePassword(String password, RegistrationResponse registrationResponse) {
        if (password.length() < 8) {
            registrationResponse.setError("Password should be atleast 8 characters long.");
            return false;
        } else if (!password.matches(".*[A-Z].*")) {
            registrationResponse.setError("Password must contain a capital letter.");
            return false;
        } else if (!password.matches(".*[0-9].*")) {
            registrationResponse.setError("Password must contain numeric characters.");
            return false;
        } else if (!password.matches(".*[_#$%.].*")) {
            registrationResponse.setError("Password must contain a special character.");
            return false;
        }
        return true;
    }
}
