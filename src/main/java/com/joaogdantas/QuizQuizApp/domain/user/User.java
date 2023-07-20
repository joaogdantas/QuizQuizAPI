package com.joaogdantas.QuizQuizApp.domain.user;

import com.joaogdantas.QuizQuizApp.domain.user.dto.UserCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "image_url")
    private String imageUrl;

    public User(UserCreateDTO data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.birthday = data.birthday();
        this.imageUrl = data.imageUrl();
    }
}
