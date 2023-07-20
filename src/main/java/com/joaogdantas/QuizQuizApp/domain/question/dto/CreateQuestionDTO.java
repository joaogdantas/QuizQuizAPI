package com.joaogdantas.QuizQuizApp.domain.question.dto;

import java.util.List;

public record CreateQuestionDTO(
        String question,
        List<String> alternatives,
        String category
) {
}
