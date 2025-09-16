package pl.perfumeria.perfumery.service;

import pl.perfumeria.perfumery.dto.UserRegistrationDto;

public interface UserService {

    void registerUser(UserRegistrationDto registrationDto);
}
