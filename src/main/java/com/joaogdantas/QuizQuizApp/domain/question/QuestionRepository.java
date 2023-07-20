package com.joaogdantas.QuizQuizApp.domain.question;

import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID>{
    @Query("SELECT q FROM question q WHERE q.question = :question")
    Optional<Question> findByQuestion(@Param("question") String question);
    List<Question> findByCategoryName(String categoryName);
    @Modifying
    @Query("DELETE FROM question q WHERE q.id = :id")
    void deleteByReferenceId(@Param("id") UUID id);

}
