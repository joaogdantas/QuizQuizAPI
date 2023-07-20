package com.joaogdantas.QuizQuizApp.controller;

import com.joaogdantas.QuizQuizApp.domain.user.User;
import com.joaogdantas.QuizQuizApp.domain.user.UserRepository;
import com.joaogdantas.QuizQuizApp.domain.user.dto.UserCreateDTO;
import com.joaogdantas.QuizQuizApp.domain.user.dto.UserReturnDTO;
import com.joaogdantas.QuizQuizApp.domain.user.dto.UserUpdateAvatarDTO;
import com.joaogdantas.QuizQuizApp.domain.user.dto.UserUpdateEmailDTO;
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
@RequestMapping("/api/user/")
public class UserController {
    private User user;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("SignUp")
    public ResponseEntity SignUp(@RequestBody UserCreateDTO data, UriComponentsBuilder uriComponentsBuilder){
        Optional<User> existentUserEmail = userRepository.findByEmail(data.email());

        if(existentUserEmail.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse email já foi registrado, por favor insira um novo.");
        }

        this.user = new User(data);
        User savedUser = userRepository.save(this.user);

        var uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).body("Usuário registrado com sucesso!");
    }
    @GetMapping("{id}")
    public ResponseEntity getUserById(@PathVariable UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> ResponseEntity.ok(new UserReturnDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("all")
    public ResponseEntity<List<UserReturnDTO>> findAllUsers(){
        var users = userRepository.findAll();
        List<UserReturnDTO> result = new ArrayList<>();

        users.forEach(u -> result.add(new UserReturnDTO(u.getId(), u.getImageUrl(), u.getName(), u.getBirthday(), u.getEmail())));

        return ResponseEntity.ok(result);
    }
    @PutMapping("updateAvatar/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody UserUpdateAvatarDTO newAvatar) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(newAvatar.imageUrl());

            User newUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Avatar atualizado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }
    @PutMapping("updateEmail/{id}")
    public ResponseEntity<String> updateEmail(@PathVariable UUID id, @RequestBody UserUpdateEmailDTO newEmail) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(newEmail.email());

            User newUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Email atualizado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dados do usuário deletados com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
    }
}