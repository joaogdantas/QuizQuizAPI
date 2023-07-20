package com.joaogdantas.QuizQuizApp.domain.question.dto;

import com.joaogdantas.QuizQuizApp.domain.question.Question;

import java.util.List;
import java.util.UUID;

public record ReturnQuestionDTO(
        UUID id,
        String question,
        List<String> alternatives
) {
    public ReturnQuestionDTO(Question question){
        this(
                question.getId(),
                question.getQuestion(),
                question.getAlternatives()
        );
    }
}
