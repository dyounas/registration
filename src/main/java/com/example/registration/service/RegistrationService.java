package com.example.registration.service;

import com.example.registration.dto.RegistrationRequest;
import com.example.registration.dto.RegistrationResponse;

public interface RegistrationService {
    RegistrationResponse register(RegistrationRequest registrationRequest);
}
