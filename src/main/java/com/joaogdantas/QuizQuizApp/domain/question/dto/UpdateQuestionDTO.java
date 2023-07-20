package com.joaogdantas.QuizQuizApp.domain.question.dto;

import java.util.List;

public record UpdateQuestionDTO(
        String question,
        List<String> alternatives
) {
}
