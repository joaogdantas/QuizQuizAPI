package com.joaogdantas.QuizQuizApp.domain.questionCategory;

import com.joaogdantas.QuizQuizApp.domain.question.Question;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "category")
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class QuestionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "stars")
    private Float stars;
    @OneToMany(mappedBy = "category")
    private List<Question> questions;
}
