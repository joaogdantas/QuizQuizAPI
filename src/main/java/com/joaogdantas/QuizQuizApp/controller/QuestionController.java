package com.joaogdantas.QuizQuizApp.controller;

import com.joaogdantas.QuizQuizApp.domain.question.Question;
import com.joaogdantas.QuizQuizApp.domain.question.QuestionRepository;
import com.joaogdantas.QuizQuizApp.domain.question.dto.CreateQuestionDTO;
import com.joaogdantas.QuizQuizApp.domain.question.dto.UpdateQuestionDTO;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategory;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/api/question/")
public class QuestionController {
    private Question question;
    private QuestionCategory questionCategory;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;
    @PostMapping("create")
    public ResponseEntity<String> createQuestion(@RequestBody CreateQuestionDTO newQuestionData, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Question> existentQuestion = questionRepository.findByQuestion(newQuestionData.question());

        if(existentQuestion.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Essa questão já existe, por favor insira um novo título.");
        }
        Question question = new Question();
        question.setQuestion(newQuestionData.question());
        question.setAlternatives(newQuestionData.alternatives());

        Optional<QuestionCategory> optionalQuestionCategory = questionCategoryRepository.findByName(newQuestionData.category());
        if (optionalQuestionCategory.isPresent()) {
            question.setCategory(optionalQuestionCategory.get());
            Question savedQuestion = questionRepository.save(question);

            var uri = uriComponentsBuilder.path("/api/question/{id}").buildAndExpand(savedQuestion.getCategory().getId()).toUri();

            return ResponseEntity.created(uri).body("Questão criada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada, por favor digite uma válida");
        }
    }
    @PostMapping("{id}/answer")
    public ResponseEntity answerQuestion(@PathVariable UUID id, @RequestBody String answer) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();

            question.setAnswer(answer);

            Question savedQuestion = questionRepository.save(question);

            return ResponseEntity.status(HttpStatus.OK).body("Questão respondida com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada!");
    }
    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable UUID id, @RequestBody UpdateQuestionDTO newQuestionData) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();

            question.setQuestion(newQuestionData.question());
            question.setAlternatives(newQuestionData.alternatives());

            Question savedQuestion = questionRepository.save(question);

            return ResponseEntity.status(HttpStatus.OK).body("Questão atualizada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada!");
    }
    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()){
            questionRepository.deleteByReferenceId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Questão deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada!");
    }
}
