package pl.perfumeria.perfumery.dto;

import pl.perfumeria.perfumery.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@PasswordMatches(message = "Hasła muszą być takie same.")
@Getter
@Setter
public class UserRegistrationDto {

    @NotEmpty(message = "Imię nie może być puste.")
    private String firstName;

    @NotEmpty(message = "Nazwisko nie może być puste.")
    private String lastName;

    @Email(message = "Podaj poprawny adres email.")
    @NotEmpty(message = "Email nie może być pusty.")
    private String email;

    @NotEmpty(message = "Hasło nie może być puste.")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków.")
    private String password;

    private String confirmPassword;

}
