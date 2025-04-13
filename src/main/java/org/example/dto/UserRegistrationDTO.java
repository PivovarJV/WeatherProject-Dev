package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 6, max = 20, message = "Длинна логина должна быть от 6 до 20 символов")
    private String login;
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 6, max = 20, message = "Длинна пароля должна быть от 6 до 20 символов")
    private String password;
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 6, max = 20, message = "Длинна пароля должна быть от 6 до 20 символов")
    private String repeatPassword;
}
