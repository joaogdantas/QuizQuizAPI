package com.joaogdantas.QuizQuizApp.domain.questionCategory;

import com.joaogdantas.QuizQuizApp.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, UUID> {
    @Query("SELECT c FROM category c WHERE c.name = :name")
    Optional<QuestionCategory> findByName(@Param("name") String name);
    @Modifying
    @Query("DELETE FROM category c WHERE c.id = :id")
    void deleteByReferenceId(@Param("id") UUID id);
}
