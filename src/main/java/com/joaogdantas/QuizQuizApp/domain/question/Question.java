package com.joaogdantas.QuizQuizApp.domain.question;

import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategory;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

import java.util.List;

@Entity(name = "question")
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "question")
    private String question;
    @Column(name = "alternatives")
    private List<String> alternatives;
    @Column(name = "answer")
    private String answer;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private QuestionCategory category;
}
