package com.example.registration.controller;

import com.example.registration.dto.RegistrationRequest;
import com.example.registration.dto.RegistrationResponse;
import com.example.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path = "/register")
    public RegistrationResponse register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }

}
