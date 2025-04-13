package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    @NotBlank(message = "Поле не может быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я-]+$", message = "Должно содержать только латинские, русские буквы и дефис.")
    private String city;
}
