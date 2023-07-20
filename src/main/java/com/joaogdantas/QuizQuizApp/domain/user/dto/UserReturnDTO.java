package com.joaogdantas.QuizQuizApp.domain.user.dto;

import com.joaogdantas.QuizQuizApp.domain.user.User;

import java.util.Date;
import java.util.UUID;

public record UserReturnDTO(
        UUID id,
        String name,
        String email,
        Date birthday,
        String imageUrl
) {
    public UserReturnDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getImageUrl()
        );
    }
}
