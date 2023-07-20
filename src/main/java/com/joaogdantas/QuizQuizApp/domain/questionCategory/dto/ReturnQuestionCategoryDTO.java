package com.joaogdantas.QuizQuizApp.domain.questionCategory.dto;

import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategory;

import java.util.UUID;

public record ReturnQuestionCategoryDTO(
        UUID id,
        String name,
        Float stars
) {
    public ReturnQuestionCategoryDTO(QuestionCategory questionCategory){
        this(
                questionCategory.getId(),
                questionCategory.getName(),
                questionCategory.getStars()
        );
    }
}
