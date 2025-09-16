package pl.perfumeria.perfumery.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.perfumeria.perfumery.dto.UserRegistrationDto;
import pl.perfumeria.perfumery.repository.UserRepository;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRegistrationDto userDto = (UserRegistrationDto) obj;
        return userDto.getPassword().equals(userDto.getConfirmPassword());

    }
}
