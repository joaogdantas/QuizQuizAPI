package com.joaogdantas.QuizQuizApp.domain.user.dto;

import java.util.Date;

public record UserCreateDTO(
    String name,
    String email,
    String password,
    Date birthday,
    String imageUrl
) {
}
