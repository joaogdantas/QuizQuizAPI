package com.joaogdantas.QuizQuizApp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM user u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
