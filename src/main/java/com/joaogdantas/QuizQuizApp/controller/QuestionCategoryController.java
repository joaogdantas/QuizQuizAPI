package com.joaogdantas.QuizQuizApp.controller;

import com.joaogdantas.QuizQuizApp.domain.question.Question;
import com.joaogdantas.QuizQuizApp.domain.question.QuestionRepository;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategory;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.QuestionCategoryRepository;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.dto.CreateQuestionCategoryDTO;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.dto.ReturnQuestionCategoryDTO;
import com.joaogdantas.QuizQuizApp.domain.questionCategory.dto.UpdateQuestionCategoryDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/category/question/")
public class QuestionCategoryController {
    private QuestionCategory questionCategory;
    private Question question;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;

    @PostMapping("create")
    public ResponseEntity<?> createQuestionCategory(@RequestBody CreateQuestionCategoryDTO newQuestionData, UriComponentsBuilder uriComponentsBuilder) {
        Optional<QuestionCategory> existentCategory = questionCategoryRepository.findByName(newQuestionData.name());

        if(existentCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Essa categoria já existe, por favor insira um novo nome.");
        }

        QuestionCategory questionCategory = new QuestionCategory();
        questionCategory.setName(newQuestionData.name());

        QuestionCategory savedQuestion = questionCategoryRepository.save(questionCategory);

        var uri = uriComponentsBuilder.path("/api/category/question/{id}").buildAndExpand(savedQuestion.getId()).toUri();

        return ResponseEntity.created(uri).body("Categoria criada com sucesso!");
    }

    @GetMapping("byId/{id}")
    public ResponseEntity getQuestionById(@PathVariable UUID id) {
        Optional<QuestionCategory> optionalCategory = questionCategoryRepository.findById(id);

        return optionalCategory.map(category -> ResponseEntity.ok(new ReturnQuestionCategoryDTO(category)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("byName/{name}")
    public ResponseEntity getQuestionByName(@PathVariable String name) {
        Optional<QuestionCategory> optionalCategory = questionCategoryRepository.findByName(name);

        return optionalCategory.map(category -> ResponseEntity.ok(new ReturnQuestionCategoryDTO(category)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("all")
    public ResponseEntity<List<ReturnQuestionCategoryDTO>> findAllCategories(){
        var questions = questionCategoryRepository.findAll();
        List<ReturnQuestionCategoryDTO> result = new ArrayList<>();

        questions.forEach(q -> result.add(new ReturnQuestionCategoryDTO(q.getId(), q.getName(), q.getStars())));

        return ResponseEntity.ok(result);
    }
    @PutMapping("updateCategoryName/{id}")
    public ResponseEntity updateCategoryName(@PathVariable UUID id, @RequestBody UpdateQuestionCategoryDTO updateQuestionCategoryDTO) {
        Optional<QuestionCategory> optionalCategory = questionCategoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            QuestionCategory questionCategory = optionalCategory.get();
            questionCategory.setName(updateQuestionCategoryDTO.name());

            QuestionCategory newQuestionCategory = questionCategoryRepository.save(questionCategory);

            return ResponseEntity.status(HttpStatus.OK).body("Categoria atualizada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
    }
    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable UUID id) {
        Optional<QuestionCategory> optionalQuestionCategory = questionCategoryRepository.findById(id);

        if (optionalQuestionCategory.isPresent()){
            questionCategoryRepository.deleteByReferenceId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
    }
}