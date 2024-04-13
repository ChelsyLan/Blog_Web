package net.javaguides.blogwebapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "enter your first name")
    private String firstName;

    private String lastName;
    @NotEmpty(message = "enter your email")
    @Email
    private String email;
    @NotEmpty(message = "enter your password")
    private String password;
}
